import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main{
    public long currentTimestamp(){
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis;
    }

    public static void main(String[] args) {
        // GLOBAL VARIABLES
        int buffer_wait = 11; //11
        int total_hours = 24; //24
        int total_timer = 0; 
        int time_allowed_to_wait = 1; // 2
        int time_counter = 0;
        int length_counter = 0;
        int total_left_lane_wait = 0;
        int total_right_lane_wait = 0;
        int total_crash_count = 0;

        // random deciding factor of who goes first, left lane or right lane:
        // if stop_light is 1, right lane goes. if 0, left lane goes.
        int stop_light = (int)Math.floor(Math.random() * (1 - 0 + 1) + 0);

        // traffic jam
        int traffic_jam_counter = 0;

        // car
        Car carClass = new Car();

        // left lane
        Queue<ArrayList<Long>> leftLane = new LinkedList<>();



        // right lane
        Queue<ArrayList<Long>> rightLane = new LinkedList<>();

        // simulate cars driving
        while(total_timer < total_hours){

            // right lane can go first
            if(stop_light == 1){
                // need to randomly add cars to left and right lane
                int decider = 60;
                while(decider > 0){

                    // right lane goes first
                    int car_adder = (int)Math.floor(Math.random() * (10 - 0 + 10) + 0);
                    if(car_adder >= 0 && car_adder <= 4){
                        // add to right lane
                        int for_loop_adder = (int)Math.floor(Math.random() * (2 - 0 + 2) + 0);
                        for(int i = 0; i < for_loop_adder; i++){
                            rightLane.add(carClass.addCarToRightLane());
                        }
                    }
                    else if(car_adder >= 5 && car_adder <= 9){
                        // add to left lane
                        int for_loop_adder = (int)Math.floor(Math.random() * (2 - 0 + 2) + 0);
                        for(int i = 0; i < for_loop_adder; i++){
                            leftLane.add(carClass.addCarToLeftLane());
                        }
                    }
                    // else{
                    //     int last_car_adder = (int)Math.floor(Math.random() * (1 - 0 + 1) + 0);
                    //     if(last_car_adder == 0){
                    //         leftLane.add(carClass.addCarToLeftLane());
                    //     }else{
                    //         rightLane.add(carClass.addCarToRightLane());
                    //     }
                    // }

                    // check if the left lane has access
                    if(stop_light == 0){
                        // see if the left lane is empty
                        if(!leftLane.isEmpty()){
                            // allow a car to pass since the left lane has access
                            leftLane.remove();
                        }
                        long total_time_waited = 0;
                        if(!rightLane.isEmpty()){
                            total_time_waited = carClass.currentTimestamp(false) - rightLane.peek().get(0);
                        }
                        if(rightLane.size() > buffer_wait || total_time_waited > time_allowed_to_wait){
                            if(total_time_waited > time_allowed_to_wait){
                                time_counter++;
                            }else{
                                length_counter++;
                            }
                            total_right_lane_wait += total_time_waited;
                            stop_light = carClass.flipStopLight(stop_light);
                        }
                    }
                    else if(stop_light == 1){
                        if(!rightLane.isEmpty()){
                            rightLane.remove();
                        }
                        long total_time_waited = 0;
                        if(!leftLane.isEmpty()){
                            total_time_waited = carClass.currentTimestamp(false) - leftLane.peek().get(0);
                        }
                        if(leftLane.size() > buffer_wait || total_time_waited > time_allowed_to_wait){
                            if(total_time_waited > time_allowed_to_wait){
                                time_counter++;
                            }else{
                                length_counter++;
                            }
                            total_left_lane_wait += total_time_waited;
                            stop_light = carClass.flipStopLight(stop_light);
                        }
                    }

                    decider--;
                }
            }
            
            // left lane can go first
            else if(stop_light == 0){
                // need to randomly add cars to left and right lane
                int decider = 60;
                while(decider > 0){
                    // right lane goes first
                    int car_adder = (int)Math.floor(Math.random() * (10 - 0 + 10) + 0);
                    if(car_adder >= 0 && car_adder <= 3){
                        // add to right lane
                        int for_loop_adder = (int)Math.floor(Math.random() * (2 - 0 + 2) + 0);
                        for(int i = 0; i < for_loop_adder; i++){
                            rightLane.add(carClass.addCarToRightLane());
                        }
                    }
                    else if(car_adder >= 6 && car_adder <= 9){
                        // add to left lane
                        int for_loop_adder = (int)Math.floor(Math.random() * (2 - 0 + 2) + 0);
                        for(int i = 0; i < for_loop_adder; i++){
                            leftLane.add(carClass.addCarToLeftLane());
                        }
                    }
                    // else{
                    //     int last_car_adder = (int)Math.floor(Math.random() * (1 - 0 + 1) + 0);
                    //     if(last_car_adder == 0){
                    //         leftLane.add(carClass.addCarToLeftLane());
                    //     }else{
                    //         rightLane.add(carClass.addCarToRightLane());
                    //     }
                    // }

                    if(stop_light == 0){
                        if(!leftLane.isEmpty()){
                            leftLane.remove();
                        }
                        long total_time_waited = 0;
                        if(!rightLane.isEmpty()){
                            total_time_waited = carClass.currentTimestamp(false) - rightLane.peek().get(0);
                        }
                        // add logic to also check if they are waiting for a certain amount of time instead of how many people are waiting!
                        if(rightLane.size() > buffer_wait || total_time_waited > time_allowed_to_wait){
                            if(total_time_waited > time_allowed_to_wait){
                                time_counter++;
                            }else{
                                length_counter++;
                            }
                            total_right_lane_wait += total_time_waited;
                            stop_light = carClass.flipStopLight(stop_light);
                        }
                    }
                    else if(stop_light == 1){
                        if(!rightLane.isEmpty()){
                            rightLane.remove();
                        }
                        long total_time_waited = 0;
                        if(!leftLane.isEmpty()){
                            total_time_waited = carClass.currentTimestamp(false) - leftLane.peek().get(0);
                        }
                        if(leftLane.size() > buffer_wait || total_time_waited > time_allowed_to_wait){
                            if(total_time_waited > time_allowed_to_wait){
                                time_counter++;
                            }else{
                                length_counter++;
                            }
                            total_left_lane_wait += total_time_waited;
                            stop_light = carClass.flipStopLight(stop_light);
                        }
                    }

                    decider--;
                }
            }
            // System.out.println("Right lane: "+rightLane.toString());
            // System.out.println("Left lane: "+leftLane.toString());
            // System.out.println();
            // System.out.println(total_left_lane_wait + total_right_lane_wait);

            total_timer++;
        }
        System.out.println("---------------------------------------------------------------");
        System.out.println("End of "+total_timer+" cycles. The traffic summary is:");
        System.out.println("Right lane currently has "+rightLane.size() + " cars waiting in it.");
        System.out.println("Right lane total wait time: "+total_right_lane_wait);
        System.out.println("Left lane currently has "+leftLane.size() + " cars waiting in it.");
        System.out.println("Left lane total wait time: "+total_left_lane_wait);
        System.out.println("---------------------------------------------------------------");
        System.out.println("Total crashes: "+ total_crash_count);
        System.out.println("Traffic light changed because time limit was reached: "+time_counter);
        System.out.println("Traffic light changed because car length limit was reached: "+length_counter);
        System.out.println("---------------------------------------------------------------");
    }
}
