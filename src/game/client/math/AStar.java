package game.client.math;

import java.awt.Point;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;

import game.client.states.PlayState;
import game.client.world.Tile;
import game.client.world.World;
import game.client.world.WorldData;

public class AStar {
	private Tile currentTile;
	private Tile neighbor;
	
	private int tileSize = 64;
	
	public CopyOnWriteArrayList<Tile> search(Point start, Point end){
		
		tileSize = WorldData.tileSize;
		
		CopyOnWriteArrayList<Tile> openList = new CopyOnWriteArrayList<Tile>();
		CopyOnWriteArrayList<Tile> closedList = new CopyOnWriteArrayList<Tile>();
		CopyOnWriteArrayList<Tile> neighbors = new CopyOnWriteArrayList<Tile>();
		double gScore;
		boolean gScoreBest;
		
		PlayState.world.resetTiles();
		if(PlayState.world.getTile(start.x / tileSize, start.y / tileSize) == null || PlayState.world.getTile(end.x / tileSize, end.y / tileSize) == null){
			return null;
		} 
		openList.add(PlayState.world.getTile(start.x / tileSize, start.y / tileSize));
		currentTile = openList.get(0);
		while(openList.size() > 0){
			//sorting the arrayList
			Collections.sort(openList , new Comparator<Tile>() {
				public int compare(Tile a, Tile b){
					if(a.f > b.f || a.f == b.f && a.h > b.h){
						return 1;
					} else {
						return -1;
					}
				}
			});
			currentTile = openList.get(0);
			
			//found path
			if(currentTile.contains(end)){
				return getPath(currentTile);
			}
			
			openList.remove(0);
			
			closedList.add(currentTile);
			currentTile.isClosed = true;
			currentTile.isOpen = false;
			
			if(currentTile.neighbors.size() <= 0){
				currentTile.neighbors = getNeighbors(PlayState.world, currentTile);
			}
			
			
			neighbors = currentTile.neighbors;
			
			int l = neighbors.size();
			for (int i = 0; i < l; i++) {
				neighbor = neighbors.get(i);
				if(neighbor.isClosed || neighbor.isSolid()){
					continue;
				}
				
				gScore = currentTile.g + 1;
				
				gScoreBest = false;
				
				if(!neighbor.isOpen){
					gScoreBest = true;
					neighbor.h = heuristic(new Point((int)neighbor.getTileLocation().xPos, (int)neighbor.getTileLocation().yPos), end);
					openList.add(neighbor);
					neighbor.isOpen = true;
				} else if(gScore < neighbor.g){
					gScoreBest = true;
				}
				
				if(gScoreBest){
					neighbor.parent = currentTile;
					neighbor.g = gScore;
					neighbor.f = neighbor.g + neighbor.h;
				}
			}
		}
		System.err.println("pathfinding failed");
		return null;
		
	}
	
	private CopyOnWriteArrayList<Tile> getPath(Tile currentTile){
		CopyOnWriteArrayList<Tile> path = new CopyOnWriteArrayList<Tile>();

		while(currentTile.parent != null) {
			path.add(currentTile);
			currentTile = currentTile.parent;
		}
		Collections.reverse(path);
		return path;
	}
	
	
	private static int heuristic(Point pos0, Point pos1) {
		// Manhattan distance
		int d1 = Math.abs (pos1.x - pos0.x);
		int d2 = Math.abs (pos1.y - pos0.y);
		return d1 + d2;
	}
	
	private CopyOnWriteArrayList<Tile> getNeighbors(World world, Tile tile) {
		CopyOnWriteArrayList<Tile> neighbors = new CopyOnWriteArrayList<Tile>();
		int x = (int)tile.getTileLocation().xPos / tileSize;
		int y = (int)tile.getTileLocation().yPos / tileSize;
 
		if(world.getTile(x-1, y) != null) {
			neighbors.add(world.getTile(x-1, y));
		}
		if(world.getTile(x+1, y) != null) {
			neighbors.add(world.getTile(x+1, y));
		}
		if(world.getTile(x, y-1) != null) {
			neighbors.add(world.getTile(x, y-1));
		}
		if(world.getTile(x, y+1) != null) {
			neighbors.add(world.getTile(x, y+1));
		}

		return neighbors;
	}
	
	public Tile getNeighbor() {
		return neighbor;
	}
}
