public class HowManyPagesInABook {
    public static int amountOfPages(int summary) {
        int stevke = 0;
        for (int i = 1; true; i++) {
            if (i >= 10) {
                stevke += steviloStevk(i);
                
            } else {
                stevke++;
            }
            if (stevke == summary) {
                return i;
            }
        }
    }

    public static int steviloStevk(int st) {
        int stevke = 1;
        while (st >= 10) {
            st /= 10;
            stevke++;
        }
        return stevke;
    }
}