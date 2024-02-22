import java.util.*;

public class Kata {
    protected final static int UPPER_LOWER_CASE_CONST = 32;

    @SuppressWarnings("unchecked")
    public static String firstNonRepeatingLetter(String maze) {
        int index = 0;
        Map<Integer, int[]> mapa = new HashMap<>();
        for (int i = 0; i < maze.length(); i++) {
            int asciiChar = maze.charAt(i);
            if (asciiChar >= 65 && asciiChar <= 90 && (mapa.containsKey(asciiChar + UPPER_LOWER_CASE_CONST))) { // check for lower case letter
                int newValue = mapa.get(asciiChar + UPPER_LOWER_CASE_CONST)[0] + 1;
                mapa.put(asciiChar + UPPER_LOWER_CASE_CONST, new int[]{newValue, mapa.get(asciiChar + UPPER_LOWER_CASE_CONST)[1]});
            } else if (asciiChar >= 97 && asciiChar <= 122 && (mapa.containsKey(asciiChar - UPPER_LOWER_CASE_CONST))) { // check for upper case letter
                int newValue = mapa.get(asciiChar - UPPER_LOWER_CASE_CONST)[0] + 1;
                mapa.put(asciiChar - UPPER_LOWER_CASE_CONST, new int[]{newValue, mapa.get(asciiChar - UPPER_LOWER_CASE_CONST)[1]});
            } else if (mapa.containsKey(asciiChar)) { // if in map
                    int newValue = mapa.get(asciiChar)[0] + 1;
                    mapa.put(asciiChar, new int[]{newValue, mapa.get(asciiChar)[1]});
            } else { // if not in map
                mapa.put(asciiChar, new int[]{1, index++});
            }
        }

        // add elements in a sorted way
        @SuppressWarnings({ "surpressWarning", "rawtypes" })
        List<String> possibleTargets = new ArrayList<>((Collection) mapa.keySet());
        Collections.fill(possibleTargets, null);

        for (Integer key : mapa.keySet()) {
            if (mapa.get(key)[0] == 1) {
                possibleTargets.set(mapa.get(key)[1], String.valueOf((char) (key.intValue())));
            }
        }

        // find first element
        String target = "";
        for (String s : possibleTargets) {
            if (!(s == null)) {
                target = s;
                break;
            }
        }

        return target;
    }
}