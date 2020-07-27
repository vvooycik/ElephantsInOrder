import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {

            /*
                    **** Parsing input ****
            */

            // Scanner scanner = new Scanner(System.in); TODO uncomment before handing in the code.
            Scanner scanner = new Scanner(new File("data/slo1.in")); // TODO to be removed
            int n = Integer.parseInt(scanner.nextLine());
            Elephant[] elephants = new Elephant[n];
            int[] permutation = new int[n];
            String[] elephantsMassTmp = scanner                 // retrieving masses as Strings
                    .nextLine()
                    .split(" ");
            Integer[] elephantsMass = (Integer[]) Arrays        // Strings to Integers
                    .stream(elephantsMassTmp)
                    .map(Integer::parseInt)
                    .toArray();

            Optional<Integer> maxMass = Arrays
                    .stream(elephantsMass)
                    .max(Integer::compareTo);
            Optional<Integer> minMass = Arrays
                    .stream(elephantsMass)
                    .min(Integer::compareTo);

            List<Integer> start = Arrays                    // order of the elephants at the beginning
                    .stream(scanner
                    .nextLine()
                    .split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            List<Integer> end = Arrays                      // order of the elephants at the end
                    .stream(scanner
                    .nextLine()
                    .split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());


            for(int i=0; i<n; i++) {                        // list of all elephants with all attributes
                elephants[i] = new Elephant(
                        i,
                        elephantsMass[i],
                        start.indexOf(i),
                        end.indexOf(i));
            }

            /*
                    **** Rearranging the order ****
            */

            // dividing permutation into cycles

            List<Cycle> cycles = new ArrayList<>();
            boolean[] visited = new boolean[n];
            Arrays.fill(visited, false);
            for(int i=0; i<n; i++){
                if(!visited[i]){
                    int x = i;
                    Cycle cycle = new Cycle(0, maxMass.orElse(6500));  // Assumption claims Elephant mass is 100 <= n <= 6500 kg
                    while(!visited[x]){
                        visited[x] = true;
                        cycle.elephants.add(elephants[x]);
                        x = start.get(end.indexOf(elephants[x].index));
                    }
                    cycles.add(cycle);
                }
            }
            int min = minMass.orElse(100); // Assumption claims Elephant mass is 100 <= n <= 6500 kg
            for(Cycle c : cycles){
                for(Elephant e : c.elephants){
                    c.sum += e.getMass();
                    c.min = Math.min(e.getMass(), c.min);
                }
            }


        }
        catch(IOException e){
            e.printStackTrace();
        }


    }
}
