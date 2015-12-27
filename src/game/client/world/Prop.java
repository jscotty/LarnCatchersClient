package game.client.world;

import game.client.main.Camera;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.concurrent.CopyOnWriteArrayList;

import operator.Vector2D;

@SuppressWarnings("serial")
public class Prop extends Rectangle{
	
	private Vector2D pos = new Vector2D(0,0);
	private PropType type;
	private int width = 32;
	private int height = 32;
	private boolean solid = false;

	public Prop(Vector2D pos, PropType type){
		this.pos = pos;
		this.type = type;
		setBounds((int)pos.xPos, (int)pos.yPos, width, height);
	}
	
	public Prop(float xPos, float yPos, PropType type){
		pos.xPos = xPos;
		pos.yPos = yPos;
		this.type = type;
		setBounds((int)pos.xPos, (int)pos.yPos, width, height);
	}
	
	public Prop(PropType type){
		this.type = type;
		setBounds((int)pos.xPos, (int)pos.yPos, width, height);
	}
	
	public Prop isSolid(boolean solid){
		this.solid = solid;
		return this;
	}
	
	public Prop setPos(Vector2D pos){
		this.pos = pos;
		return this;
	}
	public Prop setPos(float xPos, float yPos){
		pos.xPos = xPos;
		pos.yPos = yPos;
		return this;
	}
	
	public void tick(double deltaTime){
		
	}
	
	public void render(Graphics2D g){
		g.drawImage(type.getImage(), (int)pos.xPos - Camera.getxPos(), (int)pos.yPos- Camera.getyPos(), width, height, null);
	}
	
	public boolean isSolid(){
		return solid;
	}
	
	public Vector2D getTileLocation() {
		return pos;
	}
	
	public PropType getType() {
		return type;
	}
}
