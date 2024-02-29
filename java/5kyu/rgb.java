import java.util.*;

public class rgb {
    static List<String> hexTable = List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F");

    public static String rgb(int r, int g, int b) {
        return String.format("%02X%02X%02X", getInRange(r), getInRange(g), getInRange(b));
    }

    public static int getInRange(int n) {
        if (n < 0)   n = 0;
        if (n > 255) n = 255;

        return n;
    }

    // 2nd method if you dont know about the specifier %x or %X:
    // public static String rgb(int r, int g, int b) {
    //     return String.format("%s%s%s", getHex(r), getHex(g), getHex(b));
    // }

    // public static String getHex(int n) {
    //     if (n < 0)   n = 0;
    //     if (n > 255) n = 255;

    //     StringBuilder out = new StringBuilder();
    //     do {
    //         out.append(hexTable.get(n % 16));
    //         n /= 16;
    //     } while (n > 0);
    //     return String.format(" ".repeat(2 - out.length()) + "%s", out.reverse().toString()).replace(' ', '0');
    // }
}