package game.client.main;

import game.client.listeners.NetworkListenerClient;
import game.client.main.Packet.*;
import game.client.managers.GameStateManager;

import java.awt.Color;
import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.CopyOnWriteArrayList;

import operator.Vector2D;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

public class Connect {
	
	private Client client;
	private String ipadress = "127.0.0.1";
	private int tcp = 54555;
	
	private String username, password;
	
	private NetworkListenerClient networkListener;
	private GameStateManager gsm;
	
	private boolean register = false;
	
	public Connect(GameStateManager gsm){
		client = new Client();
		this.gsm = gsm;
	}
	public Connect setUserData(String username, String password){
		this.username = username;
		this.password = password;
		
		return this;
	}
	public void setPosition(Vector2D pos, String username){
		networkListener.setPos(pos, username);
	}
	
	public Connect isRegistering(){
		register = true;
		return this;
	}

	public void connect() {
		client.start();
		
		registerPackets();
		
		networkListener = new NetworkListenerClient();
		networkListener.addUserData(username, password, register);
		networkListener.init(client,gsm);
		
		
		client.addListener(networkListener);
		
		try {
			client.connect(50000, ipadress, tcp);
		} catch (IOException e) {
			e.printStackTrace();
			client.stop();
		}
	}
	
	public boolean isConnected(){
		if(client.isConnected()){
			return true;
		} else {
			return false;
		}
	}
	
	public void registerPackets(){
		Kryo kryo = client.getKryo();
		kryo.register(PacketRegister.class);
		kryo.register(Packet0LoginRequest.class);
		kryo.register(Packet1LoginAnswer.class);
		kryo.register(Packet2Message.class);
		kryo.register(Packet3Disconnect.class);
		kryo.register(MovePacketServer.class);
		kryo.register(LogOffPacket.class);
		kryo.register(NewPlayerPacket.class);
		kryo.register(MovePacket.class);
		kryo.register(Hashtable.class);
		kryo.register(Vector2D.class);
		kryo.register(CopyOnWriteArrayList.class);
		kryo.register(WorldTilesPacket.class);
		kryo.register(ChatPacket.class);
		kryo.register(ColorPacketChar.class);
		kryo.register(Packet.class);
		kryo.register(Color.class);
		kryo.register(int[][].class);
		kryo.register(int[].class);
		kryo.register(int.class);
		kryo.register(float[].class);
		kryo.register(float.class);
		kryo.register(String.class);
		kryo.register(Integer.class);
	}
	
	public Client getClient() {
		return client;
	}
}
