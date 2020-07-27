import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        try {


                    // *** Parsing input ***


            // Scanner scanner = new Scanner(System.in); TODO uncomment before handing in the code.
            Scanner scanner = new Scanner(new File("data/slo1.in")); // TODO to be removed
            int n = Integer.parseInt(scanner.nextLine());
            Elephant[] elephants = new Elephant[n];

            String[] elephantsMassTmp = scanner                 // retrieving masses as Strings
                    .nextLine()
                    .split(" ");
            Integer[] elephantsMass = new Integer[elephantsMassTmp.length];
            for(int i = 0; i<n; i++){
                elephantsMass[i] = Integer.parseInt(elephantsMassTmp[i]);
            }

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
                        start.indexOf(i+1),
                        end.indexOf(i+1));
            }


                  // *** Getting most efficient way of rearranging the order ***


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
                        cycle.add(elephants[x]);
                        x = start.get(elephants[x].getEnd()) - 1;
                    }
                    cycles.add(cycle);
                }
            }
            int globalMin = minMass.orElse(100); // Assumption claims Elephant mass is 100 <= n <= 6500 kg
            int result = 0;
            for(Cycle c : cycles){
                // Getting effort required to rearrange elephants in this cycle
                result += c.getResult(globalMin);
            }
            System.out.println(result);

        }
        catch(IOException e){
            e.printStackTrace();
        }


    }
}
