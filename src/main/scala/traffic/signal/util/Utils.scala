package traffic.signal.util

import pl.project13.scala.rainbow.Rainbow._
import traffic.signal.models._
import scala.concurrent.duration._

object Utils {

  def getColoredSignals(lightState: LightState) = Signal(lightState.name, lightState.left, lightState.straight, lightState.right)

  def calculatePhaseDelay(frequency: FiniteDuration, greenTime: FiniteDuration, amberTime: FiniteDuration, lightState: LightState):FiniteDuration = {
    lightState.straight match {
      case Light.green => (amberTime - frequency)
      case Light.amber => greenTime / 2
      case Light.red => lightState.right match {
        case Light.green => (amberTime - frequency)
        case _           => 0.seconds
      }
    }
  }
}