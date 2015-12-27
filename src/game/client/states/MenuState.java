package game.client.states;

import java.awt.Graphics2D;

import game.client.handlers.MenuHandler;
import game.client.managers.GameStateManager;

public class MenuState extends GameState {

	private MenuHandler mh;
	private boolean load = false;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		load = false;
	}

	@Override
	public void init() {
		mh = new MenuHandler(gsm);
		mh.init();
		load = true;
	}

	@Override
	public void tick(double deltaTime) {
		if(load)
			mh.tick(deltaTime);
	}

	@Override
	public void render(Graphics2D g) {
		if(load)
			mh.render(g);
	}

}
