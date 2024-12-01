import java.util.*;

public class Bowling {
    public static int bowling_score(String frames) {
        List<Integer[]> scores = new ArrayList<>();
        String[] framesArr = frames.split(" ");

        for (int i = 0; i < framesArr.length; i++) {
            char value = 0;
            int scoreOfFrame = 0;
            int spare = 0;
            int strike = 0;

            for (int j = 0; j < framesArr[i].length(); j++) {
                value = framesArr[i].charAt(j);
                if (value != 'X' && value != '/')  scoreOfFrame += value - '0';
                else if (value == '/') {
                    scoreOfFrame = 10;
                    spare = 1;
                }
                else if (value == 'X') {
                    scoreOfFrame += 10;
                    strike = 1;
                }
            }

            // spare checker
            Integer pSpare = (i - 1 >= 0) ? scores.get(i - 1)[1] : 0;
            Integer pStrike = (i - 1 >= 0) ? scores.get(i - 1)[2] : 0;

            int spareAdding = (framesArr[i].charAt(0) == 'X') ? 10 : framesArr[i].charAt(0) - '0';
            
            if      (i == 9 && pSpare == 1)        scores.set(i - 1, new Integer[]{scores.get(i - 1)[0] + spareAdding, 0, 0});
            else if (value != 'X' && pSpare == 1)  scores.set(i - 1, new Integer[]{scores.get(i - 1)[0] + spareAdding, 0, 0});
            else if (value == 'X' && pSpare == 1)  scores.set(i - 1, new Integer[]{scores.get(i - 1)[0] + 10, 0, 0});

            // strike checker
            Integer ppStrike = (i - 2 >= 0) ? scores.get(i - 2)[2] : 0;
            int strikeadding = ((scoreOfFrame > 10) ? 10 : scoreOfFrame);

            if      (pStrike == 1 && value == 'X')                                   scores.set(i - 1, new Integer[]{scores.get(i - 1)[0] + strikeadding, 0, pStrike});
            else if (pStrike == 1 && value != 'X')                                   scores.set(i - 1, new Integer[]{scores.get(i - 1)[0] + strikeadding, 0, 0});
            if      (pStrike == 1 && i == 9 && framesArr[i].charAt(1) == 'X')  scores.set(i - 1, new Integer[]{scores.get(i - 1)[0] + 10, 0, 0});
            
            int addingOld = (framesArr[i].charAt(0) == 'X') ? 10 : framesArr[i].charAt(0) - '0';
            if (ppStrike == 1)  scores.set(i - 2, new Integer[]{scores.get(i - 2)[0] + addingOld, 0, 0});

            scores.add(new Integer[]{scoreOfFrame, spare, strike});
        }

        int sumOfScores = 0;
        for (int i = 0; i < scores.size(); i++)  sumOfScores += scores.get(i)[0];

        return sumOfScores;
    }
}