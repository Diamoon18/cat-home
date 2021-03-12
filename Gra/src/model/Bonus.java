package model;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bonus {
	
	private int x, y;
	
	public Image img;
	private boolean visiable = true;
	private Rectangle pow;
	
	public Bonus(int StartX, int StartY) {
		img = new ImageIcon("src/resourses/bonusS.png").getImage();
		setVisiable(true);
		x = StartX;
		y = StartY;
		setPow(new Rectangle(x+15, y+15, 15, 15));
	}


	public Rectangle getPow() {
		return pow;
	}
	
	public void setVisiable(boolean vis) {
		this.visiable = vis;
	}
	
	public boolean getVisiable() {
		return visiable;
	}

	public int getX() {return x;}
	public int getY() {return y;}

	public void setPow(Rectangle pow) {
		this.pow = pow;
	}
	
	public static void locBonus(Map m, ArrayList<Bonus> b) {
		for(int y = 0; y < 14; y++) {
			for(int x = 0; x < 14; x++) {
				if(m.getMap(x, y).equals("B")) {
						b.add(new Bonus(x * 54, y * 54));
				}
			}
		}
	}
	
}
