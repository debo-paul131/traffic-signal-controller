package traffic.signal.models

import scala.concurrent.duration._

case object Light {
  import pl.project13.scala.rainbow.Rainbow._
  val green = "Green".green
  val red = "Red".red
  val amber = "Amber".yellow
}

/**
 * In total there are 12 signal elements . Each Signal element represent a direction of traffic
 * 
 * @param name the phase
 * @param opposite phase of the current phase
 * @param next phase 
 * @param other phase
 * @param left turning signal
 * @param straight turning signal
 * @param right turning signal
 */

trait LightState {
  val name: String
  def opposite: Option[LightState] = None
  def next: Option[LightState] = None
  def other: Option[LightState] = None
  def left: String = Light.red
  def straight: String = Light.red
  def right: String = Light.red
}

/**
 * *************************Straight traversing*********************************
 * ********************North-to-South & South-to-North phase********************
 */
case object South2North extends LightState {
  val name = "Traffic from South"
  override val opposite = Some(North2South)
  override val next = Some(South2NorthTransition)
  override val other = Some(South2West)
  override val straight = Light.green
}

case object South2NorthTransition extends LightState {
  val name = "Traffic from South"
  override val opposite = Some(North2SouthTransition)
  override val next = Some(South2East)
  override val other = Some(South2WestTransition)
  override val straight = Light.amber
}

case object North2South extends LightState {
  val name = "Traffic from North"
  override val straight = Light.green
}

case object North2SouthTransition extends LightState {
  val name = "Traffic from North"
  override val straight = Light.amber
}

/**
 * *************************Straight traversing*********************************
 * *******************East-to-West & West-to-East phase*************************
 */
case object West2East extends LightState {
  val name = "Traffic from West "
  override val opposite = Some(East2West)
  override val next = Some(West2EastTransition)
  override val other = Some(West2North)
  override val straight = Light.green
}

case object West2EastTransition extends LightState {
  val name = "Traffic from West "
  override val opposite = Some(East2WestTransition)
  override val next = Some(West2South)
  override val other = Some(West2NorthTransition)
  override val straight = Light.amber
}

case object East2West extends LightState {
  val name = "Traffic from East "
  override val straight = Light.green
}

case object East2WestTransition extends LightState {
  val name = "Traffic from East "
  override val straight = Light.amber
}

/**
 * **************************Right traversing************************************
 * ********************South-to-East & North-to-West phase***********************
 */
case object South2East extends LightState {
  val name = "Traffic from South"
  override val opposite = Some(North2West) //W2E
  override val next = Some(South2EastTransition)
  override val other = Some(West2South)
  override val right = Light.green
}

case object South2EastTransition extends LightState {
  val name = "Traffic from South"
  override val opposite = Some(North2WestTransition) //W2E
  override val next = Some(West2East)
  override val other = Some(West2South)
  override val right = Light.amber
}

case object North2West extends LightState {
  val name = "Traffic from North"
  override val right = Light.green
}

case object North2WestTransition extends LightState {
  val name = "Traffic from North"
  override val right = Light.amber
}

/**
 * *************************Right traversing************************************
 * *******************West-to-South & East-to-North phase***********************
 */
case object West2South extends LightState {
  val name = "Traffic from West "
  override val opposite = Some(East2North) //S2N
  override val next = Some(West2SouthTransition)
  override val other = Some(South2East)
  override val right = Light.green
}

case object West2SouthTransition extends LightState {
  val name = "Traffic from West "
  override val opposite = Some(East2NorthTransition) //S2N
  override val next = Some(South2North)
  override val other = Some(South2East)
  override val right = Light.amber
}

case object East2North extends LightState {
  val name = "Traffic from East "
  override val right = Light.green
}

case object East2NorthTransition extends LightState {
  val name = "Traffic from East "
  override val right = Light.amber
}

/**
 * *************************Left traversing*************************************
 * *******************South-to-West & North-to-East phase***********************
 */

case object South2West extends LightState {
  val name = "Traffic from West "
  override val opposite = Some(North2East)
  override val left = Light.green
}
case object South2WestTransition extends LightState {
  val name = "Traffic from West "
  override val opposite = Some(North2EastTransition)
  override val left = Light.amber
}

case object North2East extends LightState {
  val name = "Traffic from East "
  override val left = Light.green
}
case object North2EastTransition extends LightState {
  val name = "Traffic from East "
  override val left = Light.amber
}

/**
 * *************************Left traversing*************************************
 * *******************West-to-North & East-to-South phase***********************
 */

case object West2North extends LightState {
  val name = "Traffic from North"
  override val opposite = Some(East2South)
  override val left = Light.green
}

case object West2NorthTransition extends LightState {
  val name = "Traffic from North"
  override val opposite = Some(East2SouthTransition)
  override val left = Light.amber
}

case object East2South extends LightState {
  val name = "Traffic from South"
  override val left = Light.green
}

case object East2SouthTransition extends LightState {
  val name = "Traffic from South"
  override val left = Light.amber
}
