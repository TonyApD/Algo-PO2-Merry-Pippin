import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        System.out.print(util.ttt(nrOfDividers));
    }


    public int ttt(int maxLat) {
        int n = line.size();

        ArrayList<Integer> lp2;

        int count = 0;
        for (int i = 0; i < n; i++) {
            count += line.get(i);
        }

        int bestopt = Util.round(count);
        for (int lat = maxLat; lat > 0; lat--) {
            lp2 = new ArrayList<>(lat - 1);
            int opt = tt(0, n, lat - 1, lp2);
            if (opt < bestopt) {
                bestopt = opt;
            }
        }
        return bestopt;
    }

    public int tt(int b, int e, int l, ArrayList<Integer> lp2) {
        ArrayList<Integer> combs = new ArrayList<>(lp2.size());
        int best2 = (int) Double.MAX_VALUE;
        for (int i = b; i < e; i++) {
            i = lp2.indexOf(l);
            if (l > 0) {
                int asdf = tt(i + 1, e, l - 1, lp2);
                if (asdf < best2) {
                    best2 = asdf;
                }
            } else {
                int lpi = lp2.size() - 1;

                combs.clear();
                int comb = 0;
                for (int j = 0; j < line.size(); j++) {
                    comb += line.get(j);
                    if (lpi > 0 && j == lp2.get(lpi)) {
                        combs.add(Util.round(comb));
                        lpi--;
                        comb = 0;
                    }
                }
                combs.add(Util.round(comb));

                int tota = 0;
                for (int c : combs) {
                    tota += c;
                }

                if (tota < best2) {
                    best2 = tota;
                }
            }
        }
        return best2;
    }

    public static void main2(String[] args) {
        Scanner scan = new Scanner(System.in);

        int nrOfProducts = scan.nextInt();
        int nrOfDividers = scan.nextInt();

        ArrayList<Integer> cost = new ArrayList<>();

        for (int i = 0; i < nrOfProducts; i++) {
            cost.add(i, scan.nextInt());
        }
        System.out.print(BruteForceUtil.inputTool(cost, nrOfDividers));
    }

    private static int inputTool(List<Integer> input, int nrOfDividers) {
        int best = (int) Double.MAX_VALUE;

        int sum = 0;
        for (int j = 0; j <= nrOfDividers; j++) {
            if (input.size() > 1 && j > 0) {
                for (int i = 0; i < input.size(); i++) {
                    //Add the group before the divider to the sum
                    sum = Util.round(input.subList(0, i + 1).stream().mapToInt(Integer::intValue).sum());
                    //Add the group after the divider to the sum
                    sum += Util.round(inputTool(input.subList(i + 1, input.size()), j));
                    if (sum < best) {
                        best = sum;
                    }
                }

            } else {
                sum += Util.round(input.stream().mapToInt(Integer::intValue).sum());
            }
        }
        if (sum < best) {
            best = Util.round(sum);
        }


        return Util.round(best);
    }
}
