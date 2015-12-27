package game.client.main;

import game.client.data.PlayerData;
import game.client.gameloop.GameLoop;
import game.client.listeners.KeyEventListener;
import game.client.listeners.MouseEventListener;
import game.client.listeners.NetworkListenerClient;
import game.client.main.Packet.LogOffPacket;
import game.client.sprites.Sprites;
import game.client.ui.ScrollBar;

import java.util.Scanner;










import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import operator.GameWindow;
import operator.LoadImageFrom;

public class MainClient {

	public static Scanner scanner;
	
	public static int width = 800;
	public static int height = 600;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		GameWindow gameWindow = new GameWindow("Larn catchers", width, height);
		
		gameWindow.addKeyListener(new KeyEventListener());
		gameWindow.addMouseListener(new MouseEventListener());
		gameWindow.addMouseMotionListener(new MouseEventListener());
		gameWindow.addMouseWheelListener(new ScrollBar());
		gameWindow.add(new GameLoop(width,height));
		gameWindow.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	for (String string : NetworkListenerClient.players) {
					if(string.equals(PlayerData.playerName)){
						if(GameLoop.connect.isConnected()){
							LogOffPacket lp = new LogOffPacket();
							lp.username = PlayerData.playerName;
							GameLoop.connect.getClient().sendTCP(lp);
							GameLoop.connect.getClient().close();
						}
						
					}
				}
		    }
		});
		gameWindow.setIconImage(LoadImageFrom.loadImageFrom(Sprites.class, "icon"));
		gameWindow.setVisible(true);
	}
	
	

}
