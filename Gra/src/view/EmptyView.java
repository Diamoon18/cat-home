package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import model.Map;

public class EmptyView {
	Map m;
	public static ArrayList<Rectangle> empty = new ArrayList<Rectangle>();
	
	public void draw(Graphics2D g) {
		m = new Map();
		for(int y = 0; y < 14; y++) {
			for(int x = 0; x < 14; x++) {
				if(m.getMap(x, y).equals("n")) {
					g.setColor(Color.DARK_GRAY);
					g.fillOval(x*55, y*55, 40, 40);
					empty.add(new Rectangle(x*56, y*56, 40, 40));
	
				}
			}
		}
	}
}
