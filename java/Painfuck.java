import java.util.*;
public class Painfuck {

    public static String interpreter(String code, int iteratrions, int width, int height) {
        int currentCell = 0;
        List<Integer[]> loops = new ArrayList<>();
        for (int i = 0; i < code.length(); i++) {

            if (loops.size() > 0 && loops.get(loops.size() - 1).length == 1) {

                if (code.charAt(i) == ']') {
                    if (loops.get(loops.size() - 1)[0] == 0) {
                        loops.remove(loops.size() - 1);
                    } else {
                        loops.get(loops.size() - 1)[0]--;
                    }
                } else if (code.charAt(i) == '[') {
                    loops.get(loops.size() - 1)[0]++;
                }
                continue;
            }


            if (code.charAt(i) == '*') {
                char value = tape.charAt(currentCell);

                if (value == 49) {
                    value = 48;
                } else {
                    value = 49;
                }

                char valueInString = (char) (value);
                tape = tape.substring(0, currentCell) + valueInString + tape.substring(currentCell + 1);

            } else if (code.charAt(i) == '<') {
                currentCell--;

            } else if (code.charAt(i) == '>') {
                currentCell++;

            } else if (code.charAt(i) == '[') {

                if (tape.charAt(currentCell) == 48) {
                    Integer[] x = {0};
                    loops.add(x);
                } else {
                    Integer[] x = {i, 2004};
                    loops.add(x);
                }

            } else if (code.charAt(i) == ']') {

                if (tape.charAt(currentCell) == 49) {
                    i = loops.get(loops.size() - 1)[0];
                } else {
                    loops.remove(loops.size() - 1);
                }
            }

            if (isOutOfBounds(tape, currentCell)) {
                return tape;
            }
        }
        return tape;
    }
}