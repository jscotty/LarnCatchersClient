package game.client.listeners;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import operator.Vector2D;

public class MouseEventListener implements MouseListener,MouseMotionListener {

	private static Vector2D mousePos = new Vector2D(0,0);
	private static Point mouseP;
	private static int mouseX,mouseY;
	private static boolean pressed;
	
	public MouseEventListener() {
		
	}
	
	public void tick(){
		mouseP = new Point(mouseX, mouseY);
		mousePos.xPos = mouseX; mousePos.yPos = mouseY;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			pressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			pressed = false;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY()-30;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY()-30;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	public static boolean isPressed(){
		return pressed;
	}
	
	public static Vector2D mouseLocation(){
		return mousePos;
	}
	
	public static Point mousePoint(){
		return mouseP;
	}
	
	public static void resetPressed(){
		pressed = false;
	}

}
