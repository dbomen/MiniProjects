public class longToIp {
    public static String longToIP(long ip) {
        String binary = Long.toBinaryString(ip);
        binary = String.format("%32s", binary).replace(' ', '0');

        int[] octets = new int[4];
        int currentIndex = 0;

        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1')  octets[currentIndex] += (int) Math.pow(2, ((currentIndex + 1) * 8) - (i + 1));
            if ((i + 1) % 8 == 0)         currentIndex++;
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < octets.length; i++) {
            out.append(String.format("%d.", octets[i]));
        }
        out.deleteCharAt(out.length() - 1);

        return out.toString();
    }
}