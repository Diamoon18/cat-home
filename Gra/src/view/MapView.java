package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import model.Button;
import model.cat;

public class MapView {
	CatView catt;
	DogView dog; 
	WallView wall;
	BonusView bon;
	HomeView home;
	EmptyView em;
	GhostView gh;
	
	public Button but[] = {new Button(500, 5, 100, 40,"Help"),new Button(610, 5, 100, 40,"Menu")};

	
	public MapView(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 750, 750);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		g.setColor(Color.WHITE);
		g.drawString("Live:" + cat.getLive(), 0, 20);

		g.drawString("Pow:" + cat.getBonus(), 0, 40);
		
	
		bon = new BonusView();
		bon.draw(g);
		
		home = new HomeView();
		home.draw(g);
		
		em = new EmptyView();
		em.draw(g);
		
		wall = new WallView();
		wall.draw(g);
		
		for(Button i: but) {
			i.draw(g);
		}
		
		catt = new CatView();
		catt.draw(g);
		
		dog = new DogView();
		dog.draw(g);
		
		gh = new GhostView();
		gh.draw(g);
	}
}
