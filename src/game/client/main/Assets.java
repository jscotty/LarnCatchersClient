package game.client.main;

import game.client.sprites.Sprites;

import java.awt.image.BufferedImage;

import operator.LoadImageFrom;
import operator.SpriteSheet;

public class Assets {

	private static BufferedImage menuBG;
	private static BufferedImage btnWhite;
	private static BufferedImage btnBlack;
	private static BufferedImage textFieldBG_menu;
	private static BufferedImage textFieldBG_messages;
	
	private static SpriteSheet tileSheet =  new SpriteSheet();
	private static SpriteSheet playerSheet =  new SpriteSheet();
	private static BufferedImage playerSheetIMG;
	private static BufferedImage grass_tile;
	private static BufferedImage wall_tile;
	
	private static BufferedImage grass_prop_01;
	private static BufferedImage grass_prop_02;
	
	private static BufferedImage water_prop_01;
	private static BufferedImage welcome;
	private static BufferedImage border;

	private static BufferedImage charSelectionBG;
	
	private static BufferedImage hud;
	
	public void init(){
		menuBG = LoadImageFrom.loadImageFrom(Sprites.class, "menuBG");
		btnWhite = LoadImageFrom.loadImageFrom(Sprites.class, "btnbg");
		btnBlack = LoadImageFrom.loadImageFrom(Sprites.class, "btnbg2");
		hud = LoadImageFrom.loadImageFrom(Sprites.class, "HUD");
		textFieldBG_menu = LoadImageFrom.loadImageFrom(Sprites.class, "textfieldBG");
		textFieldBG_messages = LoadImageFrom.loadImageFrom(Sprites.class, "textfieldBG2");
		welcome = LoadImageFrom.loadImageFrom(Sprites.class, "welcome");
		charSelectionBG = LoadImageFrom.loadImageFrom(Sprites.class, "charSelect");
		
		tileSheet.setSpriteSheet(LoadImageFrom.loadImageFrom(Sprites.class, "sheet"));
		playerSheet.setSpriteSheet(LoadImageFrom.loadImageFrom(Sprites.class, "playersheet"));
		playerSheetIMG = LoadImageFrom.loadImageFrom(Sprites.class, "playersheet");
		grass_tile = tileSheet.getTile(0, 0, 64, 64);
		wall_tile = tileSheet.getTile(32, 0, 64, 64);
		
		water_prop_01 = tileSheet.getTile(0, 64, 32, 32);
		grass_prop_01 = tileSheet.getTile(32, 64, 32, 32);
		grass_prop_02 = tileSheet.getTile(64, 64, 32, 32);
		border = tileSheet.getTile(32*2, 0, 32, 32);
	}
	
	public static BufferedImage getPlayerSheetIMG() {
		return playerSheetIMG;
	}
	
	public static BufferedImage getCharSelectionBG() {
		return charSelectionBG;
	}
	
	public static BufferedImage getBorder() {
		return border;
	}
	public static BufferedImage getWelcome() {
		return welcome;
	}
	
	public static BufferedImage getTextFieldBG_messages() {
		return textFieldBG_messages;
	}
	
	public static BufferedImage getHud() {
		return hud;
	}
	
	public static BufferedImage getGrass_prop_01() {
		return grass_prop_01;
	}
	
	public static BufferedImage getGrass_prop_02() {
		return grass_prop_02;
	}
	
	public static BufferedImage getWater_prop_01() {
		return water_prop_01;
	}
	
	public static BufferedImage getGrass_tile() {
		return grass_tile;
	}
	
	public static BufferedImage getWall_tile() {
		return wall_tile;
	}
	
	public static BufferedImage getTextFieldBG_menu() {
		return textFieldBG_menu;
	}
	
	public static BufferedImage getBtnBlack() {
		return btnBlack;
	}
	
	public static BufferedImage getBtnWhite() {
		return btnWhite;
	}
	
	public static BufferedImage getMenuBG() {
		return menuBG;
	}
	
	public static SpriteSheet getTileSheet() {
		return tileSheet;
	}
	
	public static SpriteSheet getPlayerSheet() {
		return playerSheet;
	}

}
