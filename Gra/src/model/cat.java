package model;


public class cat {
	
	private static int x, y;
	private static int w, h;
	
	public static int speed, lives, bonus;
	
	
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	
	
	public cat() {
		x = 10;
		y = 40;
		w = 100;
		h = 100;
		
		speed = 10;
		lives = 9;
		bonus = 0;
	}
	
	public static int getX() { return x; }
	public static int getY() { return y; }
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	
	
	public static int getW() { return w; }
	public static int getH() { return h; }
	
	public static int getLive() { return lives; }
	public void setLive(int lives) { this.lives = lives; }
	public static int getBonus() { return bonus; }
	public void setBonus(int bonus) { this.bonus = bonus; }
	


	public void update() {
		if (down && y < Board.HEIGHT - h) {
			y+=speed;
		} 
		if (up && y > 0) {
			y-=speed;
		} 
		if (left && x > 0) {
			x-=speed;
		} 
		if (right && x < Board.WIDTH - w) {
			x+=speed;
		} 
	}
    
}
