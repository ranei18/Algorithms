import java.util.ArrayList;

public class Board {

    private final int dimension;
    private final int[][] board;
    private final ArrayList<Board> neighbors = new ArrayList();
    private int hamming;
    private int manhattan;
    private String output;

    // construct a board from an n-by-n array of block
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        dimension = blocks.length;
        board = blocks;
        hamming = 0;
        manhattan = 0;
        output += dimension + "\n";

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] != (i * 3) + j + 1 && board[i][j] != 0) {
                    hamming++;
                }
                if (board[i][j] > 0) {
                    manhattan += Math.abs((board[i][j] - 1) % dimension - j % dimension) +
                            Math.abs((board[i][j] - 1) / 3 - (i));
                }
                output += board[i][j] + " ";
            }
            output += "\n";
        }
        output += "\n";
    }

    // board dimension n
    public int dimension() {
        return dimension;
    }

    // number of blocks out of place
    public int hamming() {
        return hamming;
    }


    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        if (this.hamming() == 0) return true;
        else return false;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[][] temp = board;
        int tempVal = temp[0][0];
        temp[0][0] = temp[0][1];
        temp[0][1] = tempVal;
        return new Board(temp);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return this == y;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        if (neighbors.isEmpty()) {
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    if (board[i][j] == 0) {
                        checkNeighbor(i, j, i - 1, j);
                        checkNeighbor(i, j, i + 1, j);
                        checkNeighbor(i, j, i, j - 1);
                        checkNeighbor(i, j, i, j + 1);
                        return neighbors;
                    }
                }
            }
        }
        return neighbors;
    }

    private void checkNeighbor(int i0, int j0, int i1 , int j1) {
        int[][] temp = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                temp[i][j] = board[i][j];
            }
        }
        //why doesn't this work when it does for twin?
        //int[][] temp = board;
        if ( i1 >= 0 && i1 < dimension && j1 >= 0 && j1 < dimension) {
            temp[i0][j0] = temp[i1][j1];
            temp[i1][j1] = 0;
                neighbors.add(new Board(temp));
        }
    }


    // string representation of this board (in the output format specified below)
    public String toString() {
        return output;
    }

    // unit tests (not graded)
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        System.out.println("hamming " + initial.hamming());
        System.out.println("manhattan " + initial.manhattan());
        System.out.print(initial);
        //Board twin = initial.twin();
        //System.out.println(twin);

        Iterable<Board> list = initial.neighbors();
        for (Board b : list) {
            System.out.println("hamming: " + b.hamming());
            System.out.print(b);
            //System.out.println("hamming: " + b.hamming());
        }

    }
}
