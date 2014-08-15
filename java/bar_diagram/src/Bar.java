//==================================================
//Bar.java by Tony Matts
//
//Bar object class
//==================================================
public class Bar {

	//vars
	int width, height;
	
	//constructor overload
	public Bar(int w, int h) {
		width = w;
		height = h;
	}
	
	//constructor initializer
	public Bar() {
		width = 0;
		height = 0;
	}
	
	//setters & getters
	public void setWidth(int a) {
		width = a;
	}
	
	public void setHeight(int b) {
		height = b;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
