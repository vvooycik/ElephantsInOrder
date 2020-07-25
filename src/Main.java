import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            // Scanner scanner = new Scanner(System.in);
            Scanner scanner = new Scanner(new File("data/slo1.in"));
            int n = Integer.parseInt(scanner.nextLine());
            Elephant[] elephants = new Elephant[n];
            String[] elephantsMass = scanner.nextLine().split(" ");
            for(int i=0; i<n; i++){
                elephants[i] = new Elephant(i, Integer.parseInt(elephantsMass[i]));
            }
            List<Integer> start = Arrays
                    .stream(scanner
                    .nextLine()
                    .split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            List<Integer> end = Arrays
                    .stream(scanner
                    .nextLine()
                    .split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
