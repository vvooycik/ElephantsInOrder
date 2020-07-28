import java.io.File;
import java.io.IOException;
import java.util.*;



public class Main {
    public static void main(String[] args) {
        try {


                    // *** Parsing input ***


            // Scanner scanner = new Scanner(System.in); TODO uncomment before handing in the code.
            Scanner scanner = new Scanner(new File("data/slo10a.in")); // TODO to be removed
            int n = Integer.parseInt(scanner.nextLine());
            Hashtable<Integer, Elephant> elephants = new Hashtable<>();
            Hashtable<Integer, Integer> startOrder = new Hashtable<>();
            Hashtable<Integer, Integer> endOrder = new Hashtable<>();

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

            Object[] start = Arrays                    // order of the elephants at the beginning
                    .stream(scanner
                    .nextLine()
                    .split(" "))
                    .toArray();

            Object[] end = Arrays                      // order of the elephants at the end
                    .stream(scanner
                    .nextLine()
                    .split(" "))
                    .toArray();

            for(int i=0; i<n; i++){
                int valStart = Integer.parseInt((String)start[i]);
                int valEnd = Integer.parseInt((String)end[i]);
                startOrder.put(i, valStart);
                endOrder.put(i, valEnd);
                elephants.put(valEnd, new Elephant(valEnd, elephantsMass[valEnd-1], i));
            }

                  // *** Getting most efficient way of rearranging the order ***


            // dividing permutation into cycles

            List<Cycle> cycles = new ArrayList<>();
            boolean[] visited = new boolean[n];
            Arrays.fill(visited, false);
            for(int i=1; i<=n; i++){
                if(!visited[i-1]){
                    int x = i;
                    Cycle cycle = new Cycle(0, maxMass.orElse(6500));  // Assumption claims Elephant mass is 100 <= n <= 6500 kg
                    while(!visited[x-1]){
                        visited[x-1] = true;
                        cycle.add(elephants.get(x));
                        x = startOrder.get(elephants.get(x).getEnd());
                    }
                    cycles.add(cycle);
                }
            }
            int globalMin = minMass.orElse(100); // Assumption claims Elephant mass is 100 <= n <= 6500 kg
            long result = 0;
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
