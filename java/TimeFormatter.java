import java.util.*;

public class TimeFormatter {
    public static String formatDuration(int seconds) {
        String out = "";

        Map<String, Integer> durations = new TreeMap<>(getComparatorX());
        durations.putAll(Map.of("year", seconds / 3600 / 24 / 365, "day", (seconds / 3600 / 24) % 365, "hour", (seconds / 3600) % 24, "minute", (seconds / 60) % 60, "second", seconds % 60));

        // remove if value == 0
        List<String> toBeRemoved = new ArrayList<>();
        for (String key : durations.keySet()) {
            if (durations.get(key) == 0) {
                toBeRemoved.add(key);
            }
        }
        for (String key : toBeRemoved) {
            durations.remove(key);
        }

        // builds string
        boolean isEmpty = durations.isEmpty();
        if (isEmpty) {
            out = "now";
        } else {
            for (String key : durations.keySet()) {
                // builds value
                if (durations.get(key) < 1) {
                    continue;
                } else if (durations.get(key) == 1) {
                    out += String.format("%d %s", durations.get(key), key);
                } else {
                    out += String.format("%d %ss", durations.get(key), key);
                }

                // builds other
                // if (second to last key), else if (last key)
                if (durations.keySet().toArray()[durations.keySet().size() - 1].equals(key)) {
                } else if (durations.keySet().toArray()[durations.keySet().size() - 2].equals(key)) {
                    out += String.format(" and ");
                } else {
                    out += String.format(", ");
                }
            }
        }
        
        return out;
    }

    // compares keys based on their index in the list
    public static Comparator<String> getComparatorX() {
        return new Comparator<String>() {
            List<String> durations = List.of("year", "day", "hour", "minute", "second");
            public int compare(String a, String b) {
                int indexA = durations.indexOf(a);
                int indexB = durations.indexOf(b);
                return indexA - indexB;
            }
        };
    }
}