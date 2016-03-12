
# traffic-signal-controller
The controller is designed to control traffic signal at a 2-way intersection of roads using 4 phase traffic signaling method. The following image displays different phases , avoiding all conflicts.   

![alt tag](https://github.com/debo-paul131/traffic-signal-controller/blob/master/image%20/4PhaseSignaling.jpg)

Considering the traffic going straight (north-south and east-west) to be double of the traffic that makes a turn. 

From the picture , p1 and p2 takes double the time compared to p3 and p4 . If p1 , p2 takes X time , then p3,p4 will take X/2 time . 


##Downloading, building

git clone https://github.com/debo-paul131/traffic-signal-controller.git

sbt compile

## Running

sbt "run greentime-  NUMBER-OF-SECONDS"

Eg : If you want to give green signal an interval of 15 seconds , then sbt "run greentime- 15"

## Build Eclipse project

sbt eclipse

## Help

sbt "run --help"

## Referance
https://www.youtube.com/watch?v=6yCbtgr7rSI
