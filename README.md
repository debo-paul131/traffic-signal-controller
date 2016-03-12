
# traffic-signal-controller
The controller is designed to control traffic signal at a 2-way intersection of roads using 4 phase traffic signaling method. The following image displays different phases , avoiding all conflicts.   

![alt tag](https://github.com/debo-paul131/traffic-signal-controller/blob/master/image%20/4PhaseSignaling.jpg)


##Downloading, building

git clone https://github.com/debo-paul131/traffic-signal-controller.git

sbt compile

## Running

sbt "run greentime-  NUMBER-OF-SECONDS"

Eg : If you want to give green signal an interval of 15 seconds , then sbt "run greentime- 15"

## Help

sbt "run --help"

## Referance
https://www.youtube.com/watch?v=6yCbtgr7rSI
