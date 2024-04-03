import java.util.ArrayList;

public class Car {
    ArrayList<ArrayList<Long>> carsRightLane = new ArrayList<>();
    ArrayList<ArrayList<Long>> carsLeftLane = new ArrayList<>();

    int rightAdder = 0;
    int leftAdder = 0;

    long adder = 0;

    public long currentTimestamp(boolean val) {
        // return (int) (System.currentTimeMillis() / 1000);
        if(val == true){
            adder++;
            return System.currentTimeMillis() + adder;
        }else{
            return System.currentTimeMillis() + adder;
        }
    }

    public ArrayList<Long> addCarToRightLane(){
        ArrayList<Long> list = new ArrayList<>(); 
        list.add(currentTimestamp(true));
        rightAdder++;
        list.add( (long) carsRightLane.size() + 1);
        carsRightLane.add(list);
        return list;
    }


    public ArrayList<Long> addCarToLeftLane(){
        ArrayList<Long> list = new ArrayList<>(); 
        list.add(currentTimestamp(true));
        leftAdder++;
        list.add((long)carsLeftLane.size() + 1);
        carsLeftLane.add(list);
        return list;
    }


    public ArrayList<ArrayList<Long>> getLeftLaneCars(){
        return carsLeftLane;
    }

    public ArrayList<ArrayList<Long>> getRightLaneCars(){
        return carsRightLane;
    }

    public int flipStopLight(int currentState){
        if(currentState == 1){
            return 0;
        }else{
            return 1;
        }
    }

}
