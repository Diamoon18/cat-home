package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Help {
	private Scanner m;
	private ArrayList<String> info = new ArrayList<String>();;
	
	public Help() {
		openFile();
		readFile();
		closeFile();
	}
	
	public ArrayList<String> getInfo() {
		return info;
	}
	
	public void openFile() {
		try {
			m = new Scanner(new File("src/resourses/help.txt"));
		} catch (FileNotFoundException e) {
			System.out.print("Not found!");
			e.printStackTrace();
		}
	}
	
	public void readFile() {
		while (m.hasNextLine())  {
			info.add(m.nextLine());	
		}
	}
	
	public void closeFile() {
		m.close();
	}
}
