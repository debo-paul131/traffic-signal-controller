package traffic.signal.control

import traffic.signal.control.Application.mainActorSystem
import scala.concurrent.ExecutionContext

object ExecutionContexts {
  implicit val actorExecutionContext: ExecutionContext = mainActorSystem.dispatchers.lookup("actor-context")
}