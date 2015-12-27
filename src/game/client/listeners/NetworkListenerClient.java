package game.client.listeners;

import java.awt.Color;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.CopyOnWriteArrayList;

import operator.Animator;
import operator.SpriteSheet;
import operator.Vector2D;
import game.client.data.PlayerData;
import game.client.data.PlayersData;
import game.client.gameloop.GameLoop;
import game.client.handlers.MenuHandler;
import game.client.main.Animations;
import game.client.main.Assets;
import game.client.main.NetworkInterface;
import game.client.main.Recoloring;
import game.client.main.Packet.*;
import game.client.managers.GameStateManager;
import game.client.managers.HudManager;
import game.client.players.Player;
import game.client.states.MenuState;
import game.client.states.PlayState;
import game.client.ui.Chat;
import game.client.world.World;
import game.client.world.WorldData;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class NetworkListenerClient extends Listener {
	
	private static Client client;
	private String username,password;
	private PacketRegister pr;
	private boolean register;
	
	private Vector2D pos = new Vector2D(0,0);
	
	public static Dictionary<String, Vector2D> positions = new Hashtable<String, Vector2D>();
	public static CopyOnWriteArrayList<String> chat = new CopyOnWriteArrayList<String>();
	public static CopyOnWriteArrayList<String> players = new CopyOnWriteArrayList<String>();
	
	private boolean first = true;
	
	private GameStateManager gsm;

	private NetworkInterface ni = new NetworkInterface() {
		@Override
		public void addPlayer(String username, Vector2D pos) {
			if(first){
				for (String player : players) {
					GameLoop.playersData.addPlayer(new Player(player, positions.get(player), client));
					first = false;
				}
			} else {
				GameLoop.playersData.addPlayer(new Player(username, positions.get(username), client));
			}
		}

		@Override
		public void setPlayerPath(String username, Vector2D pos) {
			for (Player p : GameLoop.playersData.players) {
				if(p.getUsername().equals(username)){
					p.setPath(pos);
				}
			}
			
		}

		@Override
		public void addPlayerAnim(String username, Dictionary<String, Integer> colors, int selected) {
			
			System.out.println(colors.get("head"));
			int yPos = 0;
			if(selected == 1) yPos = 32;
			if(selected == 2) yPos = 64;
			if(selected == 3) yPos = 96;
			
			
			for (Player p : GameLoop.playersData.players) {
				if(p.getUsername().equals(username)){
					SpriteSheet sheet = new SpriteSheet();
					sheet = Recoloring.recolorCharacterOther(Assets.getPlayerSheetIMG(), colors);
					p.setAnim(GameLoop.animations.getAnim(sheet, 2, 0, yPos, 32, 32));
				}
			}
		}
	};
	
	public void init(Client client, GameStateManager gsm){
		NetworkListenerClient.client = client;
		this.gsm = gsm;
		
	}
	
	public void addUserData(String name, String pass, boolean register){
		username = name; password = pass;
		pr = new PacketRegister();
		pr.username = username; pr.password = password;
		this.register = register;
		positions.put(name, pos);
	}
	
	public void setPos(Vector2D pos, String username){
		this.pos = pos;
		positions.put(username, pos);
	}
	
	@Override
	public void connected(Connection con) {
		if(register)
			client.sendTCP(pr);
	}
	@Override
	public void disconnected(Connection con) {
		for (String player : players) {
			players.remove(player);
		}
		GameLoop.playersData.removeAllPlayers();
		con.close();
		
		
		MenuHandler.accepted = false;
		GameStateManager.states.push(new MenuState(gsm));
		GameStateManager.states.peek().init();
		
		
	}
	@Override
	public void received(Connection con, Object obj) {
		if(obj instanceof PacketRegister){
			boolean accepted = ((PacketRegister)obj).accepted;
			PacketRegister pr = (PacketRegister)obj;
			MenuHandler.accepted = accepted;
			PlayerData.playerName = pr.username;
			
			if(!accepted){
				con.close();
			}
		} else if(obj instanceof Packet2Message){
		} else if(obj instanceof NewPlayerPacket){
			NewPlayerPacket npp = (NewPlayerPacket)obj;
			positions = npp.positions;
			players = npp.players;
			ni.addPlayer(npp.username, npp.pos);
			
			World.getHud().getCs().sendCharacter();
			
		} else if(obj instanceof MovePacketServer){
			MovePacketServer mps = (MovePacketServer)obj;
			
			ni.setPlayerPath(mps.username, mps.positions.get(mps.username));
			
			positions = mps.positions;
		} else if(obj instanceof LogOffPacket){
			String logOffUser = ((LogOffPacket)obj).username;
			
			for (String player : players) {
				for (Player p : GameLoop.playersData.players) {
					if(p.getUsername().equals(logOffUser))
						GameLoop.playersData.players.remove(p);
				}
				if(player.equals(logOffUser)){
					players.remove(player);
				}
			}
		} else if(obj instanceof WorldTilesPacket){
			WorldTilesPacket wtp = (WorldTilesPacket)obj;
			WorldData.tiles = wtp.tiles;
			WorldData.props = wtp.props;
		} else if(obj instanceof ChatPacket){
			ChatPacket cp = (ChatPacket)obj;
			chat.add(cp.chat);
			Chat.resetPos();
			
		} else if(obj instanceof ColorPacketChar){
			ColorPacketChar cpc = (ColorPacketChar)obj;
			
			if(cpc.username != PlayerData.playerName) ni.addPlayerAnim(cpc.username, cpc.character, cpc.selected);
			
		}
	}
}
