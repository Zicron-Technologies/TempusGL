package net.zicron.tempusgl.world;

import net.zicron.tempusgl.Tempus;
import net.zicron.tempusgl.gfx.Entity;
import net.zicron.tempusgl.gfx.Renderer;
import net.zicron.tempusgl.io.Log;
import net.zicron.tempusgl.logic.AABB;
import net.zicron.tempusgl.logic.TileCollider;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;

public class Level extends Entity{
	
	TileCollider tileCollider = null;
	
	private byte[] level1 = {
			1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 
			0, 1, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 2, 2, 0, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 2, 1, 0,
			0, 1, 0, 2, 0, 2, 0, 2, 2, 2, 0, 2, 2, 2, 0, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 1, 0,
			0, 1, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 2, 2, 0, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 1, 0,
			0, 1, 0, 2, 0, 2, 0, 2, 2, 2, 0, 2, 2, 2, 0, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 1, 0,
			0, 1, 0, 2, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 2, 0, 0, 2, 2, 2, 2, 2, 1, 0,
			0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0,
			0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	};
	
	public static int xOffset;
	public static int yOffset;
	
	public Level() {
		Renderer.entities.add(this);
		Log.info("Level Len: " + level1.length);
		
		tileCollider = new TileCollider();
		
		xOffset-=40;
		addColliders();
	}
	
	private void addColliders() {
		for(int x = 0; x < 28; x++) {
			for(int y = 0; y < 12; y++) {
				byte data = level1[x + y * 28];
				if(data != 2) {
					tileCollider.colliders.add(new AABB(x * 32, y * 32, 32, 32, data));
				}
			}
		}
	}
	

	public void tick() {
		//AABB collider = TileCollider.colliders.get(0);
		//collider.x = xOffset;
		tileCollider.updateAABB();
	}

	public void render() {
		//Log.info("Drawing");
		glEnable(GL_TEXTURE_2D);
		for(int x = 0; x < 28; x++) {
			for(int y = 0; y < 12; y++) {
				byte data = level1[x + y * 28];
				switch(data) {
				case 0:
					glColor3f(1.0f, 1.0f, 1.0f);
					break;
				case 1:	
					glColor3f(1.0f, 0f, 0f);
					break;
				case 2:
					glColor3f(0f, 1.0f, 0f);
					break;
				}

				
				
				Tempus.gameTextures.bind();
				
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);
					glVertex2f(x * 32 + xOffset, y * 32 + yOffset);
					glTexCoord2f(1f/16f, 0);
					glVertex2f((x * 32) + 32 + xOffset, y * 32 + yOffset);
					glTexCoord2f(1f/16f, 1f/16f);
					glVertex2f((x * 32) + 32 + xOffset, (y * 32) + 32 + yOffset);
					glTexCoord2f(0, 1f/16f);
					glVertex2f(x * 32 + xOffset, (y * 32) + 32 + yOffset);
				glEnd();
			}
		}
		glDisable(GL_TEXTURE_2D);
		tileCollider.render();
	}

}
