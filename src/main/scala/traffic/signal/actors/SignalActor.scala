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
import scala.util.matching.Regex

class SignalActor(lightState: LightState) extends Actor {

  val dateTimeFormat = DateTimeFormat.forPattern("h:mm:ss aa")
  var signals = new Array[Signal](4)
  
  def receive = {
    case LightUp => {
      println("\n")
      println(" Time : ".onBlue + dateTimeFormat.print(DateTime.now))

      for {
        oppositeLightState <- lightState.opposite
        blockLightState <- lightState.block
        nextblockLightState <- blockLightState.opposite
      } yield {
        signals = Array(Utils.getColoredSignals(lightState), Utils.getColoredSignals(oppositeLightState),
          Signal(blockLightState.name, blockLightState.left, Light.red, Light.red),
          Signal(nextblockLightState.name, nextblockLightState.left, Light.red, Light.red))
      }

      signals.map { signal =>
        new Regex("(East|North|South|West)").findFirstIn(signal.route) match {
          case Some("North") => signals(0) = signal
          case Some("East")  => signals(1) = signal
          case Some("South") => signals(2) = signal
          case _             => signals(3) = signal
        }
      }

      signals.map { signal =>
        self ! Log(signal)
      }
    }
    case Log(signal: Signal) =>
      println(signal.route + "  " + signal.left + " | " + signal.straight + " | " + signal.right)

  }
}