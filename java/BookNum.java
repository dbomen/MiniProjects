public class BookNum {
    public static long pageDigits(long pages) {

        long total = 0;

        // gledamo naravna stevila, interval:(1 - neskoncno)
        // 9 stevil ima eno stevko
        // 90 stevil ima 2 stevki
        // 900 stevil ima 3 stevke
        // ...

        long stevke = 1;
        long delitel = 1;
        long steviloKjerSmo = 0;

        while (pages / (delitel * 10) > 0) {
            total += 9 * delitel * stevke;
            steviloKjerSmo += 9 * delitel;

            delitel *= 10;
            stevke++;
        }

        total += (pages - steviloKjerSmo) * stevke;

        return total;

        // NE DOVOLJ UCINKOVIT PROGRAM:
        // long rTotal = 0;
        // for (long i = 1; i <= pages; i++) {
        //     long trenutna = i;
        //     long cnt = 1;
        //     while (trenutna >= 10) {
        //         trenutna /= 10;
        //         cnt++;
        //     }
        //     rTotal += cnt;
        // }
        // return rTotal;
    }
    
}