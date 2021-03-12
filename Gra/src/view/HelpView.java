package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import model.Button;
import model.Help;


public class HelpView {
	
	Help inf = new Help();
	
	Image imgKl = new ImageIcon("src/resourses/klawisze.png").getImage();
	Image imgBon = new ImageIcon("src/resourses/bonusS.png").getImage();
	Image imgHome = new ImageIcon("src/resourses/home.png").getImage();
	Image imgDog = new ImageIcon("src/resourses/doggiP.png").getImage();
	Image imgCat = new ImageIcon("src/resourses/catmalL.png").getImage();
	Image imgWall = new ImageIcon("src/resourses/wall(1).png").getImage();
	Image imgGh = new ImageIcon("src/resourses/ghost.png").getImage();
	
	public Button wroc[] = {new Button(100, 650, 120, 40,"Menu"), new Button(240, 650, 120, 40, "Play")};
	
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 750, 750);
		Font fontt = new Font("Arial", Font.BOLD, 26);
		g.setFont(fontt);
		g.setColor(Color.ORANGE);
		g.drawString("Pomoc", 100, 30);
		
		g.drawImage(imgCat, 350, 5, null);
		g.drawImage(imgHome, 400, 20, null);
		g.drawImage(imgBon, 370, 150, null);
		g.drawImage(imgDog, 550, 250, null);
		g.drawImage(imgKl, 300, 350, null);
		g.drawImage(imgWall, 450, 190, null);
		g.drawImage(imgGh, 470, 90, null);
		
		g.setColor(Color.GRAY);
		g.fillOval(600, 260, 40, 40);
		
		for(Button b: wroc) {
			b.draw(g);
		}
		
		for (int i = 0; i < inf.getInfo().size(); i++) {
			Font font = new Font("Arial", Font.BOLD, 17);
			g.setFont(font);
			g.setColor(Color.ORANGE);
			g.drawString(inf.getInfo().get(i), i+10, (i+1)*60);
		}
	}
}