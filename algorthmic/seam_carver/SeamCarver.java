import java.awt.Color;

public class SeamCarver {
 
 	private Picture picture;
 	private double[] value;
 	private double[] energy;
 	private int[] edge;
 	private double min;
 	private int bottom;
 	private int[] seam;

    public SeamCarver(Picture picture) {
     	this.picture = new Picture(picture);
    }
   
    // current picture
    public Picture picture() {
     	return new Picture(this.picture);
    }
   
    // width  of current picture
    public int width() {
     	return picture.width();
    } 
   
    // height of current picture                        
    public int height() {
     	return picture.height();
    }        
    
    private double delta(Color p, Color m) {
     	return Math.pow(p.getRed() - m.getRed(), 2) +
         Math.pow(p.getGreen() - m.getGreen(), 2) +
         Math.pow(p.getBlue() - m.getBlue(), 2);
    }
    // energy of pixel at column x and row y in current picture                
    public double energy(int x, int y) {
     	//check if x&y are within bounds of pic
     	if (x < 0 || y < 0 || x >= width() || y >= height())
            throw new java.lang.IndexOutOfBoundsException();
            
     	if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1)
      		return 195075;//(255^2)*3
     	else { 
      		Color Cxp = picture.get(x+1,y);
      		Color Cxm = picture.get(x-1,y);
      		Color Cyp = picture.get(x,y+1);
      		Color Cym = picture.get(x,y-1);
      		double deltax2 = delta(Cxp, Cxm);
      		double deltay2 = delta(Cyp, Cym);
      		return deltax2 + deltay2;
     	}
    }  
   
    // sequence of indices for horizontal seam in current picture          
    public int[] findHorizontalSeam() {
     	int index;
     	value = new double[area()];
     	energy = new double[area()];
     	edge = new int[area()];
     	min = Double.POSITIVE_INFINITY;
     	bottom = 0;
     	seam = new int[width()];
     
     	for (int row = 0; row < height(); row++) {
      		for (int col = 0; col < width(); col++) {
       			
       			index = convert2d(col, row);
       			
       			if (col == 0)
        			value[index] = 0;
       			else
        			value[index] = Double.POSITIVE_INFINITY;
        			
       			edge[index] = -1;
       			energy[index] = energy(col, row);
      		}
     	}
     
     	for (int col = 0; col < width()-1; col++) {
      		for (int row = 0; row < height(); row++) {
       			
       			index = convert2d(col, row);
       
       			if (row-1 >= 0) {
        			if (value[convert2d(col+1, row-1)] > value[index] + energy[convert2d(col+1, row-1)]) {
         				value[convert2d(col+1, row-1)] = value[index] + energy[convert2d(col+1, row-1)];
         				edge[convert2d(col+1, row-1)] = index;
        			}
       			}
       			if (value[convert2d(col+1, row)] > value[index] + energy[convert2d(col+1, row)]) {
         			value[convert2d(col+1, row)] = value[index] + energy[convert2d(col+1, row)];
         			edge[convert2d(col+1, row)] = index;
       			} 
       			if (row+1 < height()) {
        			if (value[convert2d(col+1, row+1)] > value[index] + energy[convert2d(col+1, row+1)]) {
         				value[convert2d(col+1, row+1)] = value[index] + energy[convert2d(col+1, row+1)];
         				edge[convert2d(col+1, row+1)] = index;
        			} 
       			}
      		}
     	}
     
     	for (int row = 0; row < height(); row++) {
      		if (value[convert2d(width()-1, row)] < min) {
       			min = value[convert2d(width()-1, row)];
       			bottom = convert2d(width()-1, row);
      		}
     	}
     
     	while (bottom >= 0) {
      		seam[bottom % width()] = (bottom / width()); 
      		bottom = edge[bottom];
     	}
     
     	return seam;
    }
   
    // sequence of indices for vertical seam in current picture            
    public int[] findVerticalSeam() {
     	int index;
     	value = new double[area()];
     	energy = new double[area()];
     	edge = new int[area()];
     	min = Double.POSITIVE_INFINITY;
     	bottom = 0;
     	seam = new int[height()];
     
     	for (int col = 0; col < width(); col++) {
      		for (int row = 0; row < height(); row++) {
       			
       			index = convert2d(col, row);
       			
       			if (row == 0)
        			value[index] = 0;
       			else
        			value[index] = Double.POSITIVE_INFINITY;
        			
       			edge[index] = -1;
       			energy[index] = energy(col, row);  
      		}
     	}
     
     	for (int row = 0; row < height()-1; row++) {
      		for (int col = 0; col < width(); col++) {
       			
       			index = convert2d(col, row);
       			
       			if (col-1 >= 0) {
        			if (value[convert2d(col-1, row+1)] > value[index] + energy[convert2d(col-1, row+1)]) {
         				value[convert2d(col-1, row+1)] = value[index] + energy[convert2d(col-1, row+1)];
         				edge[convert2d(col-1, row+1)] = index; 
        			}
       			}
       			
       			if (value[convert2d(col, row+1)] > value[index] + energy[convert2d(col, row+1)]) {
         			value[convert2d(col, row+1)] = value[index] + energy[convert2d(col, row+1)];
         			edge[convert2d(col, row+1)] = index;  
       			}
       			
       			if (col+1 < width()) {
        			if (value[convert2d(col+1, row+1)] > value[index] + energy[convert2d(col+1, row+1)]) {
         				value[convert2d(col+1, row+1)] = value[index] + energy[convert2d(col+1, row+1)];
         				edge[convert2d(col+1, row+1)] = index; 
        			}
       			}
      		}
     	}
     
     	for (int col = 0; col < width(); col++) {
      		if (value[convert2d(col, height()-1)] < min) {
       			min = value[convert2d(col, height()-1)];
       			bottom = convert2d(col, height()-1);
      		}
     	}
     
     	while (bottom >= 0) {
      		seam[bottom / width()] = (bottom % width()); 
      		bottom = edge[bottom];
     	}
     
     	return seam;
    }
    
    public void removeHorizontalSeam(int[] a) {
        if (height() <= 1)
            throw new java.lang.IllegalArgumentException();

        if (a.length != width())
            throw new java.lang.IllegalArgumentException();

        Picture newPic = new Picture(width(), height() - 1);
        int prev = a[0];

        for (int col = 0; col < width(); col++) {
            if (a[col] < 0 || a[col] >= height())
                throw new java.lang.IndexOutOfBoundsException();

            if (a[col] < prev - 1 || a[col] > prev + 1)
                throw new java.lang.IllegalArgumentException();

            prev = a[col];

            for (int row = 0; row < height() - 1; row++) {
                if (row < prev)
                    newPic.set(col, row, picture.get(col, row));
                else
                    newPic.set(col, row, picture.get(col, row+1));
            }
        }

        picture = newPic;
    }

    public void removeVerticalSeam(int[] a) {
        if (width() <= 1)
            throw new java.lang.IllegalArgumentException();

        if (a.length != height())
            throw new java.lang.IllegalArgumentException();

        Picture newPic = new Picture(width() - 1, height());
        int prev = a[0];

        for (int row = 0; row < height(); row++) {
            if (a[row] < 0 || a[row] >= width())
                throw new java.lang.IndexOutOfBoundsException();

            if (a[row] < prev - 1 || a[row] > prev + 1)
                throw new java.lang.IllegalArgumentException();

            prev = a[row];

            for (int col = 0; col < width() - 1; col++) {
                if (col < prev)
                    newPic.set(col, row, picture.get(col, row));
                else
                    newPic.set(col, row, picture.get(col+1, row));
            }
        }

        picture = newPic;
    }
    
    private int convert2d (int c, int r) {
     	return width() * r + c;
    }
    
    private int area() {
     	return height() * width();
    }    
}