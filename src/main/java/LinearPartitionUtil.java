import java.util.ArrayList;
import java.util.Scanner;

public class LinearPartitionUtil {
    static class LinearPartition {

        static ArrayList<ArrayList<Integer>> run(ArrayList<Integer> seq, int k) {
            ArrayList<ArrayList<Integer>> result = new ArrayList<>();

            if (k <= 0) {
                ArrayList<Integer> partition = new ArrayList<>(seq);
                result.add(partition);
                return result;
            }

            int n = seq.size() - 1;

            if (k > n) {
                for (Integer value : seq) {
                    ArrayList<Integer> partition = new ArrayList<>();
                    partition.add(value);
                    result.add(partition);
                }
                return result;
            }

            int[][] table = build_partition_table(seq, k);
            k = k - 2;

            while (k >= 0) {
                ArrayList<Integer> partition = new ArrayList<>();

                for (int i = table[n - 1][k] + 1; i < n + 1; i++) {
                    partition.add(seq.get(i));
                }

                result.add(0, partition);
                n = table[n - 1][k];
                k = k - 1;
            }

            ArrayList<Integer> partition = new ArrayList<>();

            for (int i = 0; i < n + 1; i++) {
                partition.add(seq.get(i));
            }

            result.add(0, partition);

            return result;
        }

        static int[][] build_partition_table(ArrayList<Integer> seq, int k) {
            int n = seq.size();
            float[][] table = new float[n][k];
            int[][] solution = new int[n - 1][k - 1];

            for (int i = 0; i < n; i++) {
                table[i][0] = seq.get(i) + ((i > 0) ? (table[i - 1][0]) : 0);
            }

            for (int j = 0; j < k; j++) {
                table[0][j] = seq.get(0);
            }

            for (int i = 1; i < n; i++) {
                for (int j = 1; j < k; j++) {
                    table[i][j] = Integer.MAX_VALUE;
                    for (int x = 0; x < i; x++) {
                        float cost = Math.max(table[x][j - 1], table[i][0] - table[x][0]);
                        if (table[i][j] > cost) {
                            table[i][j] = cost;
                            solution[i - 1][j - 1] = x;
                        }
                    }
                }
            }

            return solution;
        }

        static String inputTool(ArrayList<Integer> input, int nrOfDividers) {
            // STEP 1: Filter input by splitting numbers to tenfolds en putting the remainder into a separate array
            ArrayList<Integer> preInput = new ArrayList<>();
            ArrayList<Integer> restInput = new ArrayList<>();

            for (Integer val : input) {
                if (val  >= 10) preInput.add(val - (val % 10));
                if (val % 10 > 0) restInput.add(val % 10);

            }

            // STEP 2 remove dividers for EDGE CASE (4 on 0||size-1 index of array)
            if (nrOfDividers > 0 && restInput.get(0) == 4) {
                restInput.remove(restInput.get(0));
                nrOfDividers--;
            }

            if (nrOfDividers > 0 && restInput.get(restInput.size() - 1) == 4) {
                restInput.remove(restInput.get(restInput.size() - 1));
                nrOfDividers--;
            }

            // STEP 3: EDGE CASE FOR ALGORITHM: I'm doing this since the algorithm views 1 as 0 partitions
            // (one worker takes all the work) so changing that to 2 here.
            nrOfDividers = (nrOfDividers == 1) ? 2 : nrOfDividers;

            // STEP 4: Run partition algorith to do I(1 1 1 1 1 1 1 1) O(1 1 1 1 | 1 1 1 1)
            ArrayList<ArrayList<Integer>> actual = run(restInput, nrOfDividers);

            // STEP 5 Calculate totals with tenfold values
            int sum = 0;
            int temp = 0;

            for (Integer val : preInput) {
                sum += val;
            }

            // STEP 6: Calculate totals with rest values
            for (ArrayList<Integer> list : actual) {
                for (Integer val : list) {
                    temp += val;
                }
                if ((temp % 10) <= 4) {
                    sum += temp - (temp % 10);
                    temp = 0;
                } else {
                    sum += temp + (10 - (temp % 10));
                    temp = 0;
                }
            }
            return String.valueOf(sum);
        }
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
        System.out.print(LinearPartition.inputTool(cost, nrOfDividers));
    }
}
