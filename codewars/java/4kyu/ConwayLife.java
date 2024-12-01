import java.util.Arrays;

public class ConwayLife {

    public static int[][] getGeneration(int[][] cells, int generations) {

        for (int i = 0; i < generations; i++) {
            cells = doGeneration(cells);
        }
        
        return trim(cells, 0);
    }

    public static int[][] doGeneration(int[][] cells) {
        cells = wrap(cells, cells.length, cells[0].length); // makes the same arr, but bigger by 1 in every directions, e.g:
        //         00000
        // 111     01110
        // 101 --> 01010
        // 111 --> 01110
        //         00000

        int rowSize = cells.length;
        int colSize = cells[0].length;
        
        int[][] cellsNew = new int[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (checkIfLives(cells, i, j, rowSize, colSize))  cellsNew[i][j] = 1;
            }
        }

        return cellsNew;
    }

    public static int[][] wrap(int[][] cells, int rowSize, int colSize) {
        int[][] wrappedCells = new int[rowSize + 2][colSize + 2];
        
        // we fill the "inner" part with cells
        for (int i = 1; i <= rowSize; i++) {
            for (int j = 1; j <= colSize; j++) {
                wrappedCells[i][j] = cells[i - 1][j - 1];
            }
        }

        return wrappedCells;
    }

    public static boolean checkIfLives(int[][] cells, int i, int j, int rowSize, int colSize) {
        int numOfNeighbours = 0;
        int[] neighbours = new int[8];

        if (inRangeChecker(rowSize, colSize, i + 1, j) && cells[i + 1][j] == 1)  {numOfNeighbours++; neighbours[0]++;}
        if (inRangeChecker(rowSize, colSize, i - 1, j) && cells[i - 1][j] == 1)  {numOfNeighbours++; neighbours[1]++;}
        if (inRangeChecker(rowSize, colSize, i, j + 1) && cells[i][j + 1] == 1)  {numOfNeighbours++; neighbours[2]++;}
        if (inRangeChecker(rowSize, colSize, i, j - 1) && cells[i][j - 1] == 1)  {numOfNeighbours++; neighbours[3]++;}

        if (inRangeChecker(rowSize, colSize, i + 1, j + 1) && cells[i + 1][j + 1] == 1)  {numOfNeighbours++; neighbours[4]++;}
        if (inRangeChecker(rowSize, colSize, i + 1, j - 1) && cells[i + 1][j - 1] == 1)  {numOfNeighbours++; neighbours[5]++;}
        if (inRangeChecker(rowSize, colSize, i - 1, j + 1) && cells[i - 1][j + 1] == 1)  {numOfNeighbours++; neighbours[6]++;}
        if (inRangeChecker(rowSize, colSize, i - 1, j - 1) && cells[i - 1][j - 1] == 1)  {numOfNeighbours++; neighbours[7]++;}

        if (cells[i][j] == 0)  return (numOfNeighbours == 3) ? true : false;  // if dead cell
        else                   return (numOfNeighbours == 2 || numOfNeighbours == 3) ? true : false; // if alive cell
    }

    public static boolean inRangeChecker(int rowSize, int colSize, int i, int j) {
        return (i >= 0 && i < rowSize && j >= 0 && j < colSize);
    }

    public static int[][] trim(int[][] mtx, int rmin, int rmax, int cmin, int cmax) {
        int[][] result = new int[rmax-rmin+1][];
        for (int r = rmin, i = 0; r <= rmax; r++, i++) {
            result[i] = Arrays.copyOfRange(mtx[r], cmin, cmax+1);
        }
        return result;
    }
     
    public static int[][] trim(int[][] mtx, int trimmed) {
        int cmin = mtx[0].length;
        int rmin = mtx.length;
        int cmax = -1;
        int rmax = -1;
     
        for (int r = 0; r < mtx.length; r++) {
            for (int c = 0; c < mtx[0].length; c++) {
                if (mtx[r][c] != trimmed) {
                    if (cmin > c) cmin = c;
                    if (cmax < c) cmax = c;
                    if (rmin > r) rmin = r;
                    if (rmax < r) rmax = r;
                }
            }
        }
     
        return trim(mtx, rmin, rmax, cmin, cmax);
    }
}