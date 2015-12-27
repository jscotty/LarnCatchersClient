package game.client.world;

import game.client.main.Assets;

import java.awt.image.BufferedImage;

public enum TileType {
	
	GRASS(0,Assets.getGrass_tile(),"Grass", "grass"),
	WALL(1,Assets.getWall_tile(),"Wall", "unWalkable");
	
	private int id;
	private BufferedImage image;
	private String name;
	private String tag;
	
	private TileType(int id, BufferedImage image, String name, String tag){
		this.id = id;
		this.image = image;
		this.name = name;
		this.tag = tag;
	}
	
	public int getId() {
		return id;
	}
	public BufferedImage getImage() {
		return image;
	}
	public String getName() {
		return name;
	}
	public String getTag() {
		return tag;
	}
}
