public class Greed {
    public static int greedy(int[] dice) {
        int[] results = new int[7];
        int[] prizesT = {0, 1000, 200, 300, 400, 500, 600};
        int prize = 0;

        // put in results
        for (int i = 0; i < dice.length; i++) {
            results[dice[i]]++;
        }

        // get prizes for triplets
        for (int i = 0; i < results.length; i++) {
            if (results[i] >= 3) {
                prize += prizesT[i];
                results[i] -= 3;
            }
        }

        // get prizes for one
        prize += results[1] * 100;
        prize += results[5] * 50;

        return prize;
    }
}