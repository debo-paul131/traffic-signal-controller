package traffic.signal.actors

import akka.actor._
import traffic.signal.models._
import scala.concurrent.duration._
import traffic.signal.control.ExecutionContexts.actorExecutionContext

class SwitchActor(lightActorRef: Option[ActorRef], delay: FiniteDuration) extends Actor {

  def receive = {
    case ScheduleOnce => context.system.scheduler.scheduleOnce(delay, self, StartPhase)

    case StartPhase => lightActorRef.map { ref =>
      ref ! LightUp
    }
  }
}