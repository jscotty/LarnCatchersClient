package game.client.world;

import java.awt.Graphics2D;
import java.util.Dictionary;
import java.util.Hashtable;

import game.client.managers.GameStateManager;
import game.client.managers.HudManager;
import game.client.managers.PlayersManager;
import game.client.states.GameState;
import game.client.states.PlayState;

public class World {
	
	private static HudManager hud = new HudManager();
	
	private int worldHeight;
	private int worldWidth;
	
	private String worldName;
	private PlayState playState;
	private GameStateManager gsm;
	
	private int tileSize = WorldData.tileSize;
	
	
	private TileManager tm = new TileManager();
	private PropsManager pm = new PropsManager();
	private PlayersManager playersManager = new PlayersManager();
	
	public static Dictionary<Integer, Tile> tileTypes = new Hashtable<Integer, Tile>();
	public static Dictionary<Integer, Prop> propTypes = new Hashtable<Integer, Prop>();
	
	
	public World(String name, PlayState playState, GameStateManager gsm){
		worldName = name;
		this.playState = playState;
		this.gsm = gsm;
		
	}
	
	public void init(){
		hud.init();
		playersManager.init();
		for (int y = 0; y < worldHeight; y++) {
			for (int x = 0; x < worldWidth; x++) {
				tileTypes.put(0, new Tile(x*tileSize, y*tileSize, TileType.GRASS));
				tileTypes.put(1, new Tile(x*tileSize, y*tileSize, TileType.WALL).isSolid(true));

				propTypes.put(0, new Prop(x*tileSize, y*tileSize, PropType.GRASS_01));
				propTypes.put(1, new Prop(x*tileSize, y*tileSize, PropType.GRASS_02));
				propTypes.put(2, new Prop(x*tileSize, y*tileSize, PropType.WATER));
				
				Tile tile;
				tile = tileTypes.get(WorldData.tiles[x + y * worldWidth]);
				
				tm.tiles.add(tile);
				
				Prop prop;
				prop = propTypes.get(WorldData.props[x + y * worldWidth]);
				pm.props.add(prop);
			}
		}
		
	}
	
	public void tick(double deltaTime){
		tm.tick(deltaTime);
		pm.tick(deltaTime);
		playersManager.tick(deltaTime);
		hud.tick(deltaTime);
	}
	
	public void render(Graphics2D g){
		tm.render(g);
		pm.render(g);
		playersManager.render(g);
		hud.render(g);
	}
	
	public Tile getTile(int x, int y){
		Tile tile = null;
		for(Tile tiles : tm.tiles){
			if(tiles.getTileLocation().xPos/tileSize  == x && tiles.getTileLocation().yPos/tileSize  == y){
				tile = tiles;
			} else {
			}
		}
		return tile;
	}
	public void resetTiles(){
		for (Tile tile : tm.tiles) {
			tile.f = 0;
			tile.g = 0;
			tile.h = 0;
			tile.isClosed =	false;
			tile.isOpen	= false;
			tile.parent = null;
		}
	}
	
	public void setSize(int width, int height){
		worldWidth = width;
		worldHeight = height;
	}
	
	public static HudManager getHud() {
		return hud;
	}
}
