package game.client.ui;

import game.client.listeners.NetworkListenerClient;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Chat extends Rectangle{

	private Font font;
	private int y = 530;
	private int yPos;
	private int height;
	private static ScrollBar scrollBar;
	private Color color = new Color(0,0,0);
	
	public Chat(Font font){
		this.font = font;
		
		scrollBar = new ScrollBar(532,393,16,130, height);
		
		setBounds(50, 390, 503, 140);
	}
	
	public void tick(){
		scrollBar.tick();
		int size = NetworkListenerClient.chat.size();
		height = (size * font.getSize()) + 20;
		scrollBar.contentSize(height);
		
		yPos = y - ((font.getSize() * (size+scrollBar.getYPos())));
	}
	
	public void render(Graphics2D g){
		g.setFont(font);
		
		g.setClip(51, 390, 503, 135);
		
		int i = 0;
		for (String chat : NetworkListenerClient.chat) {

			String[] c = chat.split("@");
			color = Color.black;
			
			String[] username = chat.split(":");
			
			if(username[0].equals("justin")){
				color =  new Color(85,204,71);
			}
			for (int j = 0; j < c.length; j++) {

				
				if(c[j].equals("red")){
					color = Color.red;
				} else if(c[j].equals("blue")){
					color = Color.blue;
				} else if(c[j].equals("green")){
					color = Color.green;
				} else if(c[j].equals("cyan")){
					color = Color.cyan;
				}
				
				if(c.length > 2){
					chat = "";
					if(username[0].length() <= 10) chat = username[0].toString() + ": ";
					chat += c[2];
				}
			}
			
			chat.replace("@", "");
			g.setColor(Color.BLACK);
			if(color != Color.BLACK)
				g.drawString(chat, 60+1, yPos+1 + (i*font.getSize()));
			g.setColor(color);
			g.drawString(chat, 60, yPos + (i*font.getSize()));
			i++;
		}

		g.setClip(0, 0, 800, 600);
		
		scrollBar.render(g);
	}
	
	public static void resetPos(){
		if(scrollBar!= null)
			scrollBar.resetPos();
	}
}
