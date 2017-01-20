package A1;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private WeightedQuickUnionUF sites;
	private boolean[][] status;
	private int openNums;
	
	public Percolation(int n) 
	{
		// create n-by-n grid, with all sites blocked
		if (n <= 0)
		{
			throw new java.lang.IllegalArgumentException();
		}
		sites = new WeightedQuickUnionUF(n*n);
		status = new boolean[n][n];
		openNums = 0;
	}
	
	public void open(int row, int col)
	{
		// open site (row, col) if it is not open already
		if (!this.isOpen(row, col))
		{
			openNums++;
			row--; col--;
			int[] x = {0, 0, -1, 1};
			int[] y = {1, -1, 0, 0};
			for (int i=0; i<4; i++)
			{
				int tempRow = row + x[i];
				int tempCol = col + y[i];
				if (tempRow < 0 || tempCol < 0 || tempRow >= status.length || tempCol >= status.length)
				{
					continue;
				}
				this.sites.union(row*status.length+col, tempRow*status.length+tempCol);
			}
 		}
	}
	
	public boolean isOpen(int row, int col)
	{
		// is site (row, col) open?
		if (row <= 0 || col <= 0 || row > status.length || col > status.length)
		{
			throw new java.lang.IndexOutOfBoundsException();
		}
		if (status[row-1][col-1])
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isFull(int row, int col)
	{
		// is site (row, col) full?
		if (row <= 0 || col <= 0 || row > status.length || col > status.length)
		{
			throw new java.lang.IndexOutOfBoundsException();
		}
		int rows = this.status.length;
		row--; col--;
		for(int i=0; i<this.status.length; i++)
		{
			if (this.status[0][i])
			{
				if (this.sites.connected(row*rows+col, i))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public int numberOfOpenSites()
	{
		// number of open sites
		return openNums;
	}
	
	public boolean percolates()
	{
		int n = status.length;
		for (int i=0; i<n; i++)
		{
			if (status[n-1][i])
			{
				for (int j=0; j<n; j++)
				{
					if (status[0][j])
					{
						if (this.sites.connected((n-1)*n+i, j))
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
