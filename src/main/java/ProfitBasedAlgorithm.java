import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProfitBasedAlgorithm {

    private static String inputTool(ArrayList<Integer> input, int nrOfDividers) {
        int w = 0;
        int sum = 0;
        ArrayList<Integer> profitList = new ArrayList<>();

        for (Integer i : input) {
            sum += i;
            w = w + (i % 10);
            if (!profitList.isEmpty() && (w % 10) + profitList.get(profitList.size() - 1) <= 4) {
                profitList.set(profitList.size() - 1, (w % 10) + profitList.get(profitList.size() - 1));
                w = 0;
            } else if (w % 10 > 0 && w % 10 < 5) {
                profitList.add(w % 10);
                w = 0;
            }
        }

        profitList.sort(Collections.reverseOrder());
        for (Integer i : profitList) {
            if (nrOfDividers != 0) {
                sum -= i;
                nrOfDividers--;
            } else {
                break;
            }
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
        System.out.print(ProfitBasedAlgorithm.inputTool(cost, nrOfDividers));
    }
}
