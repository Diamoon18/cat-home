package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import model.Board;
import model.Button;

public class WinView {
	
	Image img = new ImageIcon("src/resourses/gameover.png").getImage();
	public Button ending[] = {new Button(320, 450, 120, 40,"Menu"), new Button(490, 450, 120, 40, "Play")};
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.PINK);
		g.fillRect(0, 0, 750, 750);
		g.drawImage(img, 0, 0, null);
		
		Font fontt = new Font("Arial", Font.BOLD, 60);
		g.setFont(fontt);
		g.setColor(Color.WHITE);
		g.drawString("Cat at home", 285, 350);
		Font fontScore = new Font("Arial", Font.BOLD, 40);
		g.setFont(fontScore);
		g.drawString("Pows:" + Board.powScore, 400, 400);
		
		for(Button b: ending) {
			b.draw(g);
		}
		
	}
}
