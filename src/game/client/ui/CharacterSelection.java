package game.client.ui;

import game.client.data.PlayerData;
import game.client.gameloop.GameLoop;
import game.client.listeners.MouseEventListener;
import game.client.main.Animations;
import game.client.main.Assets;
import game.client.main.Recoloring;
import game.client.main.Packet.ColorPacketChar;
import game.client.players.CharacterPart;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.CopyOnWriteArrayList;

import operator.Animator;
import operator.Vector2D;

public class CharacterSelection {
	
	private CopyOnWriteArrayList<Animator> characters = new CopyOnWriteArrayList<Animator>();
	private boolean hoover;
	private Vector2D charPos = new Vector2D(400, 0);
	private int selected = 0;
	
	private ArrayList<UIChooseBar> chooseBars = new ArrayList<UIChooseBar>();
	
	private int charWidth = 60, charHeight = 60, count = 0;;

	private Dictionary<String, Color> colors = new Hashtable<String, Color>();
	
	private UIButton previewBtn;
	private UIButton playBtn;
	
	private boolean done;
	Dictionary<CharacterPart,Color> color = new Hashtable<CharacterPart, Color>();
	
	public void init(){
		previewBtn = new UIButton(Assets.getBtnBlack(), 100,330,100,30,"preview").setFontColor(Color.white);
		playBtn = new UIButton(Assets.getBtnBlack(), 230,330,100,30,"play").setFontColor(Color.white);
		characters.add(Animations.getMan_01());
		characters.add(Animations.getMan_02());
		characters.add(Animations.getWoman_01());
		characters.add(Animations.getWoman_02());
		
		chooseBars.add(new UIChooseBar(100, 100, 150, 20, null, null).setColor(Color.BLACK,Color.BLACK).setTextColor(Color.white).setPart(CharacterPart.HEAD).setMessage("Select hair color:"));
		chooseBars.add(new UIChooseBar(100, 160, 150, 20, null, null).setColor(Color.BLACK,Color.BLACK).setTextColor(Color.white).setPart(CharacterPart.SKIN).setMessage("Select skin color:"));
		chooseBars.add(new UIChooseBar(100, 220, 150, 20, null, null).setColor(Color.BLACK,Color.BLACK).setTextColor(Color.white).setPart(CharacterPart.SHIRT).setMessage("Select shirt color:"));
		chooseBars.add(new UIChooseBar(100, 280, 150, 20, null, null).setColor(Color.BLACK,Color.BLACK).setTextColor(Color.white).setPart(CharacterPart.BOOTS).setMessage("Select boots color:"));
		
		chooseBars.get(0).addOption("choose");chooseBars.get(0).addOption("blond");chooseBars.get(0).addOption("brown");
			chooseBars.get(0).addOption("red");chooseBars.get(0).addOption("purple");chooseBars.get(0).addOption("blue");
			chooseBars.get(0).addOption("green");chooseBars.get(0).addOption("black");
		
		chooseBars.get(1).addOption("choose");chooseBars.get(1).addOption("blank");chooseBars.get(1).addOption("white");
			chooseBars.get(1).addOption("brown");chooseBars.get(1).addOption("black");chooseBars.get(1).addOption("asian");
			chooseBars.get(1).addOption("demon");chooseBars.get(1).addOption("alien");
			
		chooseBars.get(2).addOption("choose");chooseBars.get(2).addOption("green");chooseBars.get(2).addOption("red");
			chooseBars.get(2).addOption("pink");chooseBars.get(2).addOption("purple");chooseBars.get(2).addOption("blue");
			chooseBars.get(2).addOption("brown");chooseBars.get(2).addOption("black");chooseBars.get(2).addOption("yellow");
			
		chooseBars.get(3).addOption("choose");chooseBars.get(3).addOption("brown");chooseBars.get(3).addOption("red");
			chooseBars.get(3).addOption("pink");chooseBars.get(3).addOption("purple");chooseBars.get(3).addOption("blue");
			chooseBars.get(3).addOption("green");chooseBars.get(3).addOption("black");chooseBars.get(3).addOption("yellow");
			
		colors.put("blond", new Color(225,221,0));
		colors.put("brown", new Color(183,59,25));
		colors.put("red", new Color(225,0,20));
		colors.put("demon", new Color(140,0,0));
		colors.put("purple", new Color(131,0,225));
		colors.put("alien", new Color(111,0,225));
		colors.put("asian", new Color(234,195,11));
		colors.put("yellow", new Color(234,255,11));
		colors.put("pink", new Color(225,20,239));
		colors.put("blue", new Color(25,0,225));
		colors.put("green", new Color(42,225,0));
		colors.put("black", new Color(0,0,0));
		colors.put("white", new Color(225,225,225));
		colors.put("blank", new Color(249,213,177));
		colors.put("brown", new Color(126,83,43));

		for (UIChooseBar cb : chooseBars) {
			cb.init();
		}
		
		for (Animator chars : characters) {
			chars.play();
		}
	}
	
