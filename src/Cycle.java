import java.util.ArrayList;
import java.util.List;

public class Cycle {

    //  *** Fields ***

    private List<Elephant> elephants;
    private long sum;
    private long min;

    // *** Counstructors ***

    public Cycle(long sum, long min){
        elephants = new ArrayList<>();
        this.sum = sum;
        this.min = min;
    }

    // *** Public methods ***

    public long getResult(int globalMin){
        return Math.min(method1(), method2(globalMin));
    }

    public long getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public long getMin() {
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

    // *** Private methods ***

    private long method1(){
        return sum + (elephants.size() - 2) * min;
    }

    private long method2(int globalMin){
        return sum + min + (elephants.size() + 1) * globalMin;
    }


}
