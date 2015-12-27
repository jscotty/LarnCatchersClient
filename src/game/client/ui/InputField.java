package game.client.ui;

import game.client.data.PlayerData;
import game.client.gameloop.GameLoop;
import game.client.listeners.KeyEventListener;
import game.client.listeners.MouseEventListener;
import game.client.listeners.NetworkListenerClient;
import game.client.main.Assets;
import game.client.main.Connect;
import game.client.main.Packet.ChatPacket;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import operator.Vector2D;

@SuppressWarnings("serial")
public class InputField extends Rectangle {

	private Vector2D pos = new Vector2D(0,0);
	private int width,height;
	private BufferedImage image = Assets.getTextFieldBG_menu();
	
	private String text = "", msg = "";
	private boolean password = false;
	private boolean gameMsg = false;
	
	private int count = 0;
	
	private boolean hoover,pressed;
	private Color textColor = Color.WHITE;
	
	private Font font = CustomFont.loadFont(CustomFont.class, "small_pixel.ttf", 15);
	
	public InputField(float xPos, float yPos, int width, int height) {
		pos.xPos = xPos;
		pos.yPos = yPos;
		this.width = width;
		this.height = height;
		init();
	}
	public InputField(float xPos, float yPos) {
		pos.xPos = xPos;
		pos.yPos = yPos;
		this.width = image.getWidth();
		this.height = image.getHeight();
		init();
	}
	public InputField(BufferedImage image,float xPos, float yPos) {
		this.image = image;
		pos.xPos = xPos;
		pos.yPos = yPos;
		this.width = image.getWidth();
		this.height = image.getHeight();
		init();
	}
	public InputField(BufferedImage image,float xPos, float yPos, int width, int height) {
		this.image = image;
		pos.xPos = xPos;
		pos.yPos = yPos;
		this.width = width;
		this.height = height;
		init();
	}
	
	public InputField isMessageBox(){
		gameMsg = true;
		return this;
	}
	
	private void init(){
		setBounds((int)pos.xPos, (int)pos.yPos, width, height);
		KeyEventListener.setText(text);
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
				KeyEventListener.setText(text);
			}
		} else {
			if(MouseEventListener.isPressed()){
				pressed = false;
			}
		}
		
		if(pressed){
			text = KeyEventListener.getText();
		}
		
		if(gameMsg){
			if(KeyEventListener.isSpace()){
				pressed = true;
			}
			if(KeyEventListener.isEnter()){
				sendChatMessage();
				if(pressed){
					text = "";
					KeyEventListener.setText(text);
				}
				pressed = false;
			} else {
				count = 0;
			}
		}
	}
	
	public void render(Graphics2D g){
		g.drawImage(image, (int)pos.xPos, (int)pos.yPos, width, height, null);
		

		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
		if(hoover){
			g.setColor(new Color(145,145,145));//soft gray
			g.fillRect((int)pos.xPos, (int)pos.yPos, width, height);
		}
		if(pressed){
			g.setColor(Color.WHITE);
			g.fillRect((int)pos.xPos, (int)pos.yPos, width, height);
		}

		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		g.setFont(font);
		g.setColor(textColor);
		if(!password)
			g.drawString(text, (int)pos.xPos+ 5, (int)pos.yPos + font.getSize());
		else {
			String passwordMsg = "";
			for (int i = 0; i < text.length(); i++) {
				passwordMsg = passwordMsg + "*";
			}
			g.drawString(passwordMsg, (int)pos.xPos+ 5, (int)pos.yPos + font.getSize());
		}
		g.drawString(msg, (int)pos.xPos+ 5, (int)pos.yPos -5);
	}
	
	public InputField setMessage(String message){
		msg = message;
		return this;
	}
	public InputField setTextColor(Color color){
		textColor = color;
		return this;
	}
	
	public InputField setFont(Font font){
		this.font = font;
		return this;
	}
	
	public InputField setPassword(){
		password = true;
		return this;
	}
	
	public String getText(){
		return text;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public void sendChatMessage(){
		count ++;
		
		if(count == 1){
			ChatPacket cp = new ChatPacket();
			//NetworkListenerClient.chat.add(PlayerData.playerName+ ": " + text);
			cp.chat = PlayerData.playerName+ ": " + text;
			
			GameLoop.connect.getClient().sendTCP(cp);
		}
	}

}
