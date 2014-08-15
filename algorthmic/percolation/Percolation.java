/************************************************************************
 * Percolation.java by Tony Matts
 * Programming Assignment 1
 * CSCI 332
 * Spring '14
 ***********************************************************************/
public class Percolation {

 private int topCell, bottomCell; //virtual top and bottom cells
 private int n;
 private WeightedQuickUnionUF wqFind;
 private int[] site; //0=Blocked Site, 1=Empty Open Site, 2=Full Open Site
 
 //-------------------------------------------------------------------
 // create N-by-N grid, with all sites blocked
 //-------------------------------------------------------------------
 public Percolation(int N) {
  n = N;
  topCell = n*n;
  bottomCell = n*n+1;
  wqFind = new WeightedQuickUnionUF(n*n+2);
  site = new int[n*n]; // may need to initialize entries to zero
 }
 
 //-------------------------------------------------------------------
 // open site (row i, column j) if it is not already
 //-------------------------------------------------------------------
 public void open(int i, int j) {
 
  //check if i and j values are within the grid
  inGrid(i, j);
  
  //check if site is open already
  if (isOpen(i, j))
   return;
  
  //gridSite is current position of i,j
  int gridSite = alteredCoords(i, j);
  
  //site at i,j is opened
  site[gridSite] = 1;
  
  //union with fake top cell
  if (i == 1 && !wqFind.connected(gridSite, topCell))
   wqFind.union(gridSite, topCell);
  
  //union with fake bottom cell
  if (i == n)
   wqFind.union(gridSite, bottomCell);
  
  
  //union with surrounding four cells
  if (i > 1) {
   if (isOpen(i-1, j)) {
    wqFind.union(gridSite, alteredCoords(i-1, j));
   }
  }
  if (i < n) {
   if (isOpen(i+1, j)) {
    wqFind.union(gridSite, alteredCoords(i+1, j));
   }
  }
  if (j > 1) {
   if (isOpen(i, j-1)) {
    wqFind.union(gridSite, alteredCoords(i, j-1));
   }
  }
  if (j < n) {
   if (isOpen(i, j+1)) {
    wqFind.union(gridSite, alteredCoords(i, j+1));
   }
  } 
 }
   
 //-------------------------------------------------------------------
 // is site (row i, column j) open?
 //-------------------------------------------------------------------
 public boolean isOpen(int i, int j) {
  inGrid(i, j);
  
  if (site[alteredCoords(i, j)] == 1)
   return true;
  return false;
 }
   
 //-------------------------------------------------------------------
 // is site (row i, column j) full?
 //-------------------------------------------------------------------
 public boolean isFull(int i, int j) {
  inGrid(i, j);
  
  //check if site is blocked
  if (!isOpen(i, j))
   return false;
  
  int gridSite = alteredCoords(i, j);
  
  if (wqFind.connected(topCell, gridSite))
   return true;
  return false;  
 }
 
 //-------------------------------------------------------------------
 // does the system percolate?
 //-------------------------------------------------------------------
 public boolean percolates() {
  if (wqFind.connected(topCell, bottomCell))
   return true;
  return false; 
 }
 
 //-------------------------------------------------------------------
 // alter i and j coordinates to work with union methods
 //-------------------------------------------------------------------
 private int alteredCoords(int i, int j) {
  int p = n*(i-1) + (j-1);
  
  return p;
 } 
 
 //-------------------------------------------------------------------
 // throw out of bounds exception if i or j are no within the grid
 //-------------------------------------------------------------------
 private boolean inGrid(int i, int j) {
  if (i < 1 || j < 1 || i > n || j > n)
   throw new IndexOutOfBoundsException();
  else
   return true; 
 }
}