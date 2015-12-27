package game.client.managers;

import game.client.gameloop.GameLoop;
import game.client.players.Player;

import java.awt.Graphics2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayersManager {
	
	public void init(){
		
	}
	
	public void tick(double deltaTime){
		for (Player player : GameLoop.playersData.players) {
			player.tick(deltaTime);
		}
	}
	
	public void render(Graphics2D g){
		for (Player player : GameLoop.playersData.players) {
			player.render(g);
		}
	}
}
