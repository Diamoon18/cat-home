package model;

public class Dog {
	private static int x;
	private static int y;
	
	private int speed;
	
	boolean up = false;

	public Dog(int startX, int startY, int speed){
		x = startX;
		y = startY;
		this.speed = speed;
	}
	
	
	public static int getX() {return x;}
	public static int getY() {return y;}
	
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	
	public void updateDog() {
		if (y == 630) {
			up = false;
		}
		if (y == 580) {
			up = true;
		}
		if(up) {
			y+=speed;
		}else {
			y-=speed;
		}
	}
}
