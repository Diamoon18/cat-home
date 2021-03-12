package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
	
	private Scanner m;
	
	
	private String Map[] = new String[14];
	
	public Map() {
		openFile();
		readFile();
		closeFile();
	}
	
	public String getMap(int x, int y) {
		String index = Map[y].substring(x, x + 1);
		return index;
	}
	
	public void openFile() {
		try {
			m = new Scanner(new File("src/resourses/level1.txt"));
		} catch (FileNotFoundException e) {
			System.out.print("Not found!");
			e.printStackTrace();
		}
	}
	
	public void readFile() {
		while(m.hasNext()) {
			for(int i = 0; i < 14; i++) {
				Map[i] = m.next();
			}
		}
	}
	
	public void closeFile() {
		m.close();
	}
		
}
