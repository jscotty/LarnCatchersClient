package game.client.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.Normalizer;

public class KeyEventListener implements KeyListener {

	private static boolean up,down,left,right, space,enter;
	
	private static String text = "";
	private static Normalizer normalize;
	
	public KeyEventListener() {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			up = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			down = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			right = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			space = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			enter = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			up = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			right = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			space = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			enter = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
			if(text.length()!=0)
				text = text.substring(0, text.length() - 1);
		} else if(e.getKeyCode() == KeyEvent.VK_EQUALS || e.getKeyCode() == KeyEvent.VK_ADD || e.getKeyCode() == KeyEvent.VK_BRACELEFT ||
				e.getKeyCode() == KeyEvent.VK_ALT || e.getKeyCode() == KeyEvent.VK_SHIFT || e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_CAPS_LOCK){
			
		} else {
			text += e.getKeyChar();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public static boolean isUp() {
		return KeyEventListener.up;
	}
	public static boolean isDown() {
		return KeyEventListener.down;
	}
	public static boolean isLeft() {
		return KeyEventListener.left;
	}
	public static boolean isRight() {
		return KeyEventListener.right;
	}
	public static boolean isEnter() {
		return KeyEventListener.enter;
	}
	public static boolean isSpace() {
		return KeyEventListener.space;
	}
	public static void resetText(){
		text = "";
	}
	public static String getText() {
		return text;
	}
	public static void setText(String newText){
		text = newText;
	}

}
