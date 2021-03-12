package view;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import model.Button;

public class MenuView {
	
	public static boolean click = false; 
	
	Image img = new ImageIcon("src/resourses/catti.jpg").getImage();
	
	public Button [] menuButtons = {new Button(50, 300, 130, 50,"Play"), new Button(50, 400, 130, 50,"Help"), new Button(50, 500, 130, 50,"Exit")};
	
	
	public void draw(Graphics2D g) {
		g.drawImage(img, 0, 0, null);
		
		for (Button i :menuButtons) {
			i.draw(g);
		}
	}
}
