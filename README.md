# Cat&home - Maze 2d Java
A game based on the **model, view, controller** structure.\
Purpose of the game: help the cat get home.\
Collect paw bonuses to win the game.(min 4) \
If you collect the bonus you will be automatically transferred to the ghost.\
Watch out for ghosts, they can take all the paw bonuses.\
Be careful! If you hit the wall, the cat will lose one life.\
Avoid the dog and the trap, otherwise, you will lose or lose one life.\
Standard control:\
W - go forward\
A - go left\
S - go back\
D - go right
## Game in action
https://youtu.be/-CY_I-W8RMI
## Screenshots - Menu
![Start_menu](https://user-images.githubusercontent.com/72127610/111050750-c60d2d80-844e-11eb-9c8c-00d67080d0a1.jpg)
## Play
![Play_mode](https://user-images.githubusercontent.com/72127610/111050797-040a5180-844f-11eb-991f-d4a5880b4744.jpg)
## Help
![Help](https://user-images.githubusercontent.com/72127610/111050801-066cab80-844f-11eb-9092-39e61699b0b7.jpg)
## Game Over
![GameOver](https://user-images.githubusercontent.com/72127610/111050805-0a98c900-844f-11eb-80a3-f9c409bcf864.jpg)
## Win
![Win](https://user-images.githubusercontent.com/72127610/111050807-0bc9f600-844f-11eb-825b-a24e4825ac5a.jpg)
## Explain code
Model - game logic. \
View - drawing game objects. \
Controller - interaction with the user.
## 1) Controller
```Main controller``` - this is the entry point of the game. Here we have the main method. \
The Board is a panel where the game takes place.\
Here we put the Board to the center of the JFrame container. 
```java   
setContentPane(new Board()); 
```
This line sets the size of the window. \ 
Also set the name of the application.
I have not set up the ability to change the size of the application. 
```java   
setSize(WIDTH, HEIGHT); 
setTitle("CAT&HOME");
setResizable(false);
```
This ```setDefaultCloseOperation``` will close the application when we click on the close button. \
Passing null to the setLocationRelativeTo() method centers the window on the screen.
``` java
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
```
We create an instance of our code example and make it visible on the screen. 
```java
public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainController ex = new MainController();
            ex.setVisible(true);
        });
}
```
```CatController``` - cat movements, cat control. \
``` implements MouseListener, KeyListener, MouseMotionListener ``` - mouse and keyboard support. \
```mouseMoved``` - follows the position of the mouse (for the menu)
```java
@Override
	public void mouseMoved(MouseEvent e) {
		Board.mouseX = e.getX();
		Board.mouseY = e.getY();
}
```
Keeps track of the keys pressed by the user and moves the cat in that direction, as long as the key is pressed.
```java
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
    ...
}
```
When the key is not pressed, the cat doesn't move.
```java
public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_A) {
			Board.myCat.left = false;
		}
    ...
}
```
The mouse button is pressed or not (for the start menu)
```java
@Override
	public void mousePressed(MouseEvent arg0) {
		MenuView.click = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		MenuView.click = false;
		
	}
```
## 2) Model
```Board model ``` - here is the main logic of the game. \
Three main functions: \
```gameRender()``` - is responsible for the Menu and Help keys in the Play state. \
Also call the startGame function and render-draw game.
```java
public void gameRender() {
	mapka = new MapView(g);
	for(Button i:mapka.but) {
		pasteButton(i);
	}
	startGame();
}
```
```gameDraw()``` - draws the game on the screen.
``` java	
private void gameDraw() {
	Graphics g2 = this.getGraphics();
	g2.drawImage(image,0,0,null);
	g2.dispose();
}
```
```gameUpdate() ``` - call the functions of moving game objects.
```java
private void gameUpdate() {
    	myCat.update();
    	myDog.updateDog();
    	ghosti.updateGhost();
     }
```
Depending on the state of the game, it call the necessary functions.
``` java
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
```
```pasteButton()``` - whether the button was pressed. \
If the coordinate of the mouse is on the button,
``` java
if (mouseX > e.getX() && mouseX < e.getX()+e.getW() &&
	mouseY > e.getY() && mouseY < e.getY()+e.getH()) {
```
then find out what this button is.\
Changing the color and text of the button. \
If we press the button, then the state changes in this case to play. \
If the coordinate of the mouse is not on the button, then don't change the labels and colors of the buttons. 
```java
if(e.equals(menu.menuButtons[0]) || e.equals(help.wroc[1]) || e.equals(end.ending[1]) || e.equals(win.ending[1])) {
	e.color1 = Color.GREEN;
	e.s = "Grać";
	if (MenuView.click) {
		stan = BoardEnum.PLAY;
		MenuView.click = false;
	}
} ...
else {
	if(e.equals(menu.menuButtons[0]) || e.equals(help.wroc[1]) || e.equals(end.ending[1]) || e.equals(win.ending[1])) {e.color1 = Color.ORANGE ;e.s = "Play";}
	...
```
```startGame()``` - main logic in the game. \
Checking the coalition of the cat with the wall.\
Adding music that symbolizes the loss of a life.\
Game sleep for a second.\ 
And the cat returns to the starting point without one live.\
By analogy with the trap.
```java
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
```
Checking the coalition of the cat with the paw bonus.\
Adding music that symbolizes the collect paw bonus.\
Transferred to the ghost.\ 
Adding one bonus to cat score.\
```java
for(int i = 0; i < BonusView.bon.size(); i++) {
	if(CatView.catt.intersects(BonusView.bon.get(i).getPow())) {
		Music.PlayMusic("src/resourses/bonus.wav");
		myCat.setX(50);
		myCat.setY(410);
		myCat.bonus++;
		break;
	}
}
```
Checking the coalition of the cat with the home and whether the cat has 4 paw bonuses.\
Then change the state of the game to Win.\
Adding win music.\
Setting objects in initial coordinates.
``` java
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
```
Checking the coalition of the cat with the ghost.\
Adding music.\
The cat returns to the starting point without paw bonuses.\
```java
if(CatView.getRectangleCat().intersects(GhostView.ghost)){
	Music.PlayMusic("src/resourses/ghost.wav");
	myCat.setX(10);
	myCat.setY(40);
	myCat.setBonus(0);
}
```
Checking the coalition of the cat with the dog.\
The cat returns to the starting point without paw bonuses and lives.(game over)\
```java
if(CatView.getRectangleCat().intersects(DogView.doggi)){
	myCat.setLive(0);
	myCat.setBonus(0);
	myCat.setX(10);
	myCat.setY(40);
}
```
If the cat has less than one life, change state of the game to GameOver.\
Adding GameOver music.\
Setting objects in initial coordinates, lifes and paw bonuses.
```java
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
```
```Cat model``` - object cat, logic of the cat.\
Variable initialization:
```java
	private static int x, y;
	private static int w, h;
	public static int speed, lives, bonus;
	
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
```
Setting the initial data in the constructor.
```java
public cat() {
	x = 10;
	y = 40;
	w = 100;
	h = 100;

	speed = 10;
	lives = 9;
	bonus = 0;
}
```
Moving the cat in different directions.
``` java 
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
```
By analogy with ```Dog model and Ghost model```.
```Button model``` - object Button, logic of the button.
Variable initialization:
```java
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
```
Drawing function for the Button.\
Drawing rectangle and name of the Button.
```java
public void draw(Graphics2D g) {
	g.drawRect(x, y, w, h);
	g.setColor(color1);
	g.fillRect(x, y, w, h);

	Font font = new Font("Arial", Font.BOLD, 22);
	g.setFont(font);
	g.setColor(Color.BLACK);
	g.drawString(s, x+32, y+30);
}
```
```Help model``` - rules in the game, read from a text file.\
Variable initialization and constructor:
```java
	private Scanner m;
	private ArrayList<String> info = new ArrayList<String>();

	public Help() {
		openFile();
		readFile();
		closeFile();
	}

```
Opening the file, read(add to list) and close.
```java
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
```
```Map model``` - map in the game, read from a text file.\
In this text file:\
X - wall\
B - paw bonus\
n - trap\
F - home\
o - empty space\
Variable initialization and constructor:
```java
	private Scanner m;
	private String Map[] = new String[14];
	
	public Map() {
		openFile();
		readFile();
		closeFile();
	}
```
Opening the file, read(add to table Map) and close.
```java
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
```
This function is for understanding which object will need to be drawn in view.(featching a character from an array)
```java
public String getMap(int x, int y) {
	String index = Map[y].substring(x, x + 1);
	return index;
}
```
```Music model``` - for sounds in the game.\
One function ```playMusic```:\
Open music file and play, if it exists.
```java
try {
	File musicPath = new File(path);
	if(musicPath.exists()) {
		AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
		Clip clip = AudioSystem.getClip();
		clip.open(audioInput);
		clip.start();
	}else {
		System.out.print("Not found!");
	}

} catch (Exception e) {
		e.printStackTrace();
}
```
## 3) View
```CatView``` - draw the cat.
Variable initialization:
```java
    Image img = new ImageIcon("src/resourses/catmalL.png").getImage();
    public static Rectangle catt;
```
```Draw function```\
Draw image of the cat.\
Set color of the rectangle catt.\
This rectangle needed for the **intersects method**, which is defined in the Rectangle class. (Check coalison)
```java
	g.drawImage(img, cat.getX(),cat.getY(), null);
	g.setColor(Color.RED);
	catt = new Rectangle(cat.getX()+30,cat.getY()+30,20,20);
	//g.draw(catt);
```
By anology with ```Dog view, Ghost view```.\
```Bonus view```
Variable initialization:
```java
	Map m = new Map();
	public static ArrayList<Bonus> bon = new ArrayList<Bonus>();
```
```Draw function``` - drawing bonuses.\
locBonus(Map m, ArrayList<Bonus> b) - this function looks for bonuses in the text file.
```java
	Bonus.locBonus(m, bon);
	for (Bonus sc : bon) {
		g.drawImage(sc.img, sc.getX(), sc.getY(), null);
		g.setColor(Color.BLACK);
		//g.draw(sc.getPow());
	}
```
```Empty view``` - trap view.\
If ```getMap()``` returns n, than draw gray oval and make new Rectangle for check coalition.
```java
	m = new Map();
	for(int y = 0; y < 14; y++) {
		for(int x = 0; x < 14; x++) {
			if(m.getMap(x, y).equals("n")) {
				g.setColor(Color.DARK_GRAY);
				g.fillOval(x*55, y*55, 40, 40);
				empty.add(new Rectangle(x*56, y*56, 40, 40));
			}
		}
	}
```
By anology with ```Wall view, Home view```
```Menu view```
Variable initialization:\
Background image for the menu.\
Creating a Button table.
```java
	public static boolean click = false; 
	Image img = new ImageIcon("src/resourses/catti.jpg").getImage();
	public Button [] menuButtons = {new Button(50, 300, 130, 50,"Play"), new Button(50, 400, 130, 50,"Help"), new Button(50, 500, 130, 50,"Exit")};
```
```draw(Graphics2D g)``` - drawing background image and Buttons.
```java
	g.drawImage(img, 0, 0, null);
	for (Button i :menuButtons) {
		i.draw(g);
	}
```
```Map view``` - drawing in the screen every object of the game(state Play).\
Variable initialization:	
```java
	CatView catt;
	DogView dog; 
	WallView wall;
	BonusView bon;
	HomeView home;
	EmptyView em;
	GhostView gh;
	public Button but[] = {new Button(500, 5, 100, 40,"Help"),new Button(610, 5, 100, 40,"Menu")};
```
In the constructor drawing a black background.
```java
	g.setColor(Color.BLACK);
	g.fillRect(0, 0, 750, 750);
		
```
Displaying the number of lives and bonuses on the screen.
```java
	g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
	g.setColor(Color.WHITE);
	g.drawString("Live:" + cat.getLive(), 0, 20);
	g.drawString("Pow:" + cat.getBonus(), 0, 40);
```
Call the drawing function of individual objects.\
```java
	bon = new BonusView();
	bon.draw(g);

	home = new HomeView();
	home.draw(g);

	em = new EmptyView();
	em.draw(g);

	wall = new WallView();
	wall.draw(g);

	for(Button i: but) {
		i.draw(g);
	}

	catt = new CatView();
	catt.draw(g);

	dog = new DogView();
	dog.draw(g);

	gh = new GhostView();
	gh.draw(g);
```
