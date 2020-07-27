import java.util.ArrayList;
import java.util.List;

public class Cycle {

     public List<Elephant> elephants;
     public int sum;
     public int min;

     public Cycle(int sum, int min){
         elephants = new ArrayList<>();
         this.sum = sum;
         this.min = min;
     }
}
