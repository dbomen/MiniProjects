import java.util.*;

public class DoubleLinear {

    public static int dblLinear(int n) {
        Set<Integer> sequence = new HashSet<>(List.of(1));
        List<Integer> novNumbersRunah = new ArrayList<>(recursion(1, n));
        List<Integer> novNumbersAddah = new ArrayList<>();
        while (sequence.size() <= n) {
            for (int i = 0; i < novNumbersRunah.size(); i++) {
                sequence.add(novNumbersRunah.get(i));
                novNumbersAddah.addAll(recursion(novNumbersRunah.get(i), n));
            }            
            novNumbersRunah = novNumbersAddah;
            novNumbersAddah = new ArrayList<>();
        }

        // cheese strat 
        // somehow adding 3 more loops works ¯\_(ツ)_/¯
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < novNumbersRunah.size(); i++) {
                sequence.add(novNumbersRunah.get(i));
                novNumbersAddah.addAll(recursion(novNumbersRunah.get(i), n));
            }            
            novNumbersRunah = novNumbersAddah;
            novNumbersAddah = new ArrayList<>();
        }

        List<Integer> sortableSequence = new ArrayList<>(sequence);
        Collections.sort(sortableSequence);
        return sortableSequence.get(n);
    }

    public static List<Integer> recursion(int num, int targetedSize) {
        return new ArrayList<>(List.of(2 * num + 1, 3 * num + 1));
    }
}