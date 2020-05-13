import javax.swing.*;

public class UnionFind {
    private int[] items;
    // TODO - Add instance variables?

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        items = new int[n];
        for (int i = 0; i < n; i++) {
            items[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (vertex>=items.length-1){
            throw new IllegalArgumentException("illegal argument");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        validate(v1);
        while (v1>0){
            v1 = items[v1];
        }
        return -1*v1;
    }

    /* Returns the parent of v1. If v1 is the find of a tree, returns the
       negative size of the tree for which v1 is the find. */
    public int parent(int v1) {
        // TODO
        validate(v1);
        return items[v1];
    }

    

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
//        validate(v1);
//        validate(v2);
//        while (items[v1]>=0){
//            v1 = items[v1];
//        }
//        while (items[v2]>=0){
//            v2 = items[v2];
//        }

        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's find to v2's find. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        int findV1 = find(v1);
        int findV2 = find(v2);
        if (findV1 == findV2) {
            return;
        }else{
            if (items[findV2]<=items[findV1]){
                items[findV2] = items[findV1] + items[findV2];
                items[findV1] = findV2;
            }else {
                items[findV1] = items[findV1] + items[findV2];
                items[findV2] = findV1;
            }
        }
    }

    /* Returns the find of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        validate(vertex);
        while (items[vertex]>=0){
            vertex = items[vertex];
        }
        return vertex;
    }

}
