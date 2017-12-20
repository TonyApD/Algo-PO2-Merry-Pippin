import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BruteForceUtil {

    private static String inputTool(ArrayList<Integer> input, int nrOfDividers) {
        int w = 0;
        int sum = 0;
        for(Integer i: input) {
            i += sum;
        }

        return String.valueOf(Util.round(sum));
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
