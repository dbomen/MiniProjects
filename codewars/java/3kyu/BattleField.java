public class BattleField {
    static final int SIZE = 10;

    public static boolean fieldValidator(int[][] field) {
        // ships: [battleShipCnt, cruiserCnt, destroyerCnt, sumbarineCnt]
        int[] ships = new int[4];

        boolean[][] checker = new boolean[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (checker[i][j] == false && field[i][j] == 1) {

                    if (diagonalToucher(field, i, j))  return false; // checker for diagonal touchin ships

                    // where we looking: [left, down, right, up]
                    int[] look = new int[4];

                    // check left
                    if (checkRange(j - 1) && field[i][j - 1] == 1)  look[0]++;

                    // check down
                    if (checkRange(i + 1) && field[i + 1][j] == 1)  look[1]++;

                    // check right
                    if (checkRange(j + 1) && field[i][j + 1] == 1)  look[2]++;

                    // check up
                    if (checkRange(i - 1) && field[i - 1][j] == 1)  look[3]++;


                    if (wrongLook(look))   return false; // checker for non-straight or edge touching ships

                    int size = getLenght(field, i, j, look, checker); // fixes checker aswell
                    if (size > 4)  return false;                      // size checker
                    ships[4 - size]++;
                }
            }
        }
        return allShipsUsed(ships);
    }

    public static int getLenght(int[][] field, int i, int j, int[] look, boolean[][] checker) {

        checker[i][j] = true;

        int size = 1;
        if (look[0] != 0) { // looking left

            for (int k = j - 1; k > 0; k--) {
                if (field[i][k] == 1)  size++;
                else                   break;
                checker[i][k] = true;
            }

        } else if (look[1] != 0) { // looking down
            
            for (int k = i + 1; k < SIZE; k++) {
                if (field[k][j] == 1)  size++;
                else                   break;
                checker[k][j] = true;
            }

        } else if (look[2] != 0) { // looking right
            
            for (int k = j + 1; k < SIZE; k++) {
                if (field[i][k] == 1)  size++;
                else                   break;
                checker[i][k] = true;
            }

        } else if (look[3] != 0) { // looking up
            
            for (int k = i - 1; k > 0; k--) {
                if (field[k][j] == 1)  size++;
                else                   break;
                checker[k][j] = true;
            }

        }

        return size;
    }

    public static boolean checkRange(int index) {
        if (index < 0 || index >= SIZE)  return false;
        return true;
    }

    public static boolean wrongLook(int[] look) {
        int looking = 0;
        for (int i = 0; i < look.length; i++) {
            if (look[i] != 0)  looking++;
        }
        return (looking > 1) ? true : false;
    }

    public static boolean diagonalToucher(int[][] field, int i, int j) {
        if (checkRange(i - 1) && checkRange(j - 1) && field[i - 1][j - 1] == 1)  return true;
        if (checkRange(i - 1) && checkRange(j + 1) && field[i - 1][j + 1] == 1)  return true;
        if (checkRange(i + 1) && checkRange(j - 1) && field[i + 1][j - 1] == 1)  return true;
        if (checkRange(i + 1) && checkRange(j + 1) && field[i + 1][j + 1] == 1)  return true;
        return false;
    }

    public static boolean allShipsUsed(int[] ships) {
        for (int i = 0; i < ships.length; i++) {
            if (ships[i] != i + 1)  return false;
        }
        return true;
    }
}