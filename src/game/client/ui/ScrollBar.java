package game.client.ui;

import game.client.listeners.KeyEventListener;
import game.client.listeners.MouseEventListener;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import operator.Vector2D;

@SuppressWarnings("serial")
public class ScrollBar extends Rectangle implements MouseWheelListener{
	
	private Vector2D pos = new Vector2D(0,0);
	private static Vector2D barPos = new Vector2D(0,0);
	private int width, height;
	private int barHeight, contentSize;
	private boolean hoover = false, pressed = false;

	private int count = 0;

	public ScrollBar(){}
	
	public ScrollBar(float xPos, float yPos, int width, int height, int contentSize){
		pos.xPos = xPos;
		pos.yPos = yPos;
		barPos.xPos = xPos;
		this.width = width;
		this.height = height;
		barHeight = height- 5;
		
		if(contentSize > height){
			barHeight = height - (contentSize - height)/2;
		}
		barPos.yPos = yPos + height - barHeight;

		setBounds((int)barPos.xPos, (int)barPos.yPos, width, barHeight);
	}
	
	public void tick(){
		setBounds((int)barPos.xPos-5, (int)barPos.yPos, width+10, barHeight);
		if(contentSize > height){
			barHeight = height - (contentSize - height)/2;
		}
		
		if(KeyEventListener.isEnter()){
			barPos.yPos = pos.yPos + height - barHeight;
		}
		
		
		if(this.contains(MouseEventListener.mousePoint())){
			hoover = true;
		} else {
			hoover = false;
		}
		
		if(hoover){ 
			if (MouseEventListener.isPressed()){
				pressed = true;
			} else {
				pressed = false;
			}
		} else {
			pressed = false;
		}
		
		if(pressed){
			barPos.yPos = MouseEventListener.mouseLocation().yPos - (barHeight/2);
		}
		
		
		
		if((barPos.yPos + barHeight) > 523){
			barPos.yPos = pos.yPos + height - barHeight;
		}
		if(barPos.yPos < 393){
			barPos.yPos = 393;
		}
	}
	
	public void render(Graphics2D g){
		g.setColor(Color.white);
		g.fillRect((int)pos.xPos, (int)pos.yPos, width, height);
		
		if(hoover)
			g.setColor(Color.gray);
		else
			g.setColor(Color.black);
		
		g.fillRect((int)barPos.xPos, (int)barPos.yPos, width, barHeight);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		barPos.yPos += e.getWheelRotation();
	}
	
	public void contentSize(int size){
		contentSize = size;
	}
	
	public int getYPos(){

		int posI = (int)((pos.yPos + height - barHeight) - pos.yPos);
		return (int)((barPos.yPos-pos.yPos)/7) - posI/7;
	}
	
	public void resetPos(){
		barPos.yPos = pos.yPos + height - barHeight;
	}

}
