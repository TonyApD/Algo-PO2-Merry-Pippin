import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.round;

public class BruteForceUtil {

    private ArrayList<Integer> line;

    public BruteForceUtil(ArrayList<Integer> input) {
        this.line = input;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int nrOfProducts = scan.nextInt();
        int nrOfDividers = scan.nextInt();

        ArrayList<Integer> cost = new ArrayList<>();

        for (int i = 0; i < nrOfProducts; i++) {
            cost.add(i, scan.nextInt());
        }
        BruteForceUtil util = new BruteForceUtil(cost);
        System.out.print(util.BruteforceFor(nrOfDividers));
    }

    int BruteforceFor(int maxLat){
        int n = line.size();

        int[] dividerPlacement;

        int count = 0;
        for (Integer aLine : line) {
            count += aLine;
        }

        int bestopt = round(count);
        for (int lat = maxLat; lat>0; lat--) {
            dividerPlacement = new int[lat];
            int opt = BestOptionFor(0, n-1, lat-1, dividerPlacement);
            if (opt < bestopt){
                bestopt = opt;
            }

        }
        return bestopt;
    }

    int BestOptionFor(int begin, int end, int length, int[] dividerPositions) {
        ArrayList<Integer> groupTotals = new ArrayList<>();
        int best = (int) Double.MAX_VALUE;
        // Problem is here, seems to stay iun this list.
        for (int i = begin; i < end; i++) {
            i = dividerPositions.length; // place a divider on this position
            if (length > 0) { // Place more dividers
                int subResult = BestOptionFor(i + 1, end, length - 1, dividerPositions);
                if (subResult < best) {
                    best = subResult;
                }
            } else {
                // DividerPositions contains all divider positions
                // We can now return the length of these combinations

                // 0 means a divider after the first element,
                // 1 means a divider after the second etc.

                for (int dividerPosition : dividerPositions) {
                    System.out.println(dividerPosition);
                }
                System.out.println("For ");

                int dividerPlaceIndex = dividerPositions.length - 1;


                int groupTotal = 0;
                for (int j = 0; j < line.size(); j++) { // for each element in the line
                    groupTotal += line.get(j);// add the element to the total for this group (between each divider)
                    if (dividerPlaceIndex >= 0 && j == dividerPositions[dividerPlaceIndex]) {
                        groupTotals.add(round(groupTotal));
                        System.out.println(groupTotal);
                        dividerPlaceIndex--;
                        groupTotal = 0;
                    }
                }
                groupTotals.add(round(groupTotal));
                System.out.println(groupTotal);

                int total = 0;
                for (Integer gt : groupTotals) {
                    total += gt;
                }

                System.out.println( "=>" + total + '\n');
                if (total < best) {
                    best = total;
                }
            }
        }
        return best;
    }
}
