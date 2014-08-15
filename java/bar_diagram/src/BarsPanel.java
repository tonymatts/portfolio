//==================================================
//BarsPanel.java by Tony Matts
//
//JPanel class for displaying bars that represent
//random int values.
//==================================================
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

public class BarsPanel extends JPanel {

	//vars
	Bar[] bars;
	int track = 0;
	
	//constructor
	public BarsPanel(int[] r) {
		bars = new Bar[r.length];
		for (int k = 0; k < r.length; k++) {
			bars[k] = new Bar();
			bars[k].setHeight(r[k]);
			
		}
		setBackground (Color.gray);
		setPreferredSize (new Dimension(510, 80));
	}
	
	//paints bars for each int value
	public void paintComponent (Graphics page) {
		super.paintComponent(page);
		
		for (int j = 0; j < bars.length; j++) {
			System.out.println(bars[j]);
			page.draw3DRect(j*10, 10, 6, bars[j].getHeight(), true);
			page.setColor(Color.blue);
		}
	}
	
	//method for repainting the bars
	public void drawBars(int[] a) {
		for (int i = 0; i < a.length; i++) {
			bars[i].setHeight(a[i]);
			repaint();
		}
	}
}
