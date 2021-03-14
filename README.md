# Cat&home - Maze 2d Java
A game based on the **model, view, controller** structure.\
Purpose of the game: help the cat get home.\
Collect paw bonuses to win the game.(min 4) \
If you collect the bonus you will be automatically transferred to the ghost.\
Watch out for ghosts, they can take all the paw bonuses.\
Be careful! If you hit the wall, the cat will lose one life.\
Avoid the dog and the traps, otherwise, you will lose or lose one life.\
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
Also call the startGame function.
```java
public void gameRender() {
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
then find out what this button is.
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
Game sleep for a second. \ 
And the cat returns to the starting point without one live.
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
