package game.client.monsters;

import game.client.main.Animations;
import operator.Animator;

public enum MonsterType {
	
	LARP(Animations.getLarnAnim_front(), Animations.getLarnAnim_back(), 1, ElementType.NORMAL),
	FIRE_LARP(Animations.getLarnAnim_front(), Animations.getLarnAnim_back(), 2, ElementType.FIRE);
	
	private Animator animFront;
	private Animator animBack;
	private int power;
	private ElementType type;
	
	private MonsterType(Animator animFront, Animator animBack,int power, ElementType type){
		this.animFront = animFront;
		this.animBack = animBack;
		this.power = power;
		this.type = type;
	}
	
	public Animator getAnimBack() {
		return animBack;
	}
	public Animator getAnimFront() {
		return animFront;
	}
	public int getPower() {
		return power;
	}
	public ElementType getType() {
		return type;
	}
}
