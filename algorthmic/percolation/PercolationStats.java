/************************************************************************
 * PercolationStats.java by Tony Matts
 * Programming Assignment 1
 * CSCI 332
 * Spring '14
 ***********************************************************************/
public class PercolationStats {

 private double[] threshold;
 private int n, t;
 
 //-------------------------------------------------------------------
 // perform T independent computational experiments on an N-by-N grid
 //-------------------------------------------------------------------
 public PercolationStats(int N, int T) {
  n = N;
  t = T;
  
  if (n <= 0 || t <= 0)
   throw new java.lang.IllegalArgumentException();
  
  threshold = new double[t];
  
  for (int k = 0; k < threshold.length; k++) {
   double cell = 0;
   int i, j;
   Percolation grid = new Percolation(n);
   while (!grid.percolates()) {
    i = StdRandom.uniform(n)+1;
    j = StdRandom.uniform(n)+1;
    if (!grid.isOpen(i, j)) {
     cell++;
     grid.open(i, j);
    }
   }
   threshold[k] = cell/(n*n);
  } 
 }
 
 //-------------------------------------------------------------------
 // sample mean of percolation threshold
 //-------------------------------------------------------------------
 public double mean() {
  return StdStats.mean(threshold);
 }
 
 //-------------------------------------------------------------------
 // sample standard deviation of percolation threshold
 //-------------------------------------------------------------------
 public double stddev() {
  return StdStats.stddev(threshold);
 }
 
 //-------------------------------------------------------------------
 // returns lower bound of the 95% confidence interval
 //-------------------------------------------------------------------
 public double confidenceLo() {
  return mean() - (1.96*stddev())/(Math.sqrt(t));
 }
 
 //-------------------------------------------------------------------
 // returns upper bound of the 95% confidence interval
 //-------------------------------------------------------------------
 public double confidenceHi() {
  return mean() + (1.96*stddev())/(Math.sqrt(t)); 
 }
 
 //-------------------------------------------------------------------
 // test client, described below
 //-------------------------------------------------------------------
 public static void main(String[] args) {
  int numN = StdIn.readInt();
  int numT = StdIn.readInt();
  PercolationStats percolations = new PercolationStats(numN, numT);
  
  System.out.println("mean                    = " + percolations.mean());
  System.out.println("stddev                  = " + percolations.stddev());
  System.out.println("95% confidence interval = " + percolations.confidenceLo()
       + ", " + percolations.confidenceHi());
 }
}