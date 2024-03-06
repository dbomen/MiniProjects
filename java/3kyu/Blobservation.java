/* Your code here!
    
    Notes about the methods:
        * populate:     receives List<Map<String,Integer>>
                        returns nothing
        * move:         receives an int
                        returns nothing
        * print_state:  receives nothing
                        returns List<List<Integer>>

*/

import java.util.*;

public class Blobservation {
    int[][] area;
    static boolean randomPrimerPrintaj = false;
    // static int cnt;

    public Blobservation(int hNw) {
        if (!(8 <= hNw || hNw <= 50))  throw new RuntimeException();
        this.area = new int[hNw][hNw];
        // System.out.println(cnt++);
    }

    public Blobservation(int h, int w) {
        if (!(8 <= h || h <= 50) || !(8 <= w || w <= 50))  throw new RuntimeException();
        this.area = new int[h][w];
    }

    public void populate(List<Map<String, Integer>> list) {
        // if (randomPrimerPrintaj)  System.out.format("newCall: %d %d\n", this.area[18][5], this.area[18][6]);

        // [X, Y, Size]
        List<List<Integer>> potencialElements = new ArrayList<>();
        
        try { // delete

            // // TODO: delete
            // if (randomPrimerPrintaj)  System.out.println(list.toString());

            // check list for invalid values
            for (Map<String, Integer> map : list) {
                int x = map.get("x");
                int y = map.get("y");
                int size = map.get("size");

                if (x < 0 || y < 0 || size < 1 || size > 20) {
                    // System.out.println(list.toString());
                    // System.out.println();
                    // System.out.format("x:%d y:%d size: %d\n", x, y, size);
                    throw new RuntimeException();
                }

                List<Integer> element = List.of(x, y, size);
                potencialElements.add(element);

                // if (randomPrimerPrintaj)  System.out.format("%s\n", element);
            }

            int size = potencialElements.size();
            // if (randomPrimerPrintaj)  System.out.format("-----------------------------------%d", potencialElements.size());

            // add elements
            // if (randomPrimerPrintaj)  System.out.format("ADDING ELEMENTS________________________\n");
            for (int i = 0; i < size; i++) {
                this.area[potencialElements.get(i).get(0)][potencialElements.get(i).get(1)] += potencialElements.get(i).get(2);
                // if (randomPrimerPrintaj)  System.out.format("x: %d, y: %d, size: %d, i: %d, sizeOfList: %d\n", potencialElements.get(i).get(0), potencialElements.get(i).get(1), potencialElements.get(i).get(2), i, size);
            }
            // if (randomPrimerPrintaj)  System.out.format("ADDEDDDDDDDDDD________________________\n");
            // if (randomPrimerPrintaj)  System.out.format("%d %d", this.area[18][5], this.area[18][6]);

            //----------------------------------------------------------
            // TODO: delete this print spam!
            for (int i = 0; i < this.area.length; i++) {
                for (int j = 0; j < this.area[i].length; j++) {
                    if (this.area[i][j] == 0)  System.out.format("-- ");
                    else  System.out.format("%02d ", this.area[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            // -----------------------------------------------------------
        } catch (RuntimeException e) {
            // System.out.format("caught: %s\n", list.toString());
            for (int i = 0; i < potencialElements.size(); i++) {
                int el = this.area[potencialElements.get(i).get(0)][potencialElements.get(i).get(1)];
                if (el != 0 && el >= potencialElements.get(i).get(2)) {
                    this.area[potencialElements.get(i).get(0)][potencialElements.get(i).get(1)] -= potencialElements.get(i).get(2);
                }
            }
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
                        // TODO: remove current postion k bo delal
                    }
                }
            }

            // ----------------------------------------------------------
            // TODO: delete this print spam!
            System.out.println();
            for (int i = 0; i < movements.size(); i++) {
                System.out.format("%d,%d -> %d,%d | size: %d\n", movements.get(i)[0][0], movements.get(i)[0][1], movements.get(i)[1][0], movements.get(i)[1][1], movements.get(i)[3][0]);
            }
            // -----------------------------------------------------------

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

            //----------------------------------------------------------
            // TODO: delete this print spam!
            for (int i = 0; i < this.area.length; i++) {
                for (int j = 0; j < this.area[i].length; j++) {
                    if (this.area[i][j] == 0)  System.out.format("-- ");
                    else  System.out.format("%02d ", this.area[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            // -----------------------------------------------------------
        }
    }

    public int[] findMove(int[] pos, int[] size) {
        // TODO: remove this
        System.out.print("new blob: ");
        // when it finds "skinnier" blob, it will save the distance and look for "skinnier" blobs in that range

        int maxDistance = (this.area.length > this.area[0].length) ? this.area.length - 1: this.area[0].length - 1;

        // [ [Size] [Distance] [New Move] ]
        List<int[][]> possibleTargets = new ArrayList<>(0);

        // myb optimization pol: ni nujno da je treba maxDistance gledat
        for (int i = 1; i < maxDistance; i++) {
            boolean downIsGood = !(pos[0] + i > maxDistance);
            boolean upIsGood = !(pos[0] - i < maxDistance);
            boolean rightIsGood = !(pos[1] + i > maxDistance);
            boolean leftIsGood = !(pos[1] - i < maxDistance);

            if (downIsGood && this.area[pos[0] + i][pos[1]] < size[0] && this.area[pos[0] + i][pos[1]] != 0)                         possibleTargets.add(found(pos[0] + i, pos[1], maxDistance, pos));
            if (upIsGood && this.area[pos[0] - i][pos[1]] < size[0] && this.area[pos[0] - i][pos[1]] != 0)                           possibleTargets.add(found(pos[0] - i, pos[1], maxDistance, pos));
            if (rightIsGood && this.area[pos[0]][pos[1] + i] < size[0] && this.area[pos[0]][pos[1] + i] != 0)                        possibleTargets.add(found(pos[0], pos[1] + i, maxDistance, pos));
            if (leftIsGood && this.area[pos[0]][pos[1] - i] < size[0] && this.area[pos[0]][pos[1] - i] != 0)                         possibleTargets.add(found(pos[0], pos[1] - i, maxDistance, pos));
            if (downIsGood && rightIsGood && this.area[pos[0] + i][pos[1] + i] < size[0] && this.area[pos[0] + i][pos[1] + i] != 0)  possibleTargets.add(found(pos[0] + i, pos[1] + i, maxDistance, pos));
            if (downIsGood && leftIsGood && this.area[pos[0] + i][pos[1] - i] < size[0] && this.area[pos[0] + i][pos[1] - i] != 0)   possibleTargets.add(found(pos[0] + i, pos[1] - i, maxDistance, pos));
            if (upIsGood && rightIsGood && this.area[pos[0] - i][pos[1] + i] < size[0] && this.area[pos[0] - i][pos[1] + i] != 0)    possibleTargets.add(found(pos[0] - i, pos[1] + i, maxDistance, pos));
            if (upIsGood && leftIsGood && this.area[pos[0] - i][pos[1] - i] < size[0] && this.area[pos[0] - i][pos[1] - i] != 0)     possibleTargets.add(found(pos[0] - i, pos[1] - i, maxDistance, pos));

            if (possibleTargets.size() > 0)  break;
        }

        // for (int i = 0; i < this.area.length; i++) {
        //     for (int j = 0; j < this.area[i].length; j++) {
        //         if (Math.abs(i - pos[0]) > maxDistance || Math.abs(j - pos[1]) > maxDistance)  continue;
        //         if (this.area[i][j] == 0)  continue;

        //         // get possible targets
        //         if (this.area[i][j] < size[0]) {

        //             // size
        //             int[] sizeBlob = {this.area[i][j]};
                    
        //             // distance
        //             int iDistance = Math.abs(i - pos[0]);
        //             int jDistance = Math.abs(j - pos[1]);
        //             int bigger = (iDistance > jDistance) ? iDistance : jDistance;
        //             int[] distance = {bigger};
        //             maxDistance = distance[0];

        //             // move [x, y, prio] (if size and distance is the same we check this prio)
        //             int[] move = null;
        //             boolean iSame = (i - pos[0]) == 0;
        //             boolean jSame = (j - pos[1]) == 0;
        //             boolean iBigger = (i - pos[0]) > 0;
        //             boolean jBigger = (j - pos[1]) > 0;

        //             if      (iSame && jBigger)      move = new int[]{pos[0], pos[1] + 1, 2};     // go right
        //             else if (iSame && !jBigger)     move = new int[]{pos[0], pos[1] - 1, 6};     // go left
        //             else if (jSame && iBigger)      move = new int[]{pos[0] + 1, pos[1], 4};     // go down
        //             else if (jSame && !iBigger)     move = new int[]{pos[0] - 1, pos[1], 0};     // go up
        //                                                                             // ------------ Diagonals:
        //             else if (iBigger && jBigger)    move = new int[]{pos[0] + 1, pos[1] + 1, 3}; // go right / down
        //             else if (iBigger && !jBigger)   move = new int[]{pos[0] + 1, pos[1] - 1, 5}; // go left  / down
        //             else if (!iBigger && jBigger)   move = new int[]{pos[0] - 1, pos[1] + 1, 1}; // go right / up
        //             else if (!iBigger && !jBigger)  move = new int[]{pos[0] - 1, pos[1] - 1, 7}; // go left  / up

        //             else  throw new RuntimeException("wtf happened");

        //             int[][] in = {sizeBlob, distance, move};
        //             possibleTargets.add(in);
        //         }
        //     }
        // }

        // ----------------------------------------------------------
        // TODO: delete this print spam!
        for (int a = 0; a < possibleTargets.size(); a++) {
            System.out.format("size: %d | distance: %d | possible move: %d, %d\n", possibleTargets.get(a)[0][0], possibleTargets.get(a)[1][0], possibleTargets.get(a)[2][0], possibleTargets.get(a)[2][1]);
        }
        // -----------------------------------------------------------

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

    // TESTING
    public int[][] found(int i, int j, int maxDistance, int[] pos) {
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

        else  throw new RuntimeException("wtf happened");

        int[][] in = {sizeBlob, distance, move};
        return in;
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
        // TODO: myb bos rabu sortat
    }
}