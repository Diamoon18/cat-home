package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import model.Bonus;
import model.Map;

public class BonusView {
	
	Map m = new Map();
	public static ArrayList<Bonus> bon = new ArrayList<Bonus>();
	
	public void draw(Graphics2D g) {
		Bonus.locBonus(m, bon);
		for (Bonus sc : bon) {
			g.drawImage(sc.img, sc.getX(), sc.getY(), null);
			g.setColor(Color.BLACK);
			//g.draw(sc.getPow());
		}
	}
}