package game.client.monsters;

import java.awt.Graphics2D;
import java.util.concurrent.CopyOnWriteArrayList;

import operator.Vector2D;

public class MonstersList {
	
	private Vector2D pos = new Vector2D(0,0);
	private CopyOnWriteArrayList<MonsterSlot> slots = new CopyOnWriteArrayList<MonsterSlot>();

	private int dimentionX,dimentionY;
	
	private boolean toggle = true;
	
	public MonstersList(float xPos, float yPos,int rowsX,int rowsY){
		pos.xPos = xPos;
		pos.yPos = yPos;
		dimentionX = rowsX;
		dimentionY = rowsY;
	}
	
	public void init(){
		for (int x = 0; x < dimentionX; x++) {
			for (int y = 0; y < dimentionY; y++) {
				slots.add(new MonsterSlot(new Vector2D(pos.xPos+(y*70),pos.yPos+(x*70)), 0, 64, 64));
			}
		}
	}
	
	public void tick(double deltaTime){
		if(toggle) {
			for (MonsterSlot slot : slots) {
				slot.tick(deltaTime);
			}
		}
	}
	
	public void render(Graphics2D g){
		if(toggle) {
			for (MonsterSlot slot : slots) {
				slot.render(g);
			}
		}
	}
	
	public void toggle(){
		toggle = !toggle;
	}
	
	public void removeMonster(MonsterType type){
		for (MonsterSlot slot : slots) {
			if(slot.getType().equals(type)){
				slot.emptySlot();
			}
		}
	}
}
