package game.client.ui;

import game.client.listeners.MouseEventListener;
import game.client.main.Assets;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import operator.Animator;
import operator.Vector2D;

@SuppressWarnings("serial")
public class UIButton extends Rectangle{

	private Vector2D pos = new Vector2D(0,0);
	private boolean pressed = false, hoover = false;
	private int width,height;
	private BufferedImage image = Assets.getBtnWhite();
	private Font font;
	private String message;
	private Color fontColor = Color.black;
	private Animator anim;
	private String tag = "button";
	
	public UIButton(float xPos, float yPos, String message) {
		pos.xPos = xPos;
		pos.yPos = yPos;
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.message = message;
		
		init();
	}
	
	public UIButton(float xPos, float yPos, int width, int height, String message) {
		pos.xPos = xPos;
		pos.yPos = yPos;
		this.width = width;
		this.height = height;
		this.message = message;
		
		init();
	}

	public UIButton(BufferedImage image,float xPos, float yPos, String message) {
		this.image = image;
		pos.xPos = xPos;
		pos.yPos = yPos;
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.message = message;
		
		init();
	}
	
	public UIButton(BufferedImage image,float xPos, float yPos, int width, int height, String message) {
		this.image = image;
		pos.xPos = xPos;
		pos.yPos = yPos;
		this.width = width;
		this.height = height;
		this.message = message;
		
		init();
	}

	public UIButton(Animator anim,float xPos, float yPos) {
		this.anim = anim;
		this.image = anim.sprite;
		pos.xPos = xPos;
		pos.yPos = yPos;
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.message = "";
		
		init();
	}
	
	public UIButton(Animator anim,float xPos, float yPos, int width, int height) {
		this.anim = anim;
		this.image = anim.sprite;
		pos.xPos = xPos;
		pos.yPos = yPos;
		this.width = width;
		this.height = height;
		this.message = "";
		
		init();
	}
	
	public UIButton setTag(String tag){
		this.tag = tag;
		return this;
	}
	
	public UIButton setMessage(String message){
		this.message = message;
		return this;
	}
	
	public void init(){
		setBounds((int)pos.xPos, (int)pos.yPos, width, height);
		
		font = CustomFont.loadFont(CustomFont.class, "small_pixel.ttf", 20);
	}
	
	public void tick(){
		if(getBounds().contains(MouseEventListener.mousePoint())){
			hoover = true;
		} else {
			hoover = false;
		}
		
		if(hoover){
			if(MouseEventListener.isPressed()){
				pressed = true;
			} else {
				pressed = false;
			}
		} else {
			pressed = false;
		}
	}
	
	public void render(Graphics2D g){
		g.setFont(font);
		g.drawImage(image, (int)pos.xPos, (int)pos.yPos, width, height,null);
		

		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		if(hoover){
			g.setColor(new Color(150,150,150));//grey
			g.fillRect((int)pos.xPos,(int)pos.yPos,width,height);
		}
		if(pressed){
			g.setColor(Color.BLACK);
			g.fillRect((int)pos.xPos,(int)pos.yPos,width,height);
		}
		
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		g.setColor(fontColor);
		g.drawString(message, (int)(pos.xPos + (width/2) - ((message.length()*font.getSize()/3))), (int)pos.yPos + (height / 2) + font.getSize()/2);
	}
	
	public boolean isPressed(){
		return pressed;
	}
	
	public void nextBtnSprite(){
		anim.nextFrame();
		image = anim.sprite;
	}
	
	public void resetBtnSprite(){
		anim.setCurrentFrame(0);
		image = anim.sprite;
	}
	
	public void resetButton(){
		hoover = false;
		pressed = false;
	}
	
	public boolean isActive(){
		if(anim!=null){
			if(anim.getCurrentFrame() == 0) return false;
			else return true;
		} else {
			return false;
		}
	}
	
	public UIButton setFontColor(Color color){
		this.fontColor = color;
		return this;
	}
	
	public Vector2D getButtonLocation() {
		return pos;
	}
	public String getTag() {
		return tag;
	}

}
