package game.client.data;

import game.client.players.Player;

import java.util.concurrent.CopyOnWriteArrayList;

import operator.Animator;

public class PlayersData {
	public CopyOnWriteArrayList<Player> players = new CopyOnWriteArrayList<Player>();
	
	public void addPlayer(Player player){
		players.add(player);
	}
	
	public void removePlayer(Player player){
		players.remove(player);
	}
	
	public void removeAllPlayers(){
		for (Player player : players) {
			players.remove(player);
		}
	}
	
	public void start(Animator anim){
		for (Player player : players) {
			if(player.getUsername().equals(PlayerData.playerName)){
				player.start();
				player.setAnim(anim);
			}
		}
	}
}
