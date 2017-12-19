import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProfitBasedAlgorithm {

    private static String inputTool(ArrayList<Integer> input, int nrOfDividers) {
        int sum = 0;
        int temp = 0;
        int maxPossibleProfit = (4 + (nrOfDividers * 4));
        ArrayList<Integer> profitListBySum = new ArrayList<>();
        ArrayList<Integer> profitListByNum = new ArrayList<>();
        ArrayList<Integer> profitListByUnknown = new ArrayList<>();


        // By sum calculations
        for (Integer i : input) {
            int w;
            temp += i;
            w = +temp % 10;
            if (w > 0 && w < 5) {
                profitListBySum.add(w);
            }
        }

        // By num calculations
        for (Integer i : input) {
            int n;
            n = +i % 10;
            if (n > 0 && n < 5) {
                profitListByNum.add(n);
            }
        }

        temp = 0;
        int w = 0;
        // By unknown algorithm
        for (int i = 0; i < input.size(); i++) {

            //temp += input.get(i);
            w = w + (input.get(i) % 10);
            if (!profitListByUnknown.isEmpty() &&
                    w + profitListByUnknown.get(profitListByUnknown.size() - 1)  <= 4) {
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
        // Choose which profitlist we will use
        ArrayList<Integer> optimumList;
        if (profitListByUnknown.stream().mapToInt(Integer::intValue).sum() <
                profitListBySum.stream().mapToInt(Integer::intValue).sum()) {
            optimumList = profitListBySum;
        } else {
            optimumList = profitListByUnknown;
        }


        int totalSubtracted = 0;
        sum += temp;
        Collections.sort(optimumList, Collections.reverseOrder());
        for (Integer i : optimumList) {
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
