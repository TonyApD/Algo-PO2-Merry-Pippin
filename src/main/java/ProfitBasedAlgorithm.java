import java.util.ArrayList;

public class ProfitBasedAlgorithm {

    private static int sum;

    public static void main(String[] args) {                         // Entry for the program
        Reader s = new Reader();                                     // Start a new reader based on the optimized reader class
        int p = s.nextInt();                                         // Amount of products
        int nDiv = s.nextInt();                                      // Amount of dividers
        ArrayList<Integer> input = new ArrayList<>();                // Fresh ArrayList to capture the products

        for (int i = 0; i < p; i++) {
            input.add(s.nextInt());                                  // Add the price of every product to the ArrayList
        }
        s.close();                                                   // Close the reader when done
        System.out.print(Util.round(optimizeDividers(calculateProfit(calculatePotentialProfit(input)), nDiv))); // execute and print result
    }

    /**
     * Takes the main input with products and optimizes it into a possible profits which get stored into
     * the potentialProfitList. This list has values like [4,4,2,2,3,4,3,2,1,2,1,4,2,1,2,4,3] and
     * is therefore not optimised yet
     *
     * @param input main input for current list of products
     * @return potentialProfitList
     */
    private static ArrayList<Integer> calculatePotentialProfit(ArrayList<Integer> input) {
        sum = 0;                                                     // Reset the profit at the beginning of each run
        int potP = 0;                                                // potP = potential Profit ([1..4] numbers) at a given input
        ArrayList<Integer> potentialProfitList = new ArrayList<>();  // Potential Profit ArrayList to keep track of the potential profit [1..4].
        for (Integer i : input) {                                    // Start walking through the input
            sum += i;                                                // Add the input to the sum
            potP = potP + Util.rem(i);                               // For every value we check what the remainder would be
            if (!potentialProfitList.isEmpty() && Util.rem(potP) + potentialProfitList.get(potentialProfitList.size() - 1) <= 4) {
                potentialProfitList.set(potentialProfitList.size() - 1, Util.rem(potP) + // Look if we can make a four combined of the next following numbers
                        potentialProfitList.get(potentialProfitList.size() - 1)); // add the remainder to the list, see documentation for further explaining on this
                potP = 0;                                            // If we did add potential Profit to the list, reset it for the next iteration.
            } else if (Util.pFor(potP)) {                            // Otherwise, if there is a profit
                potentialProfitList.add(Util.rem(potP));             // We add the profit to the list.
                potP = 0;                                            // And reset the profit again.
            }
        }
        return potentialProfitList;
    }

    /**
     * Takes the potentialProfitList and transforms that before placing dividers
     * The list will now look more like [4,4,4,4,2,2,3,3,2,1,2,1,4,2,1,2,3] and
     *
     * @param profitL main input for current list of products
     * @return potentialProfitList
     */
    private static ArrayList<Integer> calculateProfit(ArrayList<Integer> profitL) {
        ArrayList<Integer> optimizedProfitList = new ArrayList<>();  // Fresh new list to optimized (IE place the brackets)
        for (int i = 0; i < profitL.size(); i++) {                   // We walk to the potential profit list
            if (i < profitL.size() - 1) {                            // As long as we did not hit the end of the list
                if (i != 0 && profitL.get(i) == 4 && profitL.get(i + 1) == 4) { // If the current val is 4, we check if the nest value is 4 as well
                    optimizedProfitList.add(0, profitL.get(i)); // If this is the case, we add it to the front of the list.
                } else {                                             // Else we add it in order to the list
                    optimizedProfitList.add(profitL.get(i));         // We add it to the list
                }
            } else {
                optimizedProfitList.add(profitL.get(i));             // Same at this point, we add the value anyway
            }
        }
        return optimizedProfitList;
    }


    /**
     * Now are finally at the removing step. At this point we subtract profit (IE place dividers) as long as we have
     * them. When we are out of dividers, we stop the loop and return.
     *
     * @param optimizedProfitList this is the optimized list that we will use to subtract from the sum
     * @param nrOfDividers        number of dividers we have at our disposal
     * @return returns the resulting sum so the main can print it.
     */
    private static int optimizeDividers(ArrayList<Integer> optimizedProfitList, int nrOfDividers) {
        for (Integer i : optimizedProfitList) {                      // We walk through the list of profits we have
            if (nrOfDividers != 0) {                                 // As long as we have dividers
                sum -= i;                                            // We subtract the profit from the sum
                nrOfDividers--;                                      // Decrease the amount of dividers we have
            } else {                                                 // If we don't have any more dividers
                break;                                               // Break the loop
            }
        }
        return sum;                                                  // Return the value after done.
    }
}
