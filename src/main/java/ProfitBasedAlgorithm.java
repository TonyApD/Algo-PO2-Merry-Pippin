import java.util.ArrayList;

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
        System.out.print(Util.r(optimizeDividers(optimizePrices(input), n)));
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
        ArrayList<Integer> profitL = new ArrayList<>();

        for (Integer i : input) {
            sum += i;
            p = p + Util.rM(i);
            if (!profitL.isEmpty() && Util.rM(p) + profitL.get(profitL.size() - 1) <= 4) {
                profitL.set(profitL.size() - 1, Util.rM(p) + profitL.get(profitL.size() - 1));
                p = 0;
            } else if (Util.giveP(p)) {
                profitL.add(Util.rM(p));
                p = 0;
            }
        }

        ArrayList<Integer> optimizedProfitL = new ArrayList<>();
        for (int i = 0; i < profitL.size(); i++) {
            if (i < profitL.size() - 1) {
                if (i != 0 && profitL.get(i) == 4 && profitL.get(i + 1) == 4) {
                    optimizedProfitL.add(0, profitL.get(i));
                } else {
                    optimizedProfitL.add(profitL.get(i));
                }
            } else {
                optimizedProfitL.add(profitL.get(i));
            }
        }
        return optimizedProfitL;
    }


    /**
     * Given that the profitlist is build up, we now look at placing dividers.
     * Right now the list is sorted from high to low, then we subtract those top values as long as we have dividers
     *
     * @param optimizedProfitL list of possible subtractions based on the optimizePrices function
     * @param nrOfDividers       needed to calculate the max sum to be subtracted.
     * @return the return value consists of the optimized price that will get payed after 'placing' the dividers
     */
    private static int optimizeDividers(ArrayList<Integer> optimizedProfitL, int nrOfDividers) {
        for (Integer i : optimizedProfitL) {
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
