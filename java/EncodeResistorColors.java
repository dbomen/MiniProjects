import java.util.*;

public class EncodeResistorColors {
    public static final int ASCII_NUMBER_CONSTANT = 48;

    public static String encodeResistorColors(String ohmsString) {
        List<String> colorCodes = Arrays.asList("black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "gray", "white");

        String[] ohms = ohmsString.split(" ");

        int[] stevilke = new int[2];
        int dolzina = 0;
        int dotKonst = 0;
        for (int i = 0; i < ohms[0].length(); i++) {
            if (!(Integer.valueOf(ohms[0].charAt(i)) - ASCII_NUMBER_CONSTANT > 9 || Integer.valueOf(ohms[0].charAt(i)) - ASCII_NUMBER_CONSTANT == 0 || ohms[0].charAt(i) == '.')) {
                stevilke[i - dotKonst] = Integer.valueOf(ohms[0].charAt(i)) - ASCII_NUMBER_CONSTANT;
            } else {
                if (Integer.valueOf(ohms[0].charAt(i)) - ASCII_NUMBER_CONSTANT == 0) {
                    dolzina = ohms[0].length() - 2;
                } else if (ohms[0].charAt(i) == 'k') {
                    dolzina = ohms[0].length() - dotKonst * 2;
                } else if (ohms[0].charAt(i) == 'M') {
                    dolzina = ohms[0].length() + 3 - dotKonst * 2;
                } else if (ohms[0].charAt(i) == '.') {
                    dotKonst = 1;
                }
            }
        }

        return String.format("%s %s %s %s", colorCodes.get(stevilke[0]), colorCodes.get(stevilke[1]), colorCodes.get(dolzina), "gold");

    }
}
