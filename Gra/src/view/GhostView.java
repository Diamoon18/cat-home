package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import model.Board;


public class GhostView {
	Image img = new ImageIcon("src/resourses/ghost.png").getImage(); 
	public static Rectangle ghost;
	
	public void draw(Graphics2D g) {
		g.drawImage(img, Board.ghosti.getX() , Board.ghosti.getY(), null);
		g.setColor(Color.CYAN);
		ghost = new Rectangle(Board.ghosti.getX()+5, Board.ghosti.getY()+5,40, 40);
		//g.draw(ghost);
					
	}
}
