package game.client.states;

import java.awt.Graphics2D;

import game.client.managers.GameStateManager;
import game.client.managers.PlayersManager;
import game.client.world.World;

public class PlayState extends GameState {
	
	public static World world;
	
	private String worldName = "pokebase";
	private int[][] tileMap;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	public PlayState(String name ,int[][] tileMap, GameStateManager gsm) {
		super(gsm);
		worldName = name;
		this.tileMap = tileMap;
	}

	@Override
	public void init() {
		world = new World(worldName, this, gsm);
		world.setSize(30, 30);
		world.init();
	}

	@Override
	public void tick(double deltaTime) {
		world.tick(deltaTime);
	}

	@Override
	public void render(Graphics2D g) {
		world.render(g);
		
	}

}
