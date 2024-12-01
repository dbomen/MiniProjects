import java.util.*;

public class WeightSort {
    
    public static String orderWeight(String strng) {
        if (strng.equals("")) return "";

        String[] weights = strng.split(" ");

        List<Weight> sortableWeights = new ArrayList<>();
        
        for (String weight : weights) {
            sortableWeights.add(new Weight(weight));
        }

        Collections.sort(sortableWeights);

        StringBuilder output = new StringBuilder();

        for (Weight weight : sortableWeights) {
            output.append(String.format("%s ", weight.toString()));
        }

        return output.delete(output.length() - 1, output.length()).toString();
    }

    private static class Weight implements Comparable<Weight> {
        protected String weight;
        protected int fakeWeight;

        public Weight(String weight) {
            this.weight = weight;
            this.fakeWeight = getFakeWeight(weight);
        }

        public int getFakeWeight(String weight) {
            long weightI = Long.valueOf(weight);

            int sum = 0;
            while (weightI > 0) {
                sum += weightI % 10;
                weightI /= 10;
            }

            return sum;
        }

        @Override
        public int compareTo(Weight other) {
            return (this.fakeWeight == other.fakeWeight) ? String.valueOf(this.weight).compareTo(String.valueOf(other.weight)) : this.fakeWeight - other.fakeWeight;
        }

        @Override
        public String toString() {
            return this.weight;
        }
    }
}