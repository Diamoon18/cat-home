package controller;


import java.awt.EventQueue;

import javax.swing.JFrame;

import model.Board;



public class MainController extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public final int WIDTH = 760, HEIGHT = 790;
	 
	
	 public MainController() {
        initUI();
	 }
	
	 public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            MainController ex = new MainController();
            ex.setVisible(true);
        });
	 }
	
	 private void initUI() {

        setContentPane(new Board());

        setSize(WIDTH, HEIGHT);
        
        setTitle("CAT&HOME");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
	 }    
}
