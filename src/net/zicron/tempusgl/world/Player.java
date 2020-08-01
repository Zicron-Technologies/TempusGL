package net.zicron.tempusgl.world;

import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPolygonMode;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import net.zicron.tempusgl.Tempus;
import net.zicron.tempusgl.gfx.AnimatedTile;
import net.zicron.tempusgl.gfx.Drawer;
import net.zicron.tempusgl.gfx.Entity;
import net.zicron.tempusgl.gfx.Renderer;
import net.zicron.tempusgl.gfx.Screen;
import net.zicron.tempusgl.gfx.text.Font;
import net.zicron.tempusgl.logic.AABB;
import net.zicron.tempusgl.logic.Bullet;
import net.zicron.tempusgl.logic.TileCollider;

public class Player extends Entity{
	
	private final int moveSpeed = 4;
	private int x;
	private int y;
	private int vel;
	
	private AnimatedTile aTile;
	private boolean isStill = true;
	private int counter = 0;
	
	private enum Dir {
		LEFT,
		RIGHT,
		UP,
		DOWN,
		NONE
	};
	
	private Dir dir;
	
	
	public static AABB collider = null;
	
	
	public Player() {
		Renderer.entities.add(this);
		
		x = (Screen.current.width / 2) - 8;
		y = (Screen.current.height / 2) - 8;
		
		collider = new AABB(x, y, 32, 32, (byte)-1);
		aTile = new AnimatedTile(new int[] {64, 65}, 10);
	}

	public void tick() {		
		aTile.tick();
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			isStill = false;
			if(!TileCollider.isColliding(-1)) {
				vel = moveSpeed;
				dir = Dir.LEFT;
				moveHor();
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			isStill = false;
			if(!TileCollider.isColliding(0)) {
				vel = -moveSpeed;
				dir = Dir.RIGHT;
				moveHor();
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			isStill = false;
			if(!TileCollider.isColliding(1)) {
				vel = moveSpeed;
				dir = Dir.UP;
				moveVer();
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			isStill = false;
			if(!TileCollider.isColliding(2)) {
				vel = -moveSpeed;
				dir = Dir.DOWN;
				moveVer();
			}
		}
		
		while(Mouse.next()) {
			if(Mouse.getEventButtonState()) {
				if(Mouse.getEventButton() == 0) {
					int mouseX = Mouse.getX();
					int mouseY = (-Mouse.getY() + Screen.current.height);
					
					int rise = ((mouseY) - (y+8));
					int run = ((mouseX) - (x+8));
					
					new Bullet(x+8, y+8, rise, run);
				}
			}
		}
		
		while(Keyboard.next()) {
			if(Keyboard.getEventKey() == Keyboard.KEY_F) {
				if(Keyboard.getEventKeyState())
					Tempus.DRAW_HITBOX = !Tempus.DRAW_HITBOX;
			}else if(Keyboard.getEventKey() == Keyboard.KEY_R) {
				if(Keyboard.getEventKeyState())
					glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
			}else if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
				if(Keyboard.getEventKeyState())
					System.exit(0);
			}
			
			isStill = false;
		}
		
		if(counter % 20 == 0) {
			isStill = true;
			counter = 0;
		}
		counter++;
		vel = 0;
	}
	
	private void moveHor() {
		Level.xOffset = Level.xOffset + vel;
	}
	
	private void moveVer() {
		Level.yOffset = Level.yOffset + vel;
	}
	

	public void render() {
		Font.draw(0, 0, "Tempus_GL - Hunter Wilcox", 0xFFFFFF, 2, false, Tempus.fontTextures);
		Font.draw(0, 8, "FPS: " + Renderer.getFPS(), 0xFFFFFF, 2, false, Tempus.fontTextures);
		
		Font.draw(x - 32, y - 16, "JudgeGlass", 0xFFFFFF, 1, true, Tempus.fontTextures);
		
		if(dir != Dir.UP) {
			Drawer.drawTexturedQuad(x, y, Tempus.entityTextures, 0, 2);
			Drawer.drawTexturedQuad(x+16, y, Tempus.entityTextures, 1, 2);
			
		}else {
			Drawer.drawTexturedQuad(x, y, Tempus.entityTextures, 2, 2);
			Drawer.drawTexturedQuad(x+16, y, Tempus.entityTextures, 3, 2);
			
		}
		
		if(isStill) {
			Drawer.drawTexturedQuad(x, y+16, Tempus.entityTextures, 32, 2);
			Drawer.drawTexturedQuad(x+16, y+16, Tempus.entityTextures, 33, 2);
		}else {
			if(aTile.getSprite() == 64) {
				Drawer.drawTexturedQuad(x, y+16, Tempus.entityTextures, 64, 2);
				Drawer.drawTexturedQuad(x+16, y+16, Tempus.entityTextures, 67, 2);
			}else {
				Drawer.drawTexturedQuad(x, y+16, Tempus.entityTextures, 66, 2);
				Drawer.drawTexturedQuad(x+16, y+16, Tempus.entityTextures, 65, 2);
			}
		}
		
		glColor3f(0f, 1.0f, 1.0f);
		glBegin(GL_LINES);
			glVertex2f(x+8, y+8);
			glVertex2f(Mouse.getX(), (-Mouse.getY() + Screen.current.height));
		glEnd();
		
		if(Tempus.DRAW_HITBOX) {
			collider.render();
		}
	}

}
