package game.client.main;

import game.client.players.CharacterPart;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Dictionary;

import operator.SpriteSheet;

public class Recoloring {
	
	public static SpriteSheet recolorCharacter(BufferedImage img, Dictionary<CharacterPart, Color> color){
        BufferedImage image = new BufferedImage(img.getWidth(),img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        SpriteSheet sheet = new SpriteSheet();
        
        
        int width = img.getWidth();
        int height = img.getHeight();
        for (int y = 0; y < height; y++){
          for (int x = 0; x < width; x++){
        	  int col = img.getRGB(x, y);
        	  
        	  if((col & 0xFFFFFF) == 0xFFDD00){ // head
        		  if(color.get(CharacterPart.HEAD)!=null)
        			  image.setRGB(x, y, color.get(CharacterPart.HEAD).getRGB());
        		  else
    		          image.setRGB(x, y, img.getRGB(x,y));
        	  } else if((col & 0xFFFFFF) == 0xFFFFFF){ // skin
        		  if(color.get(CharacterPart.SKIN)!=null)
        			  image.setRGB(x, y, color.get(CharacterPart.SKIN).getRGB());
        		  else
    		          image.setRGB(x, y, img.getRGB(x,y));
        	  } else if((col & 0xFFFFFF) == 0x2AFF00){ // shirt
        		  if(color.get(CharacterPart.SHIRT)!=null)
        			  image.setRGB(x, y, color.get(CharacterPart.SHIRT).getRGB());
        		  else
    		          image.setRGB(x, y, img.getRGB(x,y));
        	  } else if((col & 0xFFFFFF) == 0xB73B19){ // boots
        		  if(color.get(CharacterPart.BOOTS)!=null)
        			  image.setRGB(x, y, color.get(CharacterPart.BOOTS).getRGB());
        		  else
    		          image.setRGB(x, y, img.getRGB(x,y));
        	  } else {
		          image.setRGB(x, y, img.getRGB(x,y));
        	  }
          }
        }

        sheet.setSpriteSheet(image);
        return sheet;
    }
	public static SpriteSheet recolorCharacterOther(BufferedImage img, Dictionary<String, Integer> color){
        BufferedImage image = new BufferedImage(img.getWidth(),img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        SpriteSheet sheet = new SpriteSheet();
        
        
        int width = img.getWidth();
        int height = img.getHeight();
        for (int y = 0; y < height; y++){
          for (int x = 0; x < width; x++){
        	  int col = img.getRGB(x, y);
        	  
        	  if((col & 0xFFFFFF) == 0xFFDD00){ // head
        		 if(color.get("head")!=null)
        			  image.setRGB(x, y, color.get("head"));
        		  else
    		          image.setRGB(x, y, img.getRGB(x,y));
        	  } else if((col & 0xFFFFFF) == 0xFFFFFF){ // skin
        		  if(color.get("skin")!=null)
        			  image.setRGB(x, y, color.get("skin"));
        		  else
    		          image.setRGB(x, y, img.getRGB(x,y));
        	  } else if((col & 0xFFFFFF) == 0x2AFF00){ // shirt
        		  if(color.get("shirt")!=null)
        			  image.setRGB(x, y, color.get("shirt"));
        		  else
    		          image.setRGB(x, y, img.getRGB(x,y));
        	  } else if((col & 0xFFFFFF) == 0xB73B19){ // boots
        		  if(color.get("boots")!=null)
        			  image.setRGB(x, y, color.get("boots"));
        		  else
    		          image.setRGB(x, y, img.getRGB(x,y));
        	  } else {
		          image.setRGB(x, y, img.getRGB(x,y));
        	  }
          }
        }

        sheet.setSpriteSheet(image);
        return sheet;
    }
}
