package game.client.monsters;

import game.client.main.Assets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import operator.Animator;
import operator.Vector2D;

public class MonsterSlot {
	
	private Vector2D pos = new Vector2D(0,0);
	private MonsterType type;
	private int level = 0;
	private int width, height;
	private Animator anim;

	public MonsterSlot(float xPos, float yPos, int width, int height, MonsterType type, int level) {
		pos.xPos = xPos;
		pos.yPos = yPos;
		this.width = width;
		this.height = height;
		this.type = type;
		this.level = level;
	}

	public MonsterSlot(Vector2D pos, int level, int width, int height) {
		this.pos = pos;
		type = null;
		this.width = width;
		this.height = height;
		this.level = level;
	}
	
	public void init(){
		if(type != null){
			anim = type.getAnimFront();
			anim.play();
		}
	}
	
	public void tick(double deltaTime){
		
	}
	
	public void render(Graphics2D g){
		g.drawImage(Assets.getBorder(), (int)pos.xPos,(int)pos.yPos, width, height, null);
		
		if(anim != null){
			g.drawImage(anim.sprite,(int)pos.xPos,(int)pos.yPos, width, height, null);
			anim.update(System.currentTimeMillis());
		}
		
		if(level > 0){
			g.setColor(Color.yellow);
			g.drawString(""+level, (int)pos.xPos, (int)pos.yPos+height -5);
		}
	}
	
	public boolean hasMonster(){
		if(type != null) return true;
		else return false;
	}
	
	public void addMonster(MonsterType type){
		if(type != null){
			this.type = type;
		}
	}
	public void emptySlot(){
		type = null; 
		level = 0;
	}
	
	public MonsterType getType() {
		return type;
	}
	
	public Vector2D getPos() {
		return pos;
	}

}
