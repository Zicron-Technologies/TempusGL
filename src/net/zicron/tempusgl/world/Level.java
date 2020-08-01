package net.zicron.tempusgl.world;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import net.zicron.tempusgl.Tempus;
import net.zicron.tempusgl.gfx.Drawer;
import net.zicron.tempusgl.gfx.Entity;
import net.zicron.tempusgl.gfx.Renderer;
import net.zicron.tempusgl.gfx.Screen;
import net.zicron.tempusgl.io.LevelLoader;
import net.zicron.tempusgl.io.Log;
import net.zicron.tempusgl.logic.AABB;
import net.zicron.tempusgl.logic.TileCollider;

public class Level extends Entity{
	
	TileCollider tileCollider = null;
	LevelLoader level1 = null;
	
	public static int xOffset;
	public static int yOffset;
	
	public Level() {
		Renderer.entities.add(this);		
		tileCollider = new TileCollider();
		level1 = new LevelLoader("res/level1.txt");
		new Enemy(100, 100);
		
		xOffset-=40;
		addColliders();
	}
	
	private void addColliders() {
		for(int x = 0; x < level1.width; x++) {
			for(int y = 0; y < level1.height; y++) {
				byte data = level1.data[x + y * level1.width];
				if(data != 2) {
					tileCollider.colliders.add(new AABB(x * 32, y * 32, 32, 32, data));
				}
			}
		}
	}
	
	public static boolean isOutOfBounds(int xx, int yy) {
		return !((xx < Screen.current.width) && (xx > -32) && (yy < Screen.current.height) && (yy > -32));
	}
	

	public void tick() {
		//AABB collider = TileCollider.colliders.get(0);
		//collider.x = xOffset;
		tileCollider.updateAABB();
	}

	public void render() {
		//Log.info("Drawing");
		glEnable(GL_TEXTURE_2D);
		for(int x = 0; x < level1.width; x++) {
			for(int y = 0; y < level1.height; y++) {
				byte data = level1.data[x + y * level1.width];
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
				
				int xx = (x * 32) + xOffset;
				int yy = y * 32 + yOffset;
				
				if(!isOutOfBounds(xx, yy)) {
					Drawer.drawTexturedQuad(x, y, xOffset, yOffset, Tempus.gameTextures, data);
				}
				
			}
		}
		glDisable(GL_TEXTURE_2D);
		tileCollider.render();
	}

}
