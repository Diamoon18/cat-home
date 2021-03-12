package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Button{
	private int x;
	private int y;
	private int w;
	private int h;
	
	public Color color1;

	public String s;
	
	public Button(int x, int y, int w, int h, String s){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.s = s;
		color1 = Color.ORANGE;
	}

	public int getX() { return x; }
	public int getY() { return y; }
	public int getW() { return w; }
	public int getH() { return h; }
	
	
	public void draw(Graphics2D g) {
		g.drawRect(x, y, w, h);
		g.setColor(color1);
		g.fillRect(x, y, w, h);
		
		Font font = new Font("Arial", Font.BOLD, 22);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(s, x+32, y+30);
	}
}
