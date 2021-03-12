package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


import model.cat;


public class CatView {
    Image img = new ImageIcon("src/resourses/catmalL.png").getImage();
    public static Rectangle catt;
    
	public void draw(Graphics2D g) {
		g.drawImage(img, cat.getX(),cat.getY(), null);
		g.setColor(Color.RED);
		catt = new Rectangle(cat.getX()+30,cat.getY()+30,20,20);
		//g.draw(catt);
	}
	
	public static Rectangle getRectangleCat() {
		return catt;
	}
}
