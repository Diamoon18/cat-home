package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


import model.Board;


public class DogView {
	Image img = new ImageIcon("src/resourses/doggiP.png").getImage(); 
	public static Rectangle doggi;
	
	public void draw(Graphics2D g) {
		g.drawImage(img, Board.myDog.getX(), Board.myDog.getY(), null);
		g.setColor(Color.CYAN);
		doggi = new Rectangle(Board.myDog.getX()+10, Board.myDog.getY()+10,40, 40);
		//g.draw(doggi);
	}
}
