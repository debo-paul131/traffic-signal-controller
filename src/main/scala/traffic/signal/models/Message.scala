package traffic.signal.models

import scala.concurrent.duration._
import traffic.signal.util._

trait Message

case class Signal(route: String, left: String, straight: String, right: String) extends Message
case class Log(signal: Signal) extends Message

trait Command extends Message

case object Schedule extends Command
case object LightUp extends Command
case object SwitchPhase extends Command
case object ScheduleOnce extends Command