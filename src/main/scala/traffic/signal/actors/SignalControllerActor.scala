package traffic.signal.actors

import akka.actor._
import traffic.signal.models._
import traffic.signal.actors._
import scala.concurrent.duration._
import java.lang.System.currentTimeMillis
import traffic.signal.control.ExecutionContexts.actorExecutionContext

class SignalControlerActor(greenTime: FiniteDuration) extends Actor {

  var delay = 0.seconds
  var currentPhase: LightState = South2North
  val amberTime = 10.seconds
  val initialDelay = greenTime / 2
  val frequency = greenTime / 2

  def getLightActorRef(prop: Props): ActorRef = context.actorOf(prop, name = "lightActor_" + currentTimeMillis)

  def receive = {
    case Schedule => {

      getLightActorRef(Props(new SignalActor(South2North))) ! LightUp

      context.system.scheduler.schedule(initialDelay, frequency, new Runnable() {
        def run() {
          val currPhase = currentPhase.next

          context.actorOf(Props(new SwitchActor(
            currPhase.map { lightState =>

              currentPhase = lightState

              lightState.straight match {
                case Light.green => delay += (amberTime - frequency)
                case Light.amber => delay += greenTime / 2
                case Light.red => lightState.right match {
                  case Light.green => delay += (amberTime - frequency)
                  case _           => delay
                }
              }

              getLightActorRef(Props(new SignalActor(lightState)))

            }, delay))) ! ScheduleOnce

        }
      })
    }

  }

}
