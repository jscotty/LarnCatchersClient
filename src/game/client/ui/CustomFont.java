package game.client.ui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class CustomFont {
	
	public CustomFont(){
		registerFonts();
	}
	
	private void registerFonts(){
		//loadFont(CustomFont.class,"small_pixel.ttf");
	}

	public static Font loadFont(Class<?> classfile, String path, int size){
		URL url = classfile.getResource(path);
		
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		font = font.deriveFont(Font.PLAIN, size);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(font);
		
		return font;
	}

}
