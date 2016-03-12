package traffic.signal.control

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import scala.collection.mutable._
import akka.actor._
import traffic.signal.actors._
import traffic.signal.models._
import scala.concurrent.duration._
import java.lang.System.currentTimeMillis

object Application {

  val mainActorSystem = ActorSystem("main")

  def main(args: Array[String]) {

    val helpString = "Usage: sbt \"run greentime- IN SECONDS\""
    if (args.head.equals("--help") || args.head.equals("-h")) {
      println(helpString)
      System.exit(0)
    }

    val greenTime: FiniteDuration =
      if (args.length > 0) {
        if (args.head.toLowerCase() == "greentime-") args(1).toInt.seconds else 10.seconds
      } else 10.seconds

    //Starting from South to North Direction    
    val lightActor = mainActorSystem.actorOf(Props(new SignalControlerActor(greenTime, South2North)), "signalControllerActor_" + currentTimeMillis)

    lightActor ! Schedule

  }
}


