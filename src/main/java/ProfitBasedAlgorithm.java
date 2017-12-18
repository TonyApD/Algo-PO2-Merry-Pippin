import java.util.ArrayList;
import java.util.Scanner;

public class ProfitBasedAlgorithm {

    static String inputTool(ArrayList<Integer> input, int nrOfDividers) {
        // STEP 1: Filter input by splitting numbers to tenfolds en putting the remainder into a separate array
        ArrayList<Integer> preInput = new ArrayList<>();
        ArrayList<Integer> restInput = new ArrayList<>();

        for (Integer val : input) {
            if (val >= 10) {
                preInput.add(val - (val % 10));
            }
            if (val % 10 > 0) restInput.add(val % 10);

        }

        // STEP 5 Calculate totals with tenfold values
        int sum = 0;
        for (Integer val : preInput) {
            sum += val;
        }

        int w = 0; //profit
        int t = 0;
        for (Integer i : restInput) {
            t += i;
            w = t % 10;
            if(w == 4) {
                sum += Util.round(t);
                nrOfDividers --;
            }
        }


        return String.valueOf(sum);
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
