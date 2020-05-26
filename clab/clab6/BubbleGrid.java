import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class BubbleGrid {
    private int[][] grid;
    private int rowNum;
    private int colNum;

    private int[][] neighborOffSet = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int ceiling = 0;
    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        rowNum = grid.length;
        colNum = grid[0].length;

    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        // TODO
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(rowNum*colNum +1);
        for (int[] dart : darts) {
//            int index = xyTo1D(dart[0] , dart[1]);
            if (grid[dart[0]][dart[1]] ==1 ){
                grid[dart[0]][dart[1]] = 2;
            }
        }

        int[] res = new int[darts.length];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (grid[i][j] == 1) {
                    unionNeighbors(i,j,grid,uf);
                }
            }
        }
        int numAfterDart = stuckBubbles(uf) ;

        for (int i = darts.length - 1; i >= 0; i--) {
            int r = darts[i][0];
            int c = darts[i][1];
            if (grid[r][c] == 0) {
                res[i] = 0;
            } else {
                grid[r][c] = 1;
                unionNeighbors(r,c,grid,uf);
                int numBeforeDart = stuckBubbles(uf);
                res[i] = numBeforeDart - numAfterDart -1;
            }
        }

        return res;

    }

    private int xyTo1D(int row, int col) {
        return row*colNum + col +1;
    }

    private void unionNeighbors(int row, int col, int[][] grid, WeightedQuickUnionUF uf) {
        if (row == 0) {
            uf.union(xyTo1D(row,col) , ceiling);
        }
        for (int[] offSet : neighborOffSet) {
            int adjRow = offSet[0] + row;
            int adjCol = offSet[1] + col;
            if (adjCol >= 0 && adjCol < colNum && adjRow >= 0 && adjRow < rowNum
                    && grid[adjRow][adjCol] == 1) {
                uf.union(xyTo1D(row, col), xyTo1D(adjRow, adjCol));
            }
        }
    }

    private int stuckBubbles(WeightedQuickUnionUF uf) {
        int count = 0;
        for (int i = 1; i < rowNum * colNum + 1; i++) {
            if (uf.connected(ceiling, i)) {
                count += 1;
            }
        }
        return count;
    }

}
