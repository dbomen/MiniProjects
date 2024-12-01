import java.util.*;

public class RomanNumerals {

    static Map<Integer, String> mapa1 = Map.of(1, "I", 4, "IV", 5, "V", 9, "IX");
    static Map<Integer, String> mapa2 = Map.of(1, "X", 4, "XL", 5, "L", 9, "XC");
    static Map<Integer, String> mapa3 = Map.of(1, "C", 4, "CD", 5, "D", 9, "CM");
    static Map<Integer, String> mapa4 = Map.of(1, "M");
    static List<Map<Integer, String>> help = Arrays.asList(mapa1, mapa2, mapa3, mapa4);

    public static String toRoman(int n) {

        int[] stevke = new int[Integer.valueOf(n).toString().length()];
        for (int i = 0; n >= 10; i++) {
            stevke[i] = n % 10;
            n /= 10;
        }
        stevke[stevke.length - 1] = n;

        String[] stevkeToRoman = new String[stevke.length];
        for (int i = stevke.length - 1; i >= 0; i--) {
            if (stevke[i] == 0) {
                stevkeToRoman[i] = "";
                continue;
            }
            int cntOfStevka = 0;
            int numTemp = stevke[i];
            while (!help.get(i).containsKey(numTemp)) {
                cntOfStevka++;
                numTemp--;
            }
            String addOn = help.get(i).get(1);
            stevkeToRoman[i] = help.get(i).get(numTemp) + addOn.repeat(cntOfStevka);
        }

        String fullString = "";
        for (int i = stevkeToRoman.length - 1; i >= 0; i--) {
            fullString += stevkeToRoman[i];
        }

        return fullString;
    }

    public static int fromRoman(String romanNumeral) {

        // Chesse strat...
        // but works efficently because of the small input range: 1 - 3999
        // 17ms, for number 3999 (most amount of loops: 2999)

        // Finds the map in which the first symbol is located
        String firstSymbol = String.valueOf(romanNumeral.charAt(0));
        int indexMape = 0;
        for (Map<Integer, String> mapa : help) {
            if (mapa.containsValue(firstSymbol)) {
                indexMape = help.indexOf(mapa);
            }
        }

        // if the first symbol is part of the first map, we start with the number 1, the second map, we start with the number 10, third (100) and last (1000)
        // we add 1 to the number until we get the right number
        // I think it would also work (be efficient enough) if you were to always start with number 1, because of the small input range
        int num = 1 * (int) Math.pow(10, indexMape - 1);
        String numInRoman = toRoman(num);
        while (!romanNumeral.equals(numInRoman)) {
            num++;
            numInRoman = toRoman(num);
        }

        return num;
    }
}