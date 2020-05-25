package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private WeightedQuickUnionUF uf;
	private WeightedQuickUnionUF ufExcludeBottom;
	private int size;
	private int[] openOrNot;
	private int virtualTop;
	private int virtualBottom;
	private int openSites;

	public Percolation(int N){
		if (N<=0) {
			throw new IllegalArgumentException("illegal argument!");
		}
		uf = new WeightedQuickUnionUF(N * N+2);
		ufExcludeBottom = new WeightedQuickUnionUF(N*N+1);
		size = N;
		openOrNot = new int[N*N];
		virtualTop = N*N;
		virtualBottom = N*N +1 ;
		openSites = 0;
	}

//	private void initUF(){
//		for (int i = 0; i < size; i++) {
//			uf.union(i,virtualTop);
//		}
//		for (int j = virtualTop-size; j < virtualTop; j++ ) {
//			uf.union(j, virtualBottom);
//		}
//	}

	public void open(int row, int col){
		validate(row,col);

		if (isOpen(row, col)) return;

		int index = xyTo1D(row,col);
		openOrNot[index] = 1;

		if (row == 0) {
			uf.union(index,virtualTop);
			ufExcludeBottom.union(index,virtualTop);
		}
		if (row == size-1) {
			uf.union(index,virtualBottom);
		}

		if (row>0 && isOpen(row-1,col)) {
			uf.union(index,xyTo1D(row-1,col));
			ufExcludeBottom.union(index,xyTo1D(row-1,col));
		}
		if (row<size-1 && isOpen(row+1,col)) {
			uf.union(index,xyTo1D(row+1,col));
			ufExcludeBottom.union(index,xyTo1D(row+1,col));
		}
		if (col>0 && isOpen(row,col-1)) {
			uf.union(index,xyTo1D(row,col-1));
			ufExcludeBottom.union(index,xyTo1D(row,col-1));
		}
		if (col<size-1 && isOpen(row,col+1)) {
			uf.union(index, xyTo1D(row, col + 1));
			ufExcludeBottom.union(index, xyTo1D(row, col + 1));
		}

		openSites++;
	}

	public boolean isOpen(int row, int col) {
		validate(row, col);
		return openOrNot[xyTo1D(row,col)] == 1;
	}

	public boolean isFull(int row, int col) {
		validate(row,col);
		return ufExcludeBottom.connected(xyTo1D(row,col),virtualTop);
	}

	public int numberOfOpenSites() {
		return openSites;
	}

	public boolean percolates() {
		return uf.connected(virtualTop,virtualBottom);
	}

	private void validate(int r, int c){
		if (r<0 || r>=size || c<0 || c>=size){
			throw new IndexOutOfBoundsException();
		}
	}


	private int xyTo1D(int row,int col){
		return row*size + col;
	}

	public static void main(String[] args) {

	}
}
