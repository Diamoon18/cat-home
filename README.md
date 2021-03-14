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
Model - game logic \
View - drawing game objects \
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
2) Model
