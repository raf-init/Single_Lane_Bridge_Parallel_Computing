# Single_Lane_Bridge_Parallel_Computing

![alt text](https://github.com/rafchriz/SingleLaneBridge_Parallel-Computing/blob/master/single_lane_bridge.png)

The bridge shown in the picture permits only a single lane of traffic. This means, that only cars moving to the same direction can exist on the bridge at the same time. A safety violation occurs, when two moving cars of different direction enter, at the same time, the bridge.
This problem represents concurrency. The cars are the threads and the bridge is the shared CPU.
The input file specifies how many cars exist on the west entrance and how many of them on the east entrance of the bridge.
Crossing time of the bridge is 1s.
Cars arrive from the west every 1s. Cars arrive from the east every 2s.
We set edelay>wdelay.
Seconds were used for the clarity of the results output file.

There are 4 cases:

*not_safe-not_fair:
This is when cars crash and others continue to enter, so the programm stops.

*safe-not_fair:
There are no crashes. However, the car that manages to reach the bridge, crosses it. There is a chance that cars from one direction to never cross the bridge.

*safe-with_switch:
Cars cross the bridge alternately.

*fair-safe-switch_with_timer:
Depending on the arrival time, the algorithm sets priorities. We set the frequency of the arrival of the west cars less than or equal to the frequency of the east cars.

## Note #1:
If crossing time is greater than the arrival time of both sites, the safe-with_switch case is enough as a solution.

## Note #2:
Sometimes, timestamps printed on the output file may be inaccurate. This is because other tasks run in the background and System.out.println() is time consuming. System.out belongs to static PrintStream class, so during printing different stuff happen, such as bytes are sent to the console and every character, as well as the console, need to be rendered.
