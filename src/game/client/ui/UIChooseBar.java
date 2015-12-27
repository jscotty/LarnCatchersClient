package game.client.ui;

import game.client.listeners.MouseEventListener;
import game.client.players.CharacterPart;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import operator.Vector2D;

@SuppressWarnings("serial")
public class UIChooseBar extends Rectangle {

	private Vector2D pos = new Vector2D(0,0);
	private Vector2D choosePos = new Vector2D(0,0);
	
	private BufferedImage btnBG, chooseBG;
	private int width, height, chooseHeight;
	
	private Color btnBGColor, chooseBGColor, txtColor;
	
	private ArrayList<String> options = new ArrayList<String>();
	
	private boolean hoover, active;
	private String option;
	
	private int fontSize = 15;
	private Font font;
	
	private CharacterPart part;
	private String message = "";
	
	public UIChooseBar(Vector2D pos, int width, int height, BufferedImage btnBG, BufferedImage chooseBG){
		this.pos = pos;
		this.width = width; this.height = height;
		this.btnBG = btnBG; this.chooseBG = chooseBG;
	}
	
	public UIChooseBar(float xPos, float yPos, int width, int height, BufferedImage btnBG, BufferedImage chooseBG){
		pos.xPos = xPos; pos.yPos = yPos;
		this.width = width; this.height = height;
		this.btnBG = btnBG; this.chooseBG = chooseBG;
		
		if(width == 0){
			this.width = btnBG.getWidth();
		}
		if(height == 0){
			this.height = btnBG.getHeight();
		}
	}
	
	public UIChooseBar setColor(Color btnBGColor, Color chooseBGColor){
		this.btnBGColor = btnBGColor; this.chooseBGColor = chooseBGColor;
		return this;
	}
	
	public UIChooseBar setTextColor(Color txtColor){
		this.txtColor = txtColor;
		return this;
	}
	
	public UIChooseBar setPart(CharacterPart part){
		this.part = part;
		return this;
	}
	
	public UIChooseBar setMessage(String msg){
		message = msg;
		return this;
	}
	
	public void addOption(String option){
		options.add(option);
	}
	
	public void addOptionList(ArrayList<String> options){
		this.options = options;
	}
	
	public void init(){
		setBounds((int)pos.xPos, (int)pos.yPos, width, height);
		
		font = CustomFont.loadFont(CustomFont.class, "small_pixel.ttf", fontSize);
		
		chooseHeight = (options.size()*fontSize)+fontSize/2;
		
		option = options.get(0);
	}
	
	public void tick(){
		if(this.contains(MouseEventListener.mousePoint())){
			hoover = true;
		} else {
			hoover = false;
		}

		if(hoover){
			if(MouseEventListener.isPressed()){
				active = true;
			}
		}
		
		for (int i = 0; i < options.size(); i++) {
			if(active){
				
				Rectangle rect = new Rectangle((int)pos.xPos,(int)pos.yPos+(i*fontSize),width,height);
				
				if(rect.contains(MouseEventListener.mousePoint())){
					choosePos.xPos = pos.xPos;
					choosePos.yPos = pos.yPos+(i*fontSize);
					
					if(MouseEventListener.isPressed()){
						option = options.get(i);
					}
				}
			}
		}
		 if(!hoover) {
			if(MouseEventListener.isPressed()){
				active = false;
			}
		}

	}
	
	public void render(Graphics2D g){
		g.setFont(font);
		if(btnBG!=null){
			g.drawImage(btnBG, (int)pos.xPos,(int)pos.yPos,width,height,null);
			g.setColor(txtColor);
			g.drawString(option, (int)pos.xPos+5,(int)pos.yPos+fontSize);
		} else {
			g.setColor(btnBGColor);
			g.fillRect((int)pos.xPos,(int)pos.yPos,width,height);
			g.setColor(txtColor);
			g.drawString(option, (int)pos.xPos+5,(int)pos.yPos+fontSize);
		}
		
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		if(hoover){
			g.setColor(new Color(150,150,150));//grey
			g.fillRect((int)pos.xPos,(int)pos.yPos,width,height);
		}
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		if(chooseBG!=null){
			if(active){
				g.drawImage(chooseBG, (int)pos.xPos,(int)pos.yPos,width,chooseHeight,null);
				
				g.setColor(txtColor);
				int i = 0;
				for (String o : options) {
					g.drawString(o, (int)pos.xPos,(int)(pos.yPos+(i*fontSize)));
					i++;
				}
			}
		} else {
			if(active){
				g.setColor(chooseBGColor);
				g.fillRect((int)pos.xPos,(int)pos.yPos,width,chooseHeight);
				
				g.setColor(txtColor);
				int i = 0;
				for (String o : options) {
					g.drawString(o, (int)pos.xPos,(int)(pos.yPos+fontSize+(i*fontSize)));
					i++;
				}
			}
		}
		
		if(active){
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
				g.setColor(new Color(150,150,150));//grey
				g.fillRect((int)choosePos.xPos,(int)choosePos.yPos,width,height);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}

		g.setColor(Color.white);
		g.drawString(message, (int)pos.xPos, (int)pos.yPos-10);
	}
	
	public String getOption() {
		return option;
	}
	
	public CharacterPart getPart() {
		return part;
	}
}
