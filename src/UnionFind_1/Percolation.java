package UnionFind_1;
//import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] grid;
    private final int gridSize;
    private final int topNode = 0;
    private final int bottomNode;
    private int openSites = 0;
    private final WeightedQuickUnionUF full;       
    
    public Percolation(int n){
        grid = new boolean[n][n];
        gridSize = n;
        bottomNode = n * n + 1;
        full = new WeightedQuickUnionUF(n * n + 2);
    }
    
    public int xyTo1D(int row, int col){
        return (row - 1)*gridSize + col;
    }
    
    public boolean isOpen(int row, int col){
        return grid[row -1][col - 1];
    }
    
    public int numberOfOpenSites(){
        return openSites;
    }
    
    public boolean percolates(){
        return full.connected(topNode,bottomNode);
    }
    
    public boolean isFull(int row, int col){
        return full.connected(topNode,xyTo1D(row, col));
    }
    
    public void open(int row, int col){
        if(!isOpen(row,col)){
            grid[row - 1][col - 1] = true;
            openSites += 1;
        }
        
        matchIfOpen(xyTo1D(row , col), row - 1, col);
        matchIfOpen(xyTo1D(row , col), row + 1, col);
        matchIfOpen(xyTo1D(row , col), row, col - 1);
        matchIfOpen(xyTo1D(row , col), row, col + 1);    
        if(row == 1){
            matchIfOpen(topNode, row, col);
        }
        if(row == gridSize && isFull(row, col)){
            matchIfOpen(bottomNode, row, col);
        }
        
    }
    
    public void matchIfOpen(int newlyOpened, int row, int col){
        try{
            if (isOpen(row,col)){
                full.union(newlyOpened,xyTo1D(row, col));
            }
        } catch (IndexOutOfBoundsException e){
            //do nothing
        }
    }
}