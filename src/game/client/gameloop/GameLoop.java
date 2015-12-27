package game.client.gameloop;

import game.client.data.PlayersData;
import game.client.listeners.KeyEventListener;
import game.client.listeners.MouseEventListener;
import game.client.main.Animations;
import game.client.main.Assets;
import game.client.main.Connect;
import game.client.managers.GameStateManager;
import game.client.players.Player;
import game.client.ui.CustomFont;
import operator.IDGameLoop;

public class GameLoop extends IDGameLoop{

	GameStateManager gsm;
	MouseEventListener ml = new MouseEventListener();
	KeyEventListener kl = new KeyEventListener();
	CustomFont customFonts;
	public static PlayersData playersData = new PlayersData();
	public static Connect connect;
	public static Assets assets = new Assets();
	public static Animations animations = new Animations();
	
	public GameLoop(int width, int height) {
		super(width, height);
	}
	
	@Override
	public void init() {
		gsm = new GameStateManager();
		assets.init();
		animations.init();
		gsm.init();
		connect = new Connect(gsm);
		customFonts = new CustomFont();
		super.init();
	}
	
	@Override
	public void tick(double deltaTime) {
		ml.tick();
		gsm.tick(deltaTime);
	}
	
	@Override
	public void render() {
		gsm.render(graphics2D);
		clear();
		super.render();
	}
	
	@Override
	public void clear() {
		super.clear();
	}

}
