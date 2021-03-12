package view;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import model.Map;

public class WallView {
	Image img = new ImageIcon("src/resourses/wall(1).png").getImage();
	Map m = new Map();
	public static ArrayList<Rectangle> wall = new ArrayList<Rectangle>();
	
	public void draw(Graphics2D g) {
		for(int y = 0; y < 14; y++) {
			for(int x = 0; x < 14; x++) {
				if(m.getMap(x, y).equals("X")) {
					g.drawImage(img, x * 53, y * 53, null);
					wall.add(new Rectangle(x * 54, y * 54, 55, 55));
					/*for (Rectangle i: wall) {
						g.setColor(Color.CYAN);
						g.draw(i);
					}*/
				}
			}
		}
	}
	
}
