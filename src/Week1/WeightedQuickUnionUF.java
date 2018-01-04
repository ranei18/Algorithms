package Week1;

public class WeightedQuickUnionUF {
    private int[] id;
    //create a second array to include the size of the node
    private int[] sz;
    
    public WeightedQuickUnionUF(int N){
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
            //set the initial size of the node to 1
            sz[i] = 1;
        }
    }
    
    private int root(int i){
        while(i != id[i]) i = id[i];
        return i;
    }
    
    public boolean connected(int p, int q){
        return root(p) == root(q);
    }
    
    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        if(i == j) return;
        //compare node sizes and add the smaller to the larger node
        if(sz[i] < sz[j]) {
            id[i] = j; sz[j] += sz[i];
        } else {
            id[j] = i; sz[i] += sz[j];
        }
    }
}