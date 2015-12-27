package game.client.main;

public class Camera {
	
	private static int xPos, yPos;
	
	public static void setCameraPosition(int xPos, int yPos){
		Camera.xPos = xPos - 300;
		Camera.yPos = yPos - 200;
	}
	
	public static int getxPos() {
		return xPos;
	}
	
	public static int getyPos() {
		return yPos;
	}

}
