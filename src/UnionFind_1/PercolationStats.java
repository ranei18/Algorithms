package UnionFind_1;
//import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdStats;

public class PercolationStats{
    private int numTrials;
    private double[] threshold;
    private int total = 0;
    
    public PercolationStats(int n, int trials){
        try{
            inputCheck(n, trials);
            threshold = new double[trials];
            numTrials = trials;
            for (int i = 0; i < trials; i++){
                Percolation p = new Percolation(n);
                while(!p.percolates()){
                    int row = StdRandom.uniform(1,n);
                    int col = StdRandom.uniform(1,n);
                    p.open(row, col);
                }
            threshold[i] = p.numberOfOpenSites() / (n * n);
            total += p.numberOfOpenSites() / (n * n);
            } 
        } catch(IllegalArgumentException e){
            //
        }   
    }
    
    public double mean(){
        return total / numTrials;
    }
    
    public double stddev(){
        int temp = 0;
        for(int i = 0; i < threshold.length; i++){
            temp += Math.pow(threshold[i] - mean(),2);
        } 
        return Math.sqrt(temp / (numTrials - 1));
    }
    
    public double confidenceLo(){
        return mean() - ((1.96 * stddev())/Math.sqrt(numTrials));
    }
    
    public double confidenceHi(){
        return mean() + ((1.96 * stddev())/Math.sqrt(numTrials));
    }
    
    void inputCheck(int n, int trials){
        if( n <= 0 || trials <= 0){
            throw new IllegalArgumentException("bad inputs");
        }
    }
    
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats test = new PercolationStats(n, trials);
        
        System.out.println("PercolationStats" + n + "\t" + trials);
        System.out.println("mean \t" + test.mean());
        System.out.println("stddev \t" + test.stddev());
        System.out.println("95% confidence \t[" + test.confidenceLo() +", " + test.confidenceHi() + "]"); 
    }   
}