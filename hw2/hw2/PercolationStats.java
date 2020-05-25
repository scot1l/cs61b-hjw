package hw2;
import edu.princeton.cs.introcs.*;

public class PercolationStats {

	private int numOfStats;
	private double[] fractions;

	public PercolationStats(int N, int T, PercolationFactory pf) {
		if(N<=0 || T <=0) {
			throw new IllegalArgumentException("illegal argument");
		}
		fractions = new double[N*N];
		numOfStats = T;
		int totalSites = N*N;
		for (int i = 0; i < T; i++) {
			int numOfOpenSites = 0;
			Percolation percolation = pf.make(N);
			while (!percolation.percolates()) {
				int raw = StdRandom.uniform(N);
				int col = StdRandom.uniform(N);
				percolation.open(raw, col);
				numOfOpenSites ++;
			}
			fractions[i] = (double)numOfOpenSites / totalSites;
		}
	}

	public double mean() {
		return StdStats.mean(fractions);
	}

	public double stddev() {
		return StdStats.stddev(fractions);
	}

	public double confidenceLow() {
		double mu = mean();
		double sigma = stddev();
		return mu - (1.96*sigma/Math.sqrt(numOfStats));
	}

	public double confidenceHigh() {
		double mu = mean();
		double sigma = stddev();
		return mu + (1.96*sigma/Math.sqrt(numOfStats));
	}
}