	public void tick(){
		previewBtn.tick();
		playBtn.tick();
		for (int i = 0; i < characters.size(); i++) {
			Rectangle rect = new Rectangle(400,70+(i*80),60,60);
			
			if(rect.contains(MouseEventListener.mousePoint())){
				hoover = true;
				charPos.yPos = 70+(i*80);
				selected = i;
			}
		}
		
		for (UIChooseBar cb : chooseBars) {
			cb.tick();
		}
		
		if(previewBtn.isPressed()){
			count++;
			if(count == 1) change();
		}if(playBtn.isPressed()){
			count++;
			if(count == 1) play();
		} else {
			count = 0;
		}
	}
	
	private void change(){
		color = new Hashtable<CharacterPart, Color>();
		for (UIChooseBar cb : chooseBars) {
			if(colors.get(cb.getOption())!= null)
				color.put(cb.getPart(), colors.get(cb.getOption()));
		}
		
		GameLoop.animations.updateChars(Recoloring.recolorCharacter(Assets.getPlayerSheetIMG(), color));
	}
	
	public void play(){
		if(selected == 0) GameLoop.playersData.start(Animations.getMan_01());
		if(selected == 1) GameLoop.playersData.start(Animations.getMan_02());
		if(selected == 2) GameLoop.playersData.start(Animations.getWoman_01());
		if(selected == 3) GameLoop.playersData.start(Animations.getWoman_02());
		
		done = true;

		Dictionary<String, Integer> character = new Hashtable<String, Integer>();
		if(color.get(CharacterPart.HEAD)!=null) character.put("head", color.get(CharacterPart.HEAD).getRGB());
		if(color.get(CharacterPart.SKIN)!=null) character.put("skin", color.get(CharacterPart.SKIN).getRGB());
		if(color.get(CharacterPart.SHIRT)!=null) character.put("shirt", color.get(CharacterPart.SHIRT).getRGB());
		if(color.get(CharacterPart.BOOTS)!=null) character.put("boots", color.get(CharacterPart.BOOTS).getRGB());
		
		ColorPacketChar cpc = new ColorPacketChar();
		cpc.character = character;
		cpc.username = PlayerData.playerName;
		cpc.selected = selected;
		GameLoop.connect.getClient().sendTCP(cpc);
		
	}
	
	public void sendCharacter(){
		Dictionary<String, Integer> character = new Hashtable<String, Integer>();
		if(color.get(CharacterPart.HEAD)!=null) character.put("head", color.get(CharacterPart.HEAD).getRGB());
		if(color.get(CharacterPart.SKIN)!=null) character.put("skin", color.get(CharacterPart.SKIN).getRGB());
		if(color.get(CharacterPart.SHIRT)!=null) character.put("shirt", color.get(CharacterPart.SHIRT).getRGB());
		if(color.get(CharacterPart.BOOTS)!=null) character.put("boots", color.get(CharacterPart.BOOTS).getRGB());
		
		ColorPacketChar cpc = new ColorPacketChar();
		cpc.character = character;
		cpc.username = PlayerData.playerName;
		cpc.selected = selected;
		GameLoop.connect.getClient().sendTCP(cpc);
	}
	
	public void render(Graphics2D g){
		g.drawImage(Assets.getCharSelectionBG(), 65,65,null);

		previewBtn.render(g);
		playBtn.render(g);
		int i = 0;
		for (Animator c : characters) {
			g.drawImage(c.sprite, 400,70+(i*80),charWidth,charHeight,null);
			
			c.update(System.currentTimeMillis());
			i++;
		}
		
		if(hoover){
			int range = 5;
			g.drawRect((int)(charPos.xPos - (range/2)), (int)(charPos.yPos-(range/2)), charWidth+range, charHeight+range);
		}
		
		for (int j = chooseBars.size()-1; j >= 0; j--) {
			chooseBars.get(j).render(g);
		}
	}
	
	public boolean isDone(){
		return done;
	}
}
