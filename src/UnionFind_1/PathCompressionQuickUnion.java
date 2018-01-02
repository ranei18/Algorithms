package UnionFind_1;

//two-pass implementation would add a second loop to root() to set
// the id[] of each examined element to the root.

public class PathCompressionQuickUnion {
    private int[] id;
    
    public PathCompressionQuickUnion(int N){
        id = new int[N];
        for (int i = 0; i < id.length; i++) id[i] = i;
    }
    
    private int root(int i){
        while(i != id[i]) {
            i = id[i];
            //one-pass variant of path compression
            //makes every other node point to its grandparent
            id[i] = id[id[i]];
        }
        return i;
    }
    
    public boolean connected(int p, int q){
        return root(p) == root(q);
    }
    
    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
}