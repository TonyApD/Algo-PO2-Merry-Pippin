import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BruteForceUtil {

    private static int inputTool(List<Integer> input, int nrOfDividers) {
        int best = (int) Double.MAX_VALUE;
        int sum = 0;

        for (int j = 0; j <= nrOfDividers ; j++) {
            if (input.size() > 1) {
                for (int i = 0; i < input.size(); i++) {
                    //Add the group before the divider to the sum
                    sum = input.subList(0, i + 1).stream().mapToInt(q -> q).sum();
                    //Add the group after the divider to the sum
                    sum += inputTool(input.subList(i + 1, input.size()), j);
                    if (sum < best) {
                        best = sum;
                    }
                }
            } else {
                sum += input.stream().mapToInt(q -> q).sum();
            }
            if (sum < best) {
                best = sum;
            }
        }


        return Util.round(best);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int nrOfProducts = scan.nextInt();
        int nrOfDividers = scan.nextInt();

        ArrayList<Integer> cost = new ArrayList<>();

        for (int i = 0; i < nrOfProducts; i++) {
            cost.add(i, scan.nextInt());
        }
        System.out.print(BruteForceUtil.inputTool(cost, nrOfDividers));
    }
}
