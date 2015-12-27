package game.client.main;

import java.awt.Color;
import java.util.Dictionary;

import operator.Vector2D;

public interface NetworkInterface {
	
	void addPlayer(String username, Vector2D pos);
	void setPlayerPath(String username, Vector2D pos);
	void addPlayerAnim(String username, Dictionary<String, Integer> colors, int selected);
}
