//package UnionFind_1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] grid;
    private final int gridSize;
    private final int topNode = 0;
    private final int bottomNode;
    private int openSites = 0;
    private final WeightedQuickUnionUF full;
    private final WeightedQuickUnionUF open;
    
    public Percolation(int n) {
        grid = new boolean[n][n];
        gridSize = n;
        bottomNode = n * n + 1;
        open = new WeightedQuickUnionUF(n * n + 2);
        full = new WeightedQuickUnionUF(n * n + 1);
    }
    
    private int xyTo1D(int row, int col) {
        return (row - 1) * gridSize + col;
    }
    
    private boolean invalidInput(int row, int col) {
        if ( row > 0 && row <= gridSize && col > 0 && col <= gridSize) {
            return false;
        } else {
	    return true;
	}
    }
    
    public boolean isOpen(int row, int col) {
        if (invalidInput(row , col)) {
		throw new IllegalArgumentException();
	} 
        return grid[row -1][col - 1];    

    }
    
    public int numberOfOpenSites() {
        return openSites;
    }
    
    public boolean percolates() {
        return open.connected(topNode,bottomNode);
    }

    public void open(int row, int col) {
        if(!isOpen(row,col)) {
            grid[row - 1][col - 1] = true;
            openSites++;
            int openedNode = xyTo1D(row , col);
			
            //join newly opened site to surrounding sites
            if (row > 1 && isOpen(row - 1, col)) {
                open.union(openedNode, openedNode - gridSize);
                full.union(openedNode, openedNode - gridSize); 
            }
            if (row < gridSize && isOpen(row + 1, col)) {
                open.union(openedNode, openedNode + gridSize);
                full.union(openedNode, openedNode + gridSize);
            }
            if (col > 1 && isOpen(row , col - 1)) {
                open.union(openedNode, openedNode - 1); 
                full.union(openedNode, openedNode - 1);
            }
            if (col < gridSize && isOpen(row , col + 1)) {
                open.union(openedNode, openedNode + 1);
                full.union(openedNode, openedNode + 1);
            }           
            
            //join top and bottom row to virtual node
            if(row == 1){
                open.union(topNode, openedNode);
                full.union(topNode, openedNode);
            }
            if(row == gridSize) {
                open.union(bottomNode, openedNode);
            }			
        } 
    }
}
