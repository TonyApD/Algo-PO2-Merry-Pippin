import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BruteForceUtil {

    private static String inputTool(List<Integer> input, int nrOfDividers) {
        int best = (int) Double.MAX_VALUE;
        int sum = 0;

        if (nrOfDividers >= 1) {
            for (int i = 0; i < input.size() - 1; i++) {
                for (int j = i + 1; j < input.size(); j++) {
                    sum = Util.round(input.subList(0, i + 1).stream().mapToInt(q -> q).sum());
                    sum += Util.round(input.subList(i + 1, j).stream().mapToInt(q -> q).sum());
                    sum += Integer.valueOf(inputTool(input.subList(j, input.size()), nrOfDividers - 1));
                    if (sum < best) {
                        best = sum;
                    }
                }
            }
        } else {
            sum += Util.round(input.stream().mapToInt(q -> q).sum());
        }
        if (sum < best) {
            best = sum;
        }


        return String.valueOf(Util.round(best));
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
