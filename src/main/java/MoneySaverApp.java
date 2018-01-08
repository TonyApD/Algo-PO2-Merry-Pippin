import java.util.ArrayList;
import java.util.List;

public class MoneySaverApp {

    public static void main(String[] args) {
        List<Integer> groups;
        Reader s = new Reader();
        List<Integer> prizes = new ArrayList<>();
        int p = s.nextInt();
        int n = s.nextInt();

        for (int i = 0; i < p; i++) {
            prizes.add(s.nextInt());
        }
        s.close();

        groups = searchProfitableGroups(prizes);

        while (groups.size() > n + 1) {
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
    private static List<Integer> searchProfitableGroups(List<Integer> prizes) {
        List<Integer> groups = new ArrayList<>();
        int currentGroupCost = 0;
        for (int i = 0; i < prizes.size(); i++) {
            if ((currentGroupCost + prizes.get(i)) % 10 == 4) {
                groups.add(currentGroupCost + prizes.get(i));
                currentGroupCost = 0;
            } else if ((currentGroupCost + prizes.get(i)) % 10 < 4) {
                if (Util.getP(currentGroupCost + prizes.get(i)) >= Util.getP(currentGroupCost)) {
                    currentGroupCost += prizes.get(i);
                } else {
                    groups.add(currentGroupCost);
                    currentGroupCost = prizes.get(i);
                }
            } else {
                if (currentGroupCost % 10 < 5 && currentGroupCost % 10 > 0) {
                    groups.add(currentGroupCost);
                    currentGroupCost = prizes.get(i);
                } else {
                    currentGroupCost += prizes.get(i);
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
        for (int i = groups.size() - 2; i >= 0; i--) {
            if (Util.round(groups.get(i) + groups.get(i + 1)) == Util.round(groups.get(i)) + Util.round(groups.get(i + 1))) {
                groups.set(i, groups.get(i) + groups.get(i + 1));
                groups.remove(i + 1);
                return; //Divider removed, so don't execute next loop
            }
        }

        //Otherwise check which divider may be removed with the least impact on the total sum
        //Only combinations of 1-4, 2-3, 2-4, 3-3, 3-4 and 4-4 may appear since the loop above combines all other combinations.
        //In this order you want to also combine them if no consecutive combinations are possible anymore?
        // Maybe try some pattern matching? For example: 6 * 4 = 24 is 4 profit. Think that something like this could be a fix.
        // Or randomized check x elements and couple the pair of least impact. Not very consistent?
        int removeDividerAfter = 0;
        int currentLeastProfitLoss = (int) Double.MAX_VALUE;
        for (int i = 0; i < groups.size() - 1; i++) {
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
