import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProfitBasedAlgorithm {

    private static String inputTool(ArrayList<Integer> input, int nrOfDividers) {
        int sum = 0;
        int temp = 0;
        ArrayList<Integer> profitList = new ArrayList<>();
        for (Integer i : input) {
            int w;
            temp += i;
            w =+ temp % 10;
            if (w >= 0 && w < 5) {
                profitList.add(w);
            }
        }

        sum += temp;
        Collections.sort(profitList, Collections.reverseOrder());
        for (Integer i : profitList) {
            if (nrOfDividers != 0) {
                sum -= i;
                nrOfDividers--;
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
