package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;
import javax.swing.Timer;

import controller.CatController;
import view.BonusView;
import view.CatView;
import view.DogView;
import view.EmptyView;
import view.GameOverView;
import view.GhostView;
import view.HelpView;
import view.HomeView;
import view.MapView;
import view.MenuView;
import view.WallView;
import view.WinView;


public class Board extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static int WIDTH = 750, HEIGHT = 750;
	
	public static int mouseX;
	public static int mouseY;
	
	
	private static BoardEnum stan = BoardEnum.MENU;
	public static int powScore;
	
	
	Timer mainTimer = new Timer(30,this);
	
	private BufferedImage image;
	private Graphics2D g;
	
	
	public static cat myCat;
	public static Dog myDog;
	public static Ghost ghosti;
	
	MenuView menu;
	MapView mapka;
	HelpView help;
	GameOverView end;
	WinView win;
	
	
	public Board() {
		initBoard();
	}
	
	private void initBoard() {
         setFocusable(true);
         requestFocus();
         mainTimer.start();
 
         image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
         g = (Graphics2D) image.getGraphics();
         menu = new MenuView();
         myCat = new cat();
         help = new HelpView();
         mapka = new MapView(g);
         end = new GameOverView();
         win = new WinView();
         myDog = new Dog(165,630,2);
         ghosti = new Ghost(54,432,2);
         
         addMouseListener(new CatController());
         addKeyListener(new CatController());
         addMouseMotionListener(new CatController());
         
	}
	
	public void gameRender() {
		for(Button i:mapka.but) {
			pasteButton(i);
		}
		startGame();
	}
	
	private void gameDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image,0,0,null);
		g2.dispose();
	}
	
     private void gameUpdate() {
    	myCat.update();
    	myDog.updateDog();
    	ghosti.updateGhost();
     }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if (stan.equals(BoardEnum.MENU)){
			menu.draw(g);
			gameDraw();
			for(Button i:menu.menuButtons) {
				pasteButton(i);
			}
		}
		if (stan.equals(BoardEnum.PLAY)){
			gameUpdate();
			gameRender();
			gameDraw();
		}
		if (stan.equals(BoardEnum.HELP)) {
			help.draw(g);
			gameDraw();
			for(Button b: help.wroc) {
				pasteButton(b);
			}
		}
		if (stan.equals(BoardEnum.GAMEOVER)){
			end.draw(g);
			gameDraw();
			for(Button b:end.ending) {
				pasteButton(b);
			}
		}
		if (stan.equals(BoardEnum.WIN)){
			win.draw(g);
			gameDraw();
			for(Button b:win.ending) {
				pasteButton(b);
			}
		}
	}
	
	private void pasteButton(Button e) {
		if (mouseX > e.getX() && mouseX < e.getX()+e.getW() &&
				mouseY > e.getY() && mouseY < e.getY()+e.getH()) {
			if(e.equals(menu.menuButtons[0]) || e.equals(help.wroc[1]) || e.equals(end.ending[1]) || e.equals(win.ending[1])) {
				e.color1 = Color.GREEN;
				e.s = "Graæ";
				if (MenuView.click) {
					stan = BoardEnum.PLAY;
					MenuView.click = false;
				}
			}
			if(e.equals(menu.menuButtons[1]) || e.equals(mapka.but[0])) {
				e.color1 = Color.blue.brighter();
				e.s = "Pomoc";
				if (MenuView.click) {
					stan = BoardEnum.HELP;
					MenuView.click = false;
				}
			}
			if(e.equals(menu.menuButtons[2])) {
				e.color1 = Color.RED;
				e.s = "Wyjœæ";
				if (MenuView.click) {
					System.exit(0);
				}
			}
			if(e.equals(help.wroc[0]) || e.equals(mapka.but[1]) || e.equals(end.ending[0]) || e.equals(win.ending[0])) {
				e.color1 = Color.GREEN;
				e.s = "Wróæ";
				if (MenuView.click) {
					stan = BoardEnum.MENU;
					MenuView.click = false;
				}
			}
		}
		else {
			if(e.equals(menu.menuButtons[0]) || e.equals(help.wroc[1]) || e.equals(end.ending[1]) || e.equals(win.ending[1])) {e.color1 = Color.ORANGE ;e.s = "Play";}
			if(e.equals(menu.menuButtons[1]) || e.equals(mapka.but[0])) {e.color1 = Color.ORANGE; e.s = "Help";}
			if(e.equals(menu.menuButtons[2])) {e.color1 = Color.ORANGE;e.s = "Exit";}
			if(e.equals(help.wroc[0]) || e.equals(mapka.but[1]) || e.equals(end.ending[0]) || e.equals(win.ending[0])) {e.color1 = Color.ORANGE;e.s = "Menu";}
		}
	}
	
	
	private void startGame() {
		
		for(int i = 0; i < WallView.wall.size(); i++) {
			if(CatView.catt.intersects(WallView.wall.get(i))) {
				Music.PlayMusic("src/resourses/minLive.wav");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				myCat.setX(10);
				myCat.setY(40);
				myCat.lives--;
				break;
			}
		}
		
		for(int i = 0; i < BonusView.bon.size(); i++) {
			if(CatView.catt.intersects(BonusView.bon.get(i).getPow())) {
				Music.PlayMusic("src/resourses/bonus.wav");
				myCat.setX(50);
				myCat.setY(410);
				myCat.bonus++;
				break;
			}
		}
		
		
		for(int i = 0; i < EmptyView.empty.size(); i++) {
			if(CatView.catt.intersects(EmptyView.empty.get(i))) {
				Music.PlayMusic("src/resourses/minLive.wav");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				myCat.setX(10);
				myCat.setY(40);
				myCat.lives--;
				break;
			}
		}
		
		
		if(CatView.getRectangleCat().intersects(HomeView.win) && myCat.getBonus() > 3){
			stan = BoardEnum.WIN;
			Music.PlayMusic("src/resourses/win.wav");
			powScore = myCat.getBonus();
			
			myCat.setX(10);
			myCat.setY(40);
			
			myCat.setLive(9);
			myCat.setBonus(0);
			
			ghosti.setX(54);
			ghosti.setY(432);
			
			myDog.setX(165);
			myDog.setY(630);
		}
		
		if(CatView.getRectangleCat().intersects(GhostView.ghost)){
			Music.PlayMusic("src/resourses/ghost.wav");
			myCat.setX(10);
			myCat.setY(40);
			myCat.setBonus(0);
		}
		
		if(CatView.getRectangleCat().intersects(DogView.doggi)){
			myCat.setLive(0);
			myCat.setBonus(0);
			myCat.setX(10);
			myCat.setY(40);
		}
		
		if(myCat.getLive() < 1) {
			stan = BoardEnum.GAMEOVER;
		} 
		
		if(stan.equals(BoardEnum.GAMEOVER)) {
			Music.PlayMusic("src/resourses/gameOver.wav");
			myCat.setLive(9);
			myCat.setBonus(0);
			
			ghosti.setX(54);
			ghosti.setY(432);
			
			myDog.setX(165);
			myDog.setY(630);
		} 
	   
	}
}

