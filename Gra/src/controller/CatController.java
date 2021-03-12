package controller;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import model.Board;


import model.Map;


import view.MenuView;


public class CatController implements MouseListener, KeyListener, MouseMotionListener {

	Map m = new Map();
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Board.mouseX = e.getX();
		Board.mouseY = e.getY();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_A) {
			Board.myCat.left = true;
		}
		if (key == KeyEvent.VK_D) {
			Board.myCat.right = true;
		}
		if (key == KeyEvent.VK_S) {
			Board.myCat.down = true;
		}
		if (key == KeyEvent.VK_W) {
			Board.myCat.up = true;
		}
		if (key == KeyEvent.VK_LEFT) {
			Board.myCat.left = true;
		}
		if (key == KeyEvent.VK_RIGHT) {
			Board.myCat.right = true;
		}
		if (key == KeyEvent.VK_DOWN) {
			Board.myCat.down = true;
		}
		if (key == KeyEvent.VK_UP) {
			Board.myCat.up = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_A) {
			Board.myCat.left = false;
		}
		if (key == KeyEvent.VK_D) {
			Board.myCat.right = false;
		}
		if (key == KeyEvent.VK_S) {
			Board.myCat.down = false;
		}
		if (key == KeyEvent.VK_W) {
			Board.myCat.up = false;
		}
		if (key == KeyEvent.VK_LEFT) {
			Board.myCat.left = false;
		}
		if (key == KeyEvent.VK_RIGHT) {
			Board.myCat.right = false;
		}
		if (key == KeyEvent.VK_DOWN) {
			Board.myCat.down = false;
		}
		if (key == KeyEvent.VK_UP) {
			Board.myCat.up = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		MenuView.click = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		MenuView.click = false;
		
	}

}
