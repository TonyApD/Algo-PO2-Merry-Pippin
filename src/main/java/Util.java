public abstract class Util {

    /**
     * Returns the rounded value
     * IE [0..4] -> 0
     * And [5..10] -> 10
     * @param input input to round down
     * @return returns the rounded value of that number
     */
    public static int round(int input) {
        if (input % 10 <= 4) {
            return input - (input % 10);
        }
        return input + 10 - (input % 10);
    }

    /**
     * If the remainder of the profit divided by ten is <= 4
     * @param input the input to calculate profit on.
     * @return profit modulo 10 if there is profit, returns zero otherwise
     */
    public static int getP(int input) {
        if (input % 10 <= 4) {
            return input % 10;
        }
        return 0;
    }

    /**
     * Boolean to check if there is profit or not
     * @param input the value to compute the boolean on
     * @return true if there is profit
     */
    public static boolean pFor(int input) {
        return input % 10 <= 4;
    }

    /**
     * Returns the remainder mod 10
     * @param input value to calculate remainder of
     * @return returns the remainder
     */
    public static int rem(int input) {
        return (input % 10);
    }
}
