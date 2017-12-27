import java.util.Scanner;

public class SubstractMaximum {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int sum = 0;

        int nrOfProducts = scan.nextInt();
        int nrOfDividers = scan.nextInt();

        for (int i = 0; i < nrOfProducts; i++) {
            sum += scan.nextInt();
        }

        System.out.print(Util.r(sum - (nrOfDividers + 1) * 4));
    }
}
