import java.util.*;

public class User {
    int rank;
    int progress;
    static List<Integer> ranks = new ArrayList<>(List.of(-8, -7, -6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6, 7, 8));

    public User() {
        this.rank = -8;
        this.progress = 0;
    }

    public void incProgress(int rank) {
        if (!(this.rank == 8)) {
            int iCRank = ranks.indexOf(this.rank);
            int iRank = ranks.indexOf(rank); if (iRank == -1) throw new IllegalArgumentException();

            if (!(iCRank > iRank + 1) && iRank != -1) {
                int rankDiff = iRank - iCRank;
                if      (iRank > iCRank)   this.progress += 10 * rankDiff * rankDiff;
                else if (iRank == iCRank)  this.progress += 3;
                else                       this.progress++;
            }

            while (this.progress > 100)  rankup();
        }
    }

    public void rankup() {
        this.rank = ranks.get(ranks.indexOf(this.rank) + 1);
        this.progress -= 100;
        if (this.rank == 8)  this.progress = 0;
    }
}