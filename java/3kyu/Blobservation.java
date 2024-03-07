import java.util.*;

public class Blobservation {
    int[][] area;
    // very important attributes, they help ALOT with optimisation
    boolean oneOrEmpty;
    int numOfElements;

    public Blobservation(int hNw) {
        if (!(8 <= hNw || hNw <= 50))  throw new RuntimeException();
        this.area = new int[hNw][hNw];
        this.oneOrEmpty = true;
        this.numOfElements = 0;
    }

    public Blobservation(int h, int w) {
        if (!(8 <= h || h <= 50) || !(8 <= w || w <= 50))  throw new RuntimeException();
        this.area = new int[h][w];
        this.oneOrEmpty = true;
        this.numOfElements = 0;
    }

    public void populate(List<Map<String, Integer>> list) {  // num of elementa before populating (useful for backtracking invalid list) (helps alot with optimisation)
        int beforePopulatingSize = this.numOfElements;

        // [X, Y, Size]
        List<List<Integer>> potencialElements = new ArrayList<>();
        
        try {

            // check list for invalid values
            for (Map<String, Integer> map : list) {
                int x = map.get("x");
                int y = map.get("y");
                int size = map.get("size");

                if (x < 0 || y < 0 || size < 1 || size > 20) {
                    throw new RuntimeException();
                }

                List<Integer> element = List.of(x, y, size);
                potencialElements.add(element);
            }

            int size = potencialElements.size();

            // add elements
            for (int i = 0; i < size; i++) {
                if (this.area[potencialElements.get(i).get(0)][potencialElements.get(i).get(1)] == 0)  this.numOfElements++;
                this.area[potencialElements.get(i).get(0)][potencialElements.get(i).get(1)] += potencialElements.get(i).get(2);
            }

            if (this.numOfElements > 1)  this.oneOrEmpty = false;
        } catch (RuntimeException e) {

            for (int i = 0; i < this.numOfElements - beforePopulatingSize; i++) {
                int el = this.area[potencialElements.get(i).get(0)][potencialElements.get(i).get(1)];
                if (el != 0 && el >= potencialElements.get(i).get(2)) {
                    this.area[potencialElements.get(i).get(0)][potencialElements.get(i).get(1)] -= potencialElements.get(i).get(2);
                }
            }
            this.numOfElements = beforePopulatingSize;

            throw new RuntimeException();
        }
    }

    public void move() {
        move(1);
    }

    public void move(int n) {
        if (n <= 0)  throw new RuntimeException();

        while (n > 0) {
            n--;
            if (this.oneOrEmpty)  return;

            // [ [Current Position], [New Position], [Is It Fused], [Size] ]
            List<int[][]> movements = new ArrayList<>();

            // finding movement
            for (int i = 0; i < this.area.length; i++) {
                for (int j = 0; j < this.area[i].length; j++) {
                    if (this.area[i][j] != 0) {
                        int[] currentPos = {i, j};
                        int[] size = {this.area[i][j]};
                        int[] move = findMove(currentPos, size);
                        int[] isFused = {0};
                        int[][] in = {currentPos, move, isFused, size};
                        movements.add(in);
                    }
                }
            }

            // [ [Current Position], [New Position], [Is Fused], [Size] ]
            List<int[][]> movementsFused = new ArrayList<>();

            // fusion
            for (int i = 0; i < movements.size(); i++) {
                if (movements.get(i)[2][0] != 0)  continue;
                for (int j = 0; j < movements.size(); j++) {
                    if (i == j)  continue;
                    if (movements.get(i)[1][0] == movements.get(j)[1][0] && movements.get(i)[1][1] == movements.get(j)[1][1]) {
                        movements.get(i)[3][0] += movements.get(j)[3][0];

                        // "delete" the fused blob
                        int[][] fused = movements.get(j);
                        fused[2][0] = 1;
                        movements.set(j, fused);
                    }
                }
                movementsFused.add(movements.get(i));
            }

            // moving
            this.area = new Blobservation(this.area.length, this.area[0].length).area;
            for (int i = 0; i < movementsFused.size(); i++) {
                this.area[movementsFused.get(i)[1][0]][movementsFused.get(i)[1][1]] = movementsFused.get(i)[3][0];
            }

            this.numOfElements = movementsFused.size();
            if (this.numOfElements <= 1)  this.oneOrEmpty = true;
        }
    }

    public int[] findMove(int[] pos, int[] size) {  // when it finds "skinnier" blob, it will save the distance and look for "skinnier" blobs in that range
        int maxDistance = (this.area.length > this.area[0].length) ? this.area.length - 1: this.area[0].length - 1;

        // [ [Size] [Distance] [New Move] ]
        List<int[][]> possibleTargets = new ArrayList<>(0);

        for (int i = 0; i < this.area.length; i++) {
            for (int j = 0; j < this.area[i].length; j++) {
                if (Math.abs(i - pos[0]) > maxDistance || Math.abs(j - pos[1]) > maxDistance)  continue;
                if (this.area[i][j] == 0)  continue;

                // get possible targets
                if (this.area[i][j] < size[0]) {

                    // size
                    int[] sizeBlob = {this.area[i][j]};
                    
                    // distance
                    int iDistance = Math.abs(i - pos[0]);
                    int jDistance = Math.abs(j - pos[1]);
                    int bigger = (iDistance > jDistance) ? iDistance : jDistance;
                    int[] distance = {bigger};
                    maxDistance = distance[0];

                    // move [x, y, prio] (if size and distance is the same we check this prio)
                    int[] move = null;
                    boolean iSame = (i - pos[0]) == 0;
                    boolean jSame = (j - pos[1]) == 0;
                    boolean iBigger = (i - pos[0]) > 0;
                    boolean jBigger = (j - pos[1]) > 0;

                    if      (iSame && jBigger)      move = new int[]{pos[0], pos[1] + 1, 2};     // go right
                    else if (iSame && !jBigger)     move = new int[]{pos[0], pos[1] - 1, 6};     // go left
                    else if (jSame && iBigger)      move = new int[]{pos[0] + 1, pos[1], 4};     // go down
                    else if (jSame && !iBigger)     move = new int[]{pos[0] - 1, pos[1], 0};     // go up
                                                                                    // ------------ Diagonals:
                    else if (iBigger && jBigger)    move = new int[]{pos[0] + 1, pos[1] + 1, 3}; // go right / down
                    else if (iBigger && !jBigger)   move = new int[]{pos[0] + 1, pos[1] - 1, 5}; // go left  / down
                    else if (!iBigger && jBigger)   move = new int[]{pos[0] - 1, pos[1] + 1, 1}; // go right / up
                    else if (!iBigger && !jBigger)  move = new int[]{pos[0] - 1, pos[1] - 1, 7}; // go left  / up

                    int[][] in = {sizeBlob, distance, move};
                    possibleTargets.add(in);
                }
            }
        }

        if (possibleTargets.size() == 0)  return pos;
        possibleTargets.sort((a, b) -> {
            if (a[1][0] == b[1][0]) {
                if (a[0][0] == b[0][0]) {
                    return a[2][2] - b[2][2];
                }
                return b[0][0] - a[0][0];
            }
            return a[1][0] - b[1][0];
        });

        return possibleTargets.get(0)[2];
    }

    public List<List<Integer>> printState() {
        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < this.area.length; i++) {
            for (int j = 0; j < this.area[i].length; j++) {
                if (this.area[i][j] == 0)  continue;

                List<Integer> sizes = List.of(i, j, this.area[i][j]);
                list.add(sizes);
            }
        }

        return list;
    }
}