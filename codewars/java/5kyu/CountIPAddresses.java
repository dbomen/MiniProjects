public class CountIPAddresses {

    public static long ipsBetween(String start, String end) {
        String[] first = start.split("\\.");
        String[] last = end.split("\\.");
        double[] multiplier = {Math.pow(2, 24), Math.pow(2, 16), Math.pow(2, 8), 1};

        long res = 0;
        for (int i = first.length - 1; i >= 0; i--)  res += (Long.parseLong(last[i]) - Long.parseLong(first[i])) * multiplier[i];
        return res;
    }
}