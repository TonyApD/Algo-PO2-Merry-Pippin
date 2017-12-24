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
     * Searches for groups of consecutive products that generate profit
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
                if (Util.getProfit(currentGroupCost + prizes[i]) > Util.getProfit(currentGroupCost)) {
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
     * Removes a divider when too much dividers are placed. Removes the divider on the position where it has the least
     * negative effect on the total sum of the groups
     *
     * @param groups the found profitable groups of consecutive products
     */
    private static void removeDivider(List<Integer> groups) {
        int removeDividerAfter = 0;
        int currentLeastProfitLoss = -1;
        for (int i = 0; i < groups.size() - 1; i++) {
            if (Util.getProfit((groups.get(i) + groups.get(i + 1))) > currentLeastProfitLoss) {
                removeDividerAfter = i;
                currentLeastProfitLoss = Util.getProfit(groups.get(i)) + Util.getProfit(groups.get(i + 1));
            }
        }
        if (removeDividerAfter < groups.size() - 1) {
            groups.set(removeDividerAfter, groups.get(removeDividerAfter) + groups.get(removeDividerAfter + 1));
            groups.remove(removeDividerAfter + 1);
        }
    }
}
