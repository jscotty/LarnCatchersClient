package game.client.monsters;

public enum ElementType {

	NORMAL(""),
	WATER("GRASS"),
	FIRE("WATER"),
	GRASS("FIRE");
	
	private String weakness;
	
	private ElementType(String weakness){
		this.weakness = weakness;
	}
	
	public String getWeakness() {
		return weakness;
	}
}
