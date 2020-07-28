import java.util.ArrayList;
import java.util.List;

public class Cycle {

    //  *** Fields ***

    private List<Elephant> elephants;
    private int sum;
    private int min;

    // *** Counstructors ***

    public Cycle(int sum, int min){
        elephants = new ArrayList<>();
        this.sum = sum;
        this.min = min;
    }

    // *** Public methods ***

    public long getResult(int globalMin){
        return Math.min(method1(), method2(globalMin));
    }

    // *** Private methods ***

    private long method1(){
        return sum + (elephants.size() - 2) * min;
    }

    private long method2(int globalMin){
        return sum + min + (elephants.size() + 1) * globalMin;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
    public void add(Elephant elephant){
        elephants.add(elephant);
        sum += elephant.getMass();
        min = Math.min(elephant.getMass(), min);
    }
}
