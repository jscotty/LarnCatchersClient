package game.client.handlers;

import game.client.gameloop.GameLoop;
import game.client.listeners.MouseEventListener;
import game.client.main.Assets;
import game.client.main.Connect;
import game.client.main.MainClient;
import game.client.managers.GameStateManager;
import game.client.states.GameState;
import game.client.states.PlayState;
import game.client.ui.CustomFont;
import game.client.ui.InputField;
import game.client.ui.UIButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MenuHandler extends JFrame {
	
	private UIButton infoButton;
	private UIButton loginButton;
	private UIButton backButton;
	private UIButton loginActionButton;
	
	private InputField loginNameField;
	private InputField loginPassField;
	
	private GameStateManager gsm;
	
	private enum MenuState{HOME,LOGIN,INFO}
	private MenuState state = MenuState.HOME;
	
	private String message;
	private String userMessage = "";
	private Font font, font2;
	
	private Connect connect;
	
	private boolean load = false;
	
	public static boolean accepted = false;
	
	public MenuHandler(GameStateManager gsm){
		this.gsm = gsm;
		load = false;
	}
	
	public void init(){
		infoButton = new UIButton(250, 460, "Info").setFontColor(new Color(204,10,0));
		loginButton = new UIButton(425, 460, "Login").setFontColor(new Color(23,204,0));

		backButton = new UIButton(250, 460, "Back");
		loginActionButton = new UIButton(425, 460, "Login").setFontColor(new Color(23,204,0));
		
		loginNameField = new InputField(250, 320).setMessage("Enter your name");
		loginPassField = new InputField(250, 380).setMessage("Enter your password").setPassword();
		
		font = CustomFont.loadFont(CustomFont.class, "small_pixel.ttf", 22);
		font2 = CustomFont.loadFont(CustomFont.class, "small_pixel.ttf", 14);
		
		load = true;
	}
	
	public void tick(double deltaTime){
		if(load){
			if(infoButton.isPressed()){
				state = MenuState.INFO;
				infoButton.resetButton();
				MouseEventListener.resetPressed();
			} else if(loginButton.isPressed()){
				state = MenuState.LOGIN;
				loginButton.resetButton();
				MouseEventListener.resetPressed();
			} else if(backButton.isPressed()){
				state = MenuState.HOME;
				backButton.resetButton();
				MouseEventListener.resetPressed();
			} else if(loginActionButton.isPressed()){
				
				if(loginNameField.getText().equals("")){
					userMessage = "Please enter your name!";
					loginActionButton.resetButton();
					return;
				} else if(loginNameField.getText().length() > 10){
					userMessage = "Sorry, your name is to long";
					loginActionButton.resetButton();
					return;
				}
				
				connect = GameLoop.connect.setUserData(loginNameField.getText(), loginPassField.getText()).isRegistering();
				connect.connect();
				
				if(!accepted){
					userMessage = "Your password is incorrect!";
				}
				if(!connect.isConnected()){
					userMessage = "could not connect";
				} else {
					userMessage = "Connecting with the server";
				}
				
				MouseEventListener.resetPressed();
			}
			
			switch (state) {
			case HOME:
				infoButton.tick();
				loginButton.tick();
				message = "Welcome to larn catchers!";
				break;
			case LOGIN:
				backButton.tick();
				loginActionButton.tick();
				
				loginNameField.tick();
				loginPassField.tick();
				message = "Please enter your username and password!";
				break;
			case INFO:
				backButton.tick();
				loginButton.tick();
				
				break;
			}
			
			if(accepted){
				GameStateManager.states.push(new PlayState(gsm));
				GameStateManager.states.peek().init();
			}
		}
	}
	
	public void render(Graphics2D g){
		g.drawImage(Assets.getMenuBG(),0,0,800,600,null);
		switch (state) {
		case HOME:
			infoButton.render(g);
			loginButton.render(g);
			g.drawImage(Assets.getWelcome(), 105,268,null);
			break;
		case LOGIN:
			backButton.render(g);
			loginActionButton.render(g);

			loginNameField.render(g);
			loginPassField.render(g);
			break;
		case INFO:
			backButton.render(g);
			loginButton.render(g);
			
			g.setColor(Color.WHITE);
			g.drawString("To create a account", 270, 320);
			g.drawString("you simpily fill in an username and password ", 130, 380);
			
			break;
		}
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(message, (MainClient.width/2)-(((message.length()*font.getSize()/3))), 250);

		g.setFont(font2);
		g.drawString(userMessage, (MainClient.width/2)-(((userMessage.length()*font2.getSize()/3))), 280);
	}

}
