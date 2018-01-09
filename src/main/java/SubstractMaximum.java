public class SubstractMaximum {

    public static void main(String[] args) {
        Reader s = new Reader();
        int sum = 0;

        int nrOfProducts = s.nextInt();
        int nrOfDividers = s.nextInt();

        for (int i = 0; i < nrOfProducts; i++) {
            sum += s.nextInt();
        }

        System.out.print(Util.round(Math.max(0, sum - (nrOfDividers + 1) * 4)));
    }
}
