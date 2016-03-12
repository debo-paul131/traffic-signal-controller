
# traffic-signal-controller
The controller is designed to control traffic signal at a 2-way intersection of roads using 4 phase traffic signaling method. The following image displays different phases , avoiding all conflicts.   

![alt tag](https://github.com/debo-paul131/traffic-signal-controller/blob/master/image%20/4PhaseSignaling.jpg)

Assuming the traffic going straight (north-south and east-west) to be double of the traffic that makes a turn i.e time taken to clear the straight going traffic pool is more than clearing turning traffic pool. 

From the picture , p1 and p2 are two phases which allows traversing of straight going traffic and left turning traffic and p3 and p4 phases allows traversing only right turing traffic. Though p1 and p2 allows both straight and left turning traffic , i am considering only the straight going iterval time for these phases . Therefore  p1 and p2 will take double the time compared to p3 and p4. Eg. If p1 , p2 takes X time , then p3,p4 will take X/2 time .


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

## Reference
https://www.youtube.com/watch?v=6yCbtgr7rSI
