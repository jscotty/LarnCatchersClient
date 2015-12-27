package game.client.managers;

import game.client.main.Animations;
import game.client.main.Assets;
import game.client.monsters.MonstersList;
import game.client.ui.CharacterSelection;
import game.client.ui.Chat;
import game.client.ui.CustomFont;
import game.client.ui.InputField;
import game.client.ui.UIButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class HudManager {
	
	private InputField textField;
	private Font font = CustomFont.loadFont(CustomFont.class, "small_pixel.ttf", 12);
	private Chat chat;
	
	private CopyOnWriteArrayList<UIButton> buttons = new CopyOnWriteArrayList<UIButton>();
	
	private MonstersList ml;
	private CharacterSelection cs = new CharacterSelection();

	public HudManager() {
		
	}
	
	public void init(){
		textField = new InputField(Assets.getTextFieldBG_messages(), 53,527,500,20).isMessageBox().setTextColor(Color.black);
		chat = new Chat(font);
		ml = new MonstersList(580, 120, 4, 2);
		ml.init();
		cs.init();
		
		buttons.add(new UIButton(Animations.getLarnBtn(), 580, 470, 46,46).setTag("mlBtn"));
		buttons.add(new UIButton(Animations.getBackPackBtn(), 650, 470,46,46).setTag("invBtn"));
		buttons.get(0).nextBtnSprite();
	}
	
	public void tick(double deltaTime){
		textField.tick();
		chat.tick();
		ml.tick(deltaTime);
		
		if(!cs.isDone())
			cs.tick();
		
		int count = 0;
		for (UIButton uiButton : buttons) {
			uiButton.tick();
			
			if(uiButton.isPressed()){
				for (UIButton btn : buttons) {
					if(btn.getTag() != uiButton.getTag()){
						btn.resetBtnSprite();
					} else {
					}
				}
				count++;
				if(count == 1){
					uiButton.nextBtnSprite();
					uiButton.resetButton();
				}
			}
		}
	}
	
	public void render(Graphics2D g){
		g.drawImage(Assets.getHud(), 0, 0, 800,600,null);
		textField.render(g);
		chat.render(g);
		ml.render(g);
		
		if(!cs.isDone())
			cs.render(g);

		for (UIButton uiButton : buttons) {
			uiButton.render(g);
		}
	}
	
	public CharacterSelection getCs() {
		return cs;
	}

}
