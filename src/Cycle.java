import java.util.ArrayList;
import java.util.List;

public class Cycle {

    //  *** Fields ***

    public List<Elephant> elephants;
    public int sum;
    public int min;

    // *** Counstructors ***

    public Cycle(int sum, int min){
        elephants = new ArrayList<>();
        this.sum = sum;
        this.min = min;
    }

    // *** Public methods ***

    public Integer getResult(int globalMin){
        return Math.min(method1(), method2(globalMin));
    }

    // *** Private methods ***

    private Integer method1(){
        return sum + (elephants.size() - 2) * min;
    }

    private Integer method2(int globalMin){
        return sum + min + (elephants.size() + 1) * globalMin;
    }


}
