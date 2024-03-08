public class GapInPrimes {
    static int cnt = 0;

    public static long[] gap(int g, long m, long n) {
        System.out.println(cnt++);
        boolean foundPrime = false;
        long currentPrime = 0;
        int currentGap = 0;
        for (long i = m; i <= n; i++) {
            boolean primeI = isPrime(i);
            if (foundPrime && primeI) {
                if (currentGap == g)  return new long[]{currentPrime, i};
                currentPrime = i;
                currentGap = 0;
            }

            if (primeI) {
                foundPrime = true;
                currentPrime = i;
            }

            if (foundPrime) currentGap++;
        }

        return null;
    }

    public static boolean isPrime(long n) {
        for (long i = (long) Math.sqrt(n); i > 1; i--) {
            if (n % i == 0)  return false;
        }
        return true;
    }
}