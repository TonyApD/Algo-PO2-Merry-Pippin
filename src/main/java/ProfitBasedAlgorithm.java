import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.DataInputStream;
import java.util.Collections;
import java.util.Scanner;

public class ProfitBasedAlgorithm {

    private static int sum;

    public static void main(String[] args) {
        Reader s = new Reader();
        ArrayList<Integer> input = new ArrayList<>();
        int p = s.nextInt();
        int n = s.nextInt();

        for (int i = 0; i < p; i++) {
            input.add(s.nextInt());
        }
        s.close();
        System.out.print(Util.round(optimizeDividers(optimizePrices(input), n)));
    }

    /**
     * Takes the main input with products and optimizes it into a possible profits which get stored into the profitList
     *
     * @param input main input for current list of products
     * @return profitList with maximal amount that could be subtracted for that given input
     */
    private static ArrayList<Integer> optimizePrices(ArrayList<Integer> input) {
        int p = 0; // p for profit, we use this to keep track of the profit at a given input
        sum = 0;
        ArrayList<Integer> profitList = new ArrayList<>();

        for (Integer i : input) {
            sum += i;
            p = p + (i % 10);
            if (!profitList.isEmpty() && (p % 10) + profitList.get(profitList.size() - 1) <= 4) {
                profitList.set(profitList.size() - 1, (p % 10) + profitList.get(profitList.size() - 1));
                p = 0;
            } else if (p % 10 > 0 && p % 10 < 5) {
                profitList.add(p % 10);
                p = 0;
            }
        }

        ArrayList<Integer> optimizorProfitList = new ArrayList<>();
        for (int i = 0; i < profitList.size(); i++) {
            if (i < profitList.size() - 1) {
                if (i != 0 && profitList.get(i) == 4 && profitList.get(i + 1) == 4) {
                    optimizorProfitList.add(0, profitList.get(i));
                } else {
                    optimizorProfitList.add(profitList.get(i));
                }
            } else {
                optimizorProfitList.add(profitList.get(i));
            }
        }
        return optimizorProfitList;
    }


    /**
     * Given that the profitlist is build up, we now look at placing dividers.
     * Right now the list is sorted from high to low, then we subtract those top values as long as we have dividers
     *
     * @param priceOptimizedList list of possible subtractions based on the optimizePrices function
     * @param nrOfDividers       needed to calculate the max sum to be subtracted.
     * @return the return value consists of the optimized price that will get payed after 'placing' the dividers
     */
    private static int optimizeDividers(ArrayList<Integer> priceOptimizedList, int nrOfDividers) {
        for (Integer i : priceOptimizedList) {
            if (nrOfDividers != 0) {
                sum -= i;
                nrOfDividers--;
            } else {
                break;
            }
        }
        return sum;
    }

}
