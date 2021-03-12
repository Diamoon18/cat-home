package model;

public class Ghost {
	private static int x;
	private static int y;
	
	private int speed;
	
	boolean move = false;

	public Ghost(int startX, int startY, int speed){
		x = startX;
		y = startY;
		this.speed = speed;
	}
	
	
	public static int getX() {return x;}
	public static int getY() {return y;}
	
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	
	public void updateGhost() {
		if (x == 54) {
			move = true;
		}
		if (x == 432) {
			move = false;
		}
		if(move) {
			x+=speed;
		}else {
			x-=speed;
		}
	}
}
