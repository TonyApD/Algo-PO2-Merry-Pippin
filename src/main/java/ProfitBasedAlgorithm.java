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

        // Choose which profitlist we will use
        ArrayList<Integer> optimumList;
        if (profitListByNum.stream().mapToInt(Integer::intValue).sum() <
                profitListBySum.stream().mapToInt(Integer::intValue).sum()) {
            optimumList = profitListBySum;
        } else {
            optimumList = profitListByNum;
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
