public class Dec2Fact {
    
    public static String dec2FactString(long nb) {
        // find max fac for num
        long fac;
        for (fac = 0; nb > toFac(fac); fac++); fac--;
        if (nb == toFac(fac + 1))              fac++;

        StringBuilder out = new StringBuilder();

        for (long i = fac; i > 0; i--) {
            long j;
            for (j = 1; nb >= toFac(i) * j; j++); j--;

            if (j > 9)  out.append(Character.valueOf((char) (64 + j - 9)));
            else        out.append(j);

            nb -= toFac(i) * j;
        }

        out.append(0);
        return out.toString();
    }

    public static long factString2Dec(String str) {
        StringBuilder sb = new StringBuilder(str);
        long out = 0;
        for (int i = 0; sb.length() > 0; i++) {
            out += Long.parseLong(String.valueOf(sb.charAt(sb.length() - 1)), 16) * toFac(i);
            sb.deleteCharAt(sb.length() - 1);
        }

        return out;
    }


    public static long toFac(long n) {
        if (n <= 0)  return 0;

        long out = 1;
        for (long i = n; i > 1; i--)  out *= i;

        return out;
    }

    public static void main(String[] args) {
        System.out.println(dec2FactString(720));
    }
}