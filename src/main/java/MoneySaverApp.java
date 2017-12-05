import java.util.Scanner;

public class MoneySaverApp {

    public static void main(String[] args) {
        int nrOfProducts;
        int nrOfDividers;

        Scanner scan = new Scanner(System.in);

        nrOfProducts = scan.nextInt();
        nrOfDividers = scan.nextInt();

        int[] cost = new int[nrOfProducts];

        for (int i = 0; i < nrOfProducts; i++) {
            cost[i] = scan.nextInt();
        }

        int totalCost = 0;
        int usedDividers = 0;
        int currentGroupCost = 0;
        for (int i = 0; i < cost.length; i++) {
            if ((currentGroupCost + cost[i]) % 10 == 4) {
                totalCost += currentGroupCost + cost[i] - 4;
                if (i < cost.length - 1) {
                    usedDividers++;
                }
                currentGroupCost = 0;
            } else if ((currentGroupCost + cost[i]) % 10 < 4) {
                currentGroupCost += cost[i];
            } else {
                if (currentGroupCost % 10 < 5 && currentGroupCost % 10 > 0) {
                    totalCost += currentGroupCost - (currentGroupCost % 10);
                    usedDividers++;
                    currentGroupCost = cost[i];
                } else {
                    currentGroupCost += cost[i];
                }

            }
        }

        if (currentGroupCost > 0) {
            if ((currentGroupCost % 10) <= 4) {
                totalCost += currentGroupCost - (currentGroupCost % 10);
            } else {
                totalCost += currentGroupCost + (10 - (currentGroupCost % 10));
            }
        }
        if (usedDividers > nrOfDividers) {
            System.out.println("Too much dividers used! Used: " + usedDividers + " instead of " + nrOfDividers);
        }

        System.out.print(totalCost);
    }
}
