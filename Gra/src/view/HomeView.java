package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import model.Map;

public class HomeView {
	Image img = new ImageIcon("src/resourses/home.png").getImage();
	Map m = new Map();
	public static Rectangle win;
	
	public void draw(Graphics2D g) {
		for(int y = 0; y < 14; y++) {
			for(int x = 0; x < 14; x++) {
				if(m.getMap(x, y).equals("F")) {
					g.drawImage(img, x * 54, y * 54, null);
					win = new Rectangle(x * 54, y * 54, 40,40);
					g.setColor(Color.CYAN);
					//g.draw(win);
				}
			}
		}
	}
}
