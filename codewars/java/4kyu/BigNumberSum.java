public class BigNumberSum {
    public static String add(String a, String b) {
        StringBuilder out = new StringBuilder();
        StringBuilder sbA = new StringBuilder(a); fixLeadingZeros(sbA); sbA.reverse();
        StringBuilder sbB = new StringBuilder(b); fixLeadingZeros(sbB); sbB.reverse();

        int size = (sbA.length() > sbB.length()) ? sbA.length() : sbB.length();

        boolean carry = false;
        for (int i = 0; i < size; i++) {
            int cA = (i < sbA.length()) ? Integer.valueOf(sbA.charAt(i)) - '0' : 0;
            int cB = (i < sbB.length()) ? Integer.valueOf(sbB.charAt(i)) - '0' : 0;
            int sum = cA + cB;

            if (carry) {
                sum++;
                carry = false;
            }

            if (sum > 9) {
                sum -= 10;
                carry = true;
            }

            out.append(sum);
        }
        if (carry)  out.append('1');

        return out.reverse().toString();
    }

    public static void fixLeadingZeros(StringBuilder num) {
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) != '0')  break;
            num.delete(0, 1);
        }
    }
}