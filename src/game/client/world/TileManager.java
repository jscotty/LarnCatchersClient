package game.client.world;

import java.awt.Graphics2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class TileManager {

	public CopyOnWriteArrayList<Tile> tiles = new CopyOnWriteArrayList<Tile>();
	
	public TileManager(){
		
	}
	
	public void tick(double deltaTime){
		for (Tile tile : tiles) {
			tile.tick(deltaTime);
		}
	}
	
	public void render(Graphics2D g){
		for (Tile tile : tiles) {
			tile.render(g);
		}
	}
}
