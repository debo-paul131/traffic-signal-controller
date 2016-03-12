package traffic.signal.util

import pl.project13.scala.rainbow.Rainbow._
import traffic.signal.models._



object Utils {

  def getColoredSignals(lightState: LightState) = Signal(lightState.name, lightState.left, lightState.straight, lightState.right)

}