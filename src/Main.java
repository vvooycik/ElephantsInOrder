import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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
            String[] elephantsMass = scanner.nextLine().split(" ");

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
                        Integer.parseInt(elephantsMass[i]),
                        start.indexOf(i),
                        end.indexOf(i));
            }

            /*
                    **** Rearranging the order ****
            */

            // dividing permutation into cycles

            List<List<Elephant>> cycles = new ArrayList<>();
            boolean[] visited = new boolean[n];
            Arrays.fill(visited, false);
            for(int i=0; i<n; i++){
                if(!visited[i]){
                    int x = i;
                    List<Elephant> cycle = new ArrayList<>();
                    while(!visited[x]){
                        visited[x] = true;
                        cycle.add(elephants[x]);
                        x = start.get(end.indexOf(elephants[x]));
                    }
                    cycles.add(cycle);
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }


    }
}
