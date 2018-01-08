public abstract class Util {

    public static int round(int input) {
        if (input % 10 <= 4) {
            return input - (input % 10);
        }
        return input + 10 - (input % 10);
    }

    public static int getP(int input) {
        if (input % 10 <= 4) {
            return input % 10;
        }
        return 0;
    }

    public static boolean pFor(int input) {
        return input % 10 <= 4;
    }

    public static int rem(int input) {
        return (input % 10);
    }
}
