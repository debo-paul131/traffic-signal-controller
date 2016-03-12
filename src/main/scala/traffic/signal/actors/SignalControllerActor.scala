package traffic.signal.actors

import akka.actor._
import traffic.signal.models._
import traffic.signal.actors._
import traffic.signal.util._
import scala.concurrent.duration._
import java.lang.System.currentTimeMillis
import traffic.signal.control.ExecutionContexts.actorExecutionContext

class SignalControlerActor(greenTime: FiniteDuration, startPhase: LightState) extends Actor {

  var phaseDelay = 0.seconds
  var currentPhase: LightState = startPhase
  val amberTime = 10.seconds
  val initialDelay = greenTime / 2
  val frequency = greenTime / 2

  def getLightActorRef(prop: Props): ActorRef = context.actorOf(prop, name = "lightActor_" + currentTimeMillis)

  def receive = {
    case Schedule => {

      getLightActorRef(Props(new SignalActor(startPhase))) ! LightUp

      context.system.scheduler.schedule(initialDelay, frequency, new Runnable() {
        def run() {
          val currPhase = currentPhase.next
          context.actorOf(Props(new SwitchActor(
            currPhase.map { lightState =>

              currentPhase = lightState

              phaseDelay += Utils.calculatePhaseDelay(frequency, greenTime, amberTime, lightState)

              getLightActorRef(Props(new SignalActor(lightState)))

            }, phaseDelay)), "switchAcior_" + currentTimeMillis) ! ScheduleOnce
        }
      })
    }
  }
}
