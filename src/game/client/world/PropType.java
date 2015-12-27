package game.client.world;

import game.client.main.Assets;

import java.awt.image.BufferedImage;

public enum PropType {
	
	GRASS_01(0,Assets.getGrass_prop_01(),"Grass_01", "prop"),
	GRASS_02(0,Assets.getGrass_prop_02(),"Grass_02", "prop"),
	WATER(1,Assets.getWater_prop_01(),"Water", "prop");
	
	private int id;
	private BufferedImage image;
	private String name;
	private String tag;
	
	private PropType(int id, BufferedImage image, String name, String tag){
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
