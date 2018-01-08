import java.util.ArrayList;

public class ProfitBasedAlgorithm {

    private static int sum;

    public static void main(String[] args) {                         // Entry for the program
        Reader s = new Reader();                                     // Start a new reader based on the optimized reader class
        int p = s.nextInt();                                         // Amount of products
        int nDiv = s.nextInt();                                      // Amount of dividers
        ArrayList<Integer> input = new ArrayList<>();                // Fresh ArrayList to capture the products

        for (int i = 0; i < p; i++) {
            input.add(s.nextInt());                                  // Add the products to the ArrayList for use later
        }
        s.close();                                                   // Close the reader when done
        System.out.print(Util.round(optimizeDividers(calculateProfit(calculatePotentialProfit(input)), nDiv))); // Execute the optimizeDividers function and return the result
    }

    /**
     * Takes the main input with products and optimizes it into a possible profits which get stored into the profitList
     *
     * @param input main input for current list of products
     * @return profitList with maximal amount that could be subtracted for that given input
     */
    private static ArrayList<Integer> calculatePotentialProfit(ArrayList<Integer> input) {
        sum = 0;                                                     // Reset the profit at the beginning of each run
        int potP = 0;                                                // potP = potential Profit ([1..4] numbers) at a given input
        ArrayList<Integer> potentialProfitList = new ArrayList<>();  // Potential Profit ArrayList to keep track of the potential profit [1..4].
        for (Integer i : input) {                                    // Start walking through the input
            sum += i;                                                // Add the input to the sum
            potP = potP + Util.rem(i);                               // For every value we check what the remainder would be
            if (!potentialProfitList.isEmpty() && Util.rem(potP) +
                    potentialProfitList.get(potentialProfitList.size() - 1) <= 4) {
                potentialProfitList.set(potentialProfitList.size() - 1, Util.rem(potP) +
                        potentialProfitList.get(potentialProfitList.size() - 1)); // add the remainder to the list, see documentation for further explaining on this
                potP = 0;                                            // If we did add potential Profit to the list, reset it for the next iteration.
            } else if (Util.pFor(potP)) {                            // Otherwise, if there is a profit
                potentialProfitList.add(Util.rem(potP));             // We add the profit to the list.
                potP = 0;                                            // And reset the profit again.
            }
        }
        return potentialProfitList;
    }

    private static ArrayList<Integer> calculateProfit(ArrayList<Integer> profitL) {
        ArrayList<Integer> optimizedProfitL = new ArrayList<>();    // Fresh new list to optimized (IE place the brackets)
        for (int i = 0; i < profitL.size(); i++) {                  // We walk to the potential profit list
            if (i < profitL.size() - 1) {                           // As long as we did not hit the end of the list
                if (i != 0 && profitL.get(i) == 4 && profitL.get(i + 1) == 4) { // If the current val is 4, we check if the nest value is 4 as well
                    optimizedProfitL.add(0, profitL.get(i)); // If this is the case, we add it to the front of the list.
                } else {                                            // Else we add it in order to the list
                    optimizedProfitL.add(profitL.get(i));           // We add it to the list
                }
            } else {
                optimizedProfitL.add(profitL.get(i));               // Same at this point, we add the value anyway
            }
        }
        return optimizedProfitL;                                    // Return the resulting list
    }


    /**
     * Given that the profitlist is build up, we now look at placing dividers.
     * Right now the list is sorted from high to low, then we subtract those top values as long as we have dividers
     *
     * @param optimizedProfitL list of possible subtractions based on the optimizePrices function
     * @param nrOfDividers     needed to calculate the max sum to be subtracted.
     * @return the return value consists of the optimized price that will get payed after 'placing' the dividers
     */
    private static int optimizeDividers(ArrayList<Integer> optimizedProfitL, int nrOfDividers) {
        for (Integer i : optimizedProfitL) {                        // We walk through the list of profits we have
            if (nrOfDividers != 0) {                                // As long as we have dividers
                sum -= i;                                           // We subtract the profit from the sum
                nrOfDividers--;                                     // Decrease the amount of dividers we have
            } else {                                                // If we don't have any more dividers
                break;                                              // Break the loop
            }
        }
        return sum;                                                 // Return the value after done.
    }

}
