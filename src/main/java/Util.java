public abstract class Util {

    public static int round(int input) {
        if (input % 10 <= 4) {
            return input - (input % 10);
        }
        return input + 10 - (input % 10);
    }

    public static int getProfit(int input){
        if (input % 10 <= 4) {
            return input % 10;
        }
        return 0;
    }

    public static boolean givesProfit(int input) {
        return input % 10 <= 4;
    }

}
