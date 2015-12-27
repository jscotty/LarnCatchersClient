package game.client.players;

import game.client.data.PlayerData;
import game.client.listeners.MouseEventListener;
import game.client.listeners.NetworkListenerClient;
import game.client.main.Camera;
import game.client.main.Packet.MovePacket;
import game.client.math.AStar;
import game.client.ui.CustomFont;
import game.client.world.Tile;
import game.client.world.WorldData;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.concurrent.CopyOnWriteArrayList;

import com.esotericsoftware.kryonet.Client;

import operator.Animator;
import operator.Vector2D;

public class Player {
	
	private String username;
	private Vector2D pos;
	private Vector2D movePos;
	private int moveSpeed = 1;
	private Client client;
	
	private int nextTile = 1;
	
	private boolean move = false;
	
	private int size = 30;
	
	private Animator anim;

	private Rectangle mouseRect = new Rectangle(50,49, 505, 345);
	private AStar astar = new AStar();
	private CopyOnWriteArrayList<Tile> path = new CopyOnWriteArrayList<Tile>();
	private int count = 0;
	
	private Font font = CustomFont.loadFont(CustomFont.class, "small_pixel.ttf", 12);
	
	public Player(String username, Vector2D pos, Client client){
		this.username = username;
		this.pos = pos;
		movePos = pos;
		this.client = client;
	}
	
	public void tick(double deltaTime){

		if(username.equals(PlayerData.playerName) && move){
			if(mouseRect.contains(MouseEventListener.mousePoint())){
				count++;
				if(count == 1) MouseEventListener.resetPressed();
				if(MouseEventListener.isPressed()){
					nextTile = 1;
					MovePacket movePack = new MovePacket();
					movePos = new Vector2D(MouseEventListener.mouseLocation().xPos + Camera.getxPos(),MouseEventListener.mouseLocation().yPos + Camera.getyPos());
					movePack.dir = movePos;
					movePack.username = username;
					client.sendTCP(movePack);
					
					MouseEventListener.resetPressed();
				}
			}
			Camera.setCameraPosition((int)pos.xPos, (int)pos.yPos);
		}
		
		Vector2D nextTilePos = pos;
		if(path.size() >= 1 && nextTile < path.size()){
			nextTilePos = path.get(nextTile).getTileLocation();
		} else {
			nextTile = 1;
		}
			
		
		Vector2D moveDir = new Vector2D(nextTilePos.xPos - pos.xPos, nextTilePos.yPos - pos.yPos);
		
		Vector2D vel = new Vector2D(0,0);
		
		float magnitude = moveDir.magnitude();
		if(magnitude < 1){
			if(nextTile < path.size()-1) {
				nextTile++;
			} else if(path.size() > 1) {
				nextTile = path.size()-1;
			} else {
				nextTile = 1;
			}
		} else {
			moveDir.normalize();
			moveDir.xPos = moveDir.xPos * moveSpeed;
			moveDir.yPos = moveDir.yPos * moveSpeed;
			
			vel = moveDir;
			
			vel.xPos = Math.round(vel.xPos);
			vel.yPos = Math.round(vel.yPos);

			//System.out.println("("+vel.xPos + ","+ vel.yPos+")");
			
			pos.xPos += vel.xPos;
			pos.yPos += vel.yPos;
			
		}
		
	}
	
	public void render(Graphics2D g){
		int l = 1;
		for (Tile tile : path) {
			if(l >= path.size()-1){
				l = path.size()-1;
			}
			if(tile.equals(path.get(nextTile-1))){
				
			} else {
				g.setColor(Color.black);
				
				
				//System.out.println("test: "+ nextTile + " " + l);
				if(nextTile-1 >= l){
					
				} else {
					
					if(l+1 < path.size()){
						g.drawLine(	(int)tile.getTileLocation().xPos - Camera.getxPos() + (size/2),
								(int)tile.getTileLocation().yPos- Camera.getyPos()+ (size/2),
								
								(int)path.get(l).getTileLocation().xPos - Camera.getxPos()+ (size/2),
								(int)path.get(l).getTileLocation().yPos - Camera.getyPos()+ (size/2));
					}
				}
				
			}
			l++;
		}
		
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString(username, (int)pos.xPos-2 - Camera.getxPos(), (int)pos.yPos-2 - Camera.getyPos());
		g.setColor(Color.yellow);
		g.drawString(username, (int)pos.xPos-4 - Camera.getxPos(), (int)pos.yPos-4 - Camera.getyPos());
		
		if(anim != null){
			g.drawImage(anim.sprite,(int)pos.xPos - Camera.getxPos(), (int)pos.yPos - Camera.getyPos(), size, size, null);
			anim.update(System.currentTimeMillis());
		} else {
			g.setColor(Color.red);
			g.fillRect((int)pos.xPos - Camera.getxPos(), (int)pos.yPos - Camera.getyPos(), size, size);
		}
		
	}
	
	public void setPath(Vector2D movePos){
		this.movePos = movePos;
		
		if(astar.search(new Point((int)pos.xPos,(int)pos.yPos), new Point((int)movePos.xPos,(int)movePos.yPos)) == null){
			
		} else {
			path = astar.search(new Point((int)pos.xPos,(int)pos.yPos), new Point((int)movePos.xPos,(int)movePos.yPos));
			nextTile = 1;
		}
	}
	
	public void start(){
		move = true;
	}
	
	public void setAnim(Animator anim){
		this.anim = anim;
		this.anim.play();
	}
	
	public String getUsername() {
		return username;
	}
	public Vector2D getPos() {
		return pos;
	}
	public Vector2D getMovePos() {
		return movePos;
	}
	public void setMovePos(Vector2D movePos) {
		this.movePos = movePos;
	}
}
