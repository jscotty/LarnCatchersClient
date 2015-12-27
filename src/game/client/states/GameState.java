package game.client.states;

import java.awt.Graphics2D;

import game.client.managers.GameStateManager;

public abstract class GameState {

	GameStateManager gsm;
	
	public GameState(GameStateManager gsm){
		this.gsm = gsm;
	}

	public abstract void init();
	public abstract void tick(double deltaTime);
	public abstract void render(Graphics2D g);

}
