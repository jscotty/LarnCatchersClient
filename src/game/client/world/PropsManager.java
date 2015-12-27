package game.client.world;

import java.awt.Graphics2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class PropsManager {

	public CopyOnWriteArrayList<Prop> props = new CopyOnWriteArrayList<Prop>();
	
	public PropsManager(){
		
	}
	
	public void tick(double deltaTime){
		for (Prop prop : props) {
			if(prop != null)
				prop.tick(deltaTime);
		}
	}
	
	public void render(Graphics2D g){
		for (Prop prop : props) {
			if(prop != null)
				prop.render(g);
		}
	}
}
