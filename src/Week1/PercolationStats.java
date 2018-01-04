//package UnionFind_1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int numTrials;
    private double[] threshold;
    private final double confidence = 1.96;
    private int counter;
	private Percolation p;
    
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException();
        } else {
            threshold = new double[trials];
            numTrials = trials;
            for (int i = 0; i < trials; i++) {
                counter = 0;
                p = new Percolation(n);
                while(!p.percolates()) {
                    int row;
                    int col;
                    do {
                    row = StdRandom.uniform(n) + 1;
                    col = StdRandom.uniform(n) + 1;
                    } while(p.isOpen(row , col));
                    p.open(row, col);
                }
                threshold[i] = (double)counter / (double)(n * n);
            }   
        }   
    }
    
    public double mean() {
        return StdStats.mean(threshold);
    }
    
    public double stddev() {
        return StdStats.stddev(threshold);
    }
    
    public double confidenceLo() {
        return mean() - ((confidence * stddev())/Math.sqrt(numTrials));
    }
    
    public double confidenceHi(){
        return mean() + ((confidence * stddev())/Math.sqrt(numTrials));
    }
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
		
		//System.out.println("mean:\t\t\t\t = " + stats.mean());
		//System.out.println("stddev:\t\t\t\t = " + stats.stddev());
		//System.out.println("95% confidence interval:\t = " + stats.confidenceLo() + ", " + stats.confidenceHi());
	}
}