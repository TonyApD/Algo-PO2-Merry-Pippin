import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoneySaverApp {

    public static void main(String[] args) {
        List<Integer> groups;
        Scanner scan = new Scanner(System.in);

        int nrOfProducts = scan.nextInt();
        int nrOfDividers = scan.nextInt();

        int[] prizes = new int[nrOfProducts];

        for (int i = 0; i < nrOfProducts; i++) {
            prizes[i] = scan.nextInt();
        }

        groups = searchProfitableGroups(prizes);

        while (groups.size() > nrOfDividers + 1) {
            removeDivider(groups);
        }

        System.out.print(groups.stream().mapToInt(Util::round).sum());
    }

    /**
     * Searches for groups of consecutive products that have a non-decreasing sum of profit
     *
     * @param prizes the array of prizes all products
     * @return the list with profitable groups
     */
    private static List<Integer> searchProfitableGroups(int[] prizes) {
        List<Integer> groups = new ArrayList<>();
        int currentGroupCost = 0;
        for (int i = 0; i < prizes.length; i++) {
            if ((currentGroupCost + prizes[i]) % 10 == 4) {
                groups.add(currentGroupCost + prizes[i]);
                currentGroupCost = 0;
            } else if ((currentGroupCost + prizes[i]) % 10 < 4) {
                if (Util.getProfit(currentGroupCost + prizes[i]) >= Util.getProfit(currentGroupCost)) {
                    currentGroupCost += prizes[i];
                } else {
                    groups.add(currentGroupCost);
                    currentGroupCost = prizes[i];
                }
            } else {
                if (currentGroupCost % 10 < 5 && currentGroupCost % 10 > 0) {
                    groups.add(currentGroupCost);
                    currentGroupCost = prizes[i];
                } else {
                    currentGroupCost += prizes[i];
                }

            }
        }

        //Add last found group to the list
        if (currentGroupCost > 0) {
            groups.add(currentGroupCost);
        }
        return groups;
    }

    /**
     * Removes a divider when too much dividers are placed. First check whether two groups may be merged with same result after rounding.
     * Otherwise removes the divider on the position where it has the least negative effect on the total sum
     *
     * @param groups the found profitable groups of consecutive products
     */
    private static void removeDivider(List<Integer> groups) {
        //First check whether two groups may be merged that the total sum of the groups remains equal
        for (int i = 0; i < groups.size() - 1; i++) { //TODO: Traversing from end to begin let's c5 fail suddenly. Check this
            if (Util.round(groups.get(i) + groups.get(i + 1)) == Util.round(groups.get(i)) + Util.round(groups.get(i + 1))) {
                groups.set(i, groups.get(i) + groups.get(i + 1));
                groups.remove(i + 1);
                return; //TODO: Check why return lets some cases not fail
            }
        }

        //Otherwise check which divider may be removed with the least impact on the total sum
        int removeDividerAfter = 0;
        int currentLeastProfitLoss = (int) Double.MAX_VALUE;
        for (int i = groups.size() - 2; i >= 0; i--) {
            if ((groups.get(i) % 10 + groups.get(i + 1) % 10) < currentLeastProfitLoss) {
                removeDividerAfter = i;
                currentLeastProfitLoss = groups.get(i) % 10 + groups.get(i + 1) % 10;
            }
        }
        if (removeDividerAfter <= groups.size() - 1) {
            groups.set(removeDividerAfter, groups.get(removeDividerAfter) + groups.get(removeDividerAfter + 1));
            groups.remove(removeDividerAfter + 1);
        }
    }
}
