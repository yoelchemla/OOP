package gameClient;

import api.directed_weighted_graph;
import api.edge_data;
import api.geo_location;
import api.node_data;
import gameClient.util.Point3D;
import gameClient.util.Range;
import gameClient.util.Range2D;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents a very simple GUI class to present a
 * game on a graph - you are welcome to use this class - yet keep in mind
 * that the code is not well written in order to force you improve the
 * code and not to take it "as is".
 *
 */
public class MyFrame extends JFrame{
	

	private Arena _ar;
	private jPanel panel;
	private gameClient.util.Range2Range _w2f;

	
	void setTime(long time){
		panel.setTime(time);
	}
	MyFrame(String a) {
		super(a);

	}
	public void update(Arena ar) {
		this._ar = ar;
		panel=new jPanel();
		this.add(panel);
		updateFrame();
	}

	private void updateFrame() {
		Range rx = new Range(20,this.getWidth()-20);
		Range ry = new Range(this.getHeight()-10,150);
		Range2D frame = new Range2D(rx,ry);
		directed_weighted_graph g = _ar.getGraph();
		_w2f = Arena.w2f(g,frame);
		panel.update(_ar);
		panel.repaint();	
	}
	public void paint(Graphics g) {
		updateFrame();

	}
	
	
}