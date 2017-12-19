import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProfitBasedAlgorithm {

    private static String inputTool(ArrayList<Integer> input, int nrOfDividers) {
        int sum = 0;
        int temp = 0;
        int maxPossibleProfit = (4 + (nrOfDividers * 4));

        ArrayList<Integer> profitListByUnknown = new ArrayList<>();

        int w = 0;
        // By unknown algorithm
        for (Integer i : input) {

            //temp += input.get(i);
            w = w + (i % 10);
            if (!profitListByUnknown.isEmpty() &&
                    w + profitListByUnknown.get(profitListByUnknown.size() - 1) <= 4) {
                profitListByUnknown.set(profitListByUnknown.size() - 1, w + profitListByUnknown.get(profitListByUnknown.size() - 1));
                w = 0;
            } else if (w % 10 > 0 && w % 10 < 5) {
                profitListByUnknown.add(0); // balkje
                profitListByUnknown.add(w % 10);
                w = 0;
            }
        }

        for (Integer i : input) {
            temp += i;
        }

        int totalSubtracted = 0;
        sum += temp;
        profitListByUnknown.sort(Collections.reverseOrder());
        for (Integer i : profitListByUnknown) {
            if (nrOfDividers != 0) {
                if (totalSubtracted != maxPossibleProfit) {
                    sum -= i;
                    totalSubtracted += i;
                    nrOfDividers--;
                }
            } else {
                break;
            }
        }
        return String.valueOf(Util.round(sum));
    }


    public static void main(String[] args) {
        int nrOfProducts;
        int nrOfDividers;

        Scanner scan = new Scanner(System.in);

        nrOfProducts = scan.nextInt();
        nrOfDividers = scan.nextInt();

        ArrayList<Integer> cost = new ArrayList<>();

        for (int i = 0; i < nrOfProducts; i++) {
            cost.add(i, scan.nextInt());
        }
        System.out.print(ProfitBasedAlgorithm.inputTool(cost, nrOfDividers));
    }
}
