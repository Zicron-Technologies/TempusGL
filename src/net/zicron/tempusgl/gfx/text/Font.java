package net.zicron.tempusgl.gfx.text;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.*;

import net.zicron.tempusgl.io.Texture;

public class Font {
	private static String chars = "" + //
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ      " + //
            "0123456789.,!?'\"-+=/\\%()<>:;_     " + //
            "";
	public static void draw(int x, int y, String message, int color, int scale, boolean background, Texture spriteSheet){
        if(spriteSheet == null) return;
        message = message.toUpperCase();
        for(int i = 0; i < message.length(); i++){
            int charIndex = chars.indexOf(message.charAt(i));
            if(charIndex >= 0) Font.drawFont(x + i * spriteSheet.pw, y, spriteSheet, charIndex, color, scale, background);
        }
    }
	
	private static void drawFont(int x, int y, Texture t, int index, int color, int scale, boolean background) {
		t.bind();
		
		int r = (color & 0xFF0000) >> 16;
	    int g = (color & 0xFF00) >> 8;
	    int b = (color & 0xFF);
	    
	    glColor3f(r/255, g/255, b/255);
		
		if(!background) {
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		}
			
		
		int xx = index % 32;
		int yy = index / 32;
		
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
			glTexCoord2f((xx / 32f), (yy / 32f));
			glVertex2f(x * scale, y * scale);
			glTexCoord2f((xx + 1) / 32f, yy / 32f);
			glVertex2f((x+8) * scale, y * scale);
			glTexCoord2f((xx+1) / 32f, (yy+1) / 32f);
			glVertex2f((x + 8) * scale, (y + 8) * scale);
			glTexCoord2f(xx / 32f, (yy+1) / 32f);
			glVertex2f(x * scale, (y + 8) * scale);
		glEnd();
		glDisable(GL_TEXTURE_2D);
		
		if(!background) {
			glDisable(GL_BLEND);
		}
	}
}