package game.client.world;

import game.client.main.Camera;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.concurrent.CopyOnWriteArrayList;

import operator.Vector2D;

public class Tile extends Rectangle{
	
	private Vector2D pos = new Vector2D(0,0);
	private TileType type;
	private int width = 64;
	private int height = 64;
	private boolean solid = false;

	// AStar variables
	public double g = 0;
	public double h = 0;
	public double f = 0;
	
	public Tile parent;
	
	public boolean isOpen = false;
	public boolean isClosed = false;

	public CopyOnWriteArrayList<Tile> neighbors = new CopyOnWriteArrayList<Tile>();
	// end AStar

	public Tile(Vector2D pos, TileType type){
		this.pos = pos;
		this.type = type;
		setBounds((int)pos.xPos, (int)pos.yPos, width, height);
	}
	
	public Tile(float xPos, float yPos, TileType type){
		pos.xPos = xPos;
		pos.yPos = yPos;
		this.type = type;
		setBounds((int)pos.xPos, (int)pos.yPos, width, height);
	}
	
	public Tile(TileType type){
		this.type = type;
		setBounds((int)pos.xPos, (int)pos.yPos, width, height);
	}
	
	public Tile isSolid(boolean solid){
		this.solid = solid;
		return this;
	}
	
	public Tile setPos(Vector2D pos){
		this.pos = pos;
		return this;
	}
	public Tile setPos(float xPos, float yPos){
		pos.xPos = xPos;
		pos.yPos = yPos;
		return this;
	}
	
	public void tick(double deltaTime){
	}
	
	public void render(Graphics2D g){
		//System.out.println("(" + pos.xPos + "," + pos.yPos + ")");
		g.drawImage(type.getImage(), (int)pos.xPos - Camera.getxPos(), (int)pos.yPos- Camera.getyPos(), width, height, null);
	}
	
	public boolean isSolid(){
		return solid;
	}
	
	public Vector2D getTileLocation() {
		return pos;
	}
	
	public TileType getType() {
		return type;
	}
}
