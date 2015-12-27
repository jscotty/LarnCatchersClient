package game.client.main;

import game.client.sprites.Sprites;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import operator.Animator;
import operator.LoadImageFrom;
import operator.SpriteSheet;

public class Animations {
	
	private static Animator larnAnim_front;
	private ArrayList<BufferedImage> larnFrontList = new ArrayList<BufferedImage>();
	
	private static Animator larnAnim_back;
	private ArrayList<BufferedImage> larnBackList = new ArrayList<BufferedImage>();
	
	private static Animator backPackBtn;
	private ArrayList<BufferedImage> backPackBtn_list = new ArrayList<BufferedImage>();
	
	private static Animator larnBtn;
	private ArrayList<BufferedImage> larnBtn_list = new ArrayList<BufferedImage>();
	
	private static Animator man_01;
	private ArrayList<BufferedImage> man_01_list = new ArrayList<BufferedImage>();
	
	private static Animator man_02;
	private ArrayList<BufferedImage> man_02_list = new ArrayList<BufferedImage>();
	
	private static Animator woman_01;
	private ArrayList<BufferedImage> woman_01_list = new ArrayList<BufferedImage>();
	
	private static Animator woman_02;
	private ArrayList<BufferedImage> woman_02_list = new ArrayList<BufferedImage>();
	
	public Animations(){
		
	}
	
	public void init(){
		
		SpriteSheet monsterSheet = new SpriteSheet();
		monsterSheet.setSpriteSheet(LoadImageFrom.loadImageFrom(Sprites.class, "monsters"));
		
		for (int i = 0; i < 2; i++) {
			larnFrontList.add(monsterSheet.getTile(32*i, 0, 32, 32));
		}
		for (int i = 0; i < 2; i++) {
			larnBackList.add(monsterSheet.getTile((32*i) + 64, 0, 32, 32));
		}
		for (int i = 0; i < 2; i++) {
			backPackBtn_list.add(Assets.getTileSheet().getTile(32*i, 32, 32, 32));
		}
		for (int i = 0; i < 2; i++) {
			larnBtn_list.add(Assets.getTileSheet().getTile((32*i) + 64, 32, 32, 32));
		}
		for (int i = 0; i < 2; i++) {
			man_01_list.add(Assets.getPlayerSheet().getTile((32*i), 0, 32, 32));
		}
		for (int i = 0; i < 2; i++) {
			woman_01_list.add(Assets.getPlayerSheet().getTile((32*i), 32, 32, 32));
		}
		for (int i = 0; i < 2; i++) {
			man_02_list.add(Assets.getPlayerSheet().getTile((32*i), 64, 32, 32));
		}
		for (int i = 0; i < 2; i++) {
			woman_02_list.add(Assets.getPlayerSheet().getTile((32*i), 96, 32, 32));
		}
		
		larnAnim_front = new Animator(larnFrontList);
		larnAnim_front.setSpeed(50);

		larnAnim_back = new Animator(larnBackList);
		larnAnim_back.setSpeed(50);

		man_01 = new Animator(man_01_list);
		man_01.setSpeed(200);

		man_02 = new Animator(man_02_list);
		man_02.setSpeed(200);

		woman_01 = new Animator(woman_01_list);
		woman_01.setSpeed(200);

		woman_02 = new Animator(woman_02_list);
		woman_02.setSpeed(200);

		backPackBtn = new Animator(backPackBtn_list);

		larnBtn = new Animator(larnBtn_list);
	}
	
	public void updateChars(SpriteSheet sheet){
		// removing current 
		for (int i = 0; i < man_01_list.size(); i++) {
			man_01_list.remove(i);
			i--;
		}
		for (int i = 0; i < man_02_list.size(); i++) {
			man_02_list.remove(i);
			i--;
		}
		for (int i = 0; i < woman_01_list.size(); i++) {
			woman_01_list.remove(i);
			i--;
		}
		for (int i = 0; i < woman_02_list.size(); i++) {
			woman_02_list.remove(i);
			i--;
		}

		for (int i = 0; i < 2; i++) {
			man_01_list.add(sheet.getTile((32*i), 0, 32, 32));
		}
		for (int i = 0; i < 2; i++) {
			woman_01_list.add(sheet.getTile((32*i), 32, 32, 32));
		}
		for (int i = 0; i < 2; i++) {
			man_02_list.add(sheet.getTile((32*i), 64, 32, 32));
		}
		for (int i = 0; i < 2; i++) {
			woman_02_list.add(sheet.getTile((32*i), 96, 32, 32));
		}

		man_01 = new Animator(man_01_list);
		man_01.setSpeed(200);
		man_01.play();

		man_02 = new Animator(man_02_list);
		man_02.setSpeed(200);
		man_02.play();

		woman_01 = new Animator(woman_01_list);
		woman_01.setSpeed(200);
		woman_01.play();

		woman_02 = new Animator(woman_02_list);
		woman_02.setSpeed(200);
		woman_02.play();
	}
	
	public Animator getAnim(SpriteSheet sheet, int size, int xPos, int yPos, int width, int height){
		Animator anim;
		
		ArrayList<BufferedImage> anim_list = new ArrayList<BufferedImage>();
		
		for (int i = 0; i < size; i++) {
			anim_list.add(sheet.getTile((32*i)+xPos, yPos, width, height));
		}
		
		anim = new Animator(anim_list);
		anim.play();
		anim.setSpeed(200);
		
		return anim;
	}
	
	public static Animator getMan_01() {
		return man_01;
	}
	public static Animator getMan_02() {
		return man_02;
	}
	public static Animator getWoman_01() {
		return woman_01;
	}
	public static Animator getWoman_02() {
		return woman_02;
	}
	public static Animator getLarnAnim_front() {
		return larnAnim_front;
	}
	public static Animator getLarnAnim_back() {
		return larnAnim_back;
	}
	public static Animator getLarnBtn() {
		return larnBtn;
	}
	public static Animator getBackPackBtn() {
		return backPackBtn;
	}
	
}
