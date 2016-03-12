package traffic.signal.actors

import traffic.signal.models._
import akka.actor._
import scala.concurrent.duration._
import traffic.signal.control.ExecutionContexts.actorExecutionContext
import java.lang.System.currentTimeMillis
import org.joda.time.format.DateTimeFormat
import com.github.nscala_time.time.Imports.DateTime
import traffic.signal.util._
import pl.project13.scala.rainbow.Rainbow._
import scala.collection.mutable._

class SignalActor(lightState: LightState) extends Actor {

  val signals = ListBuffer[Signal]()

  val dateTimeFormat = DateTimeFormat.forPattern("h:mm:ss aa")

  implicit def orderingByRoutes[A <: Signal]: Ordering[A] = Ordering.by(_.route)

  def receive = {
    case LightUp => {
      println("\n")
      println(" Time : ".onBlue + dateTimeFormat.print(DateTime.now))

      for {
        oppositeLightState <- lightState.opposite
        blockLightState <- lightState.block
        nextblockLightState <- blockLightState.opposite
      } yield {
        signals += Utils.getColoredSignals(lightState)
        signals += Utils.getColoredSignals(oppositeLightState)
        signals += Signal(blockLightState.name, blockLightState.left, Light.red, Light.red)
        signals += Signal(nextblockLightState.name, nextblockLightState.left, Light.red, Light.red)
      }

      signals.sorted.map { signal =>
        self ! Log(signal)
      }
    }
    case Log(signal: Signal) =>
      println(signal.route + "  " + signal.left + " | " + signal.straight + " | " + signal.right)

  }
}