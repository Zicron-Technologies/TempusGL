package net.zicron.tempusgl.world;

import net.zicron.tempusgl.Tempus;
import net.zicron.tempusgl.gfx.Entity;
import net.zicron.tempusgl.gfx.Renderer;
import net.zicron.tempusgl.gfx.Screen;
import net.zicron.tempusgl.io.Log;
import net.zicron.tempusgl.logic.AABB;
import net.zicron.tempusgl.logic.TileCollider;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;

public class Player extends Entity{
	
	private final int moveSpeed = 2;
	private int x;
	private int y;
	private int vel;
	
	private enum Dir {
		LEFT,
		RIGHT,
		UP,
		DOWN,
		NONE
	};
	
	private Dir resDir;
	private Dir currentDir;
	
	
	public static AABB collider = null;
	
	
	public Player() {
		Renderer.entities.add(this);
		
		x = (Screen.current.width / 2) - 8;
		y = (Screen.current.height / 2) - 8;
		
		collider = new AABB(x, y, 16, 16);
	}

	public void tick() {		
		int xx = ((x / 32) - Level.xOffset);
		int yy = ((y / 32) + Level.yOffset);
		
		//Log.info("Player POS: X:" + xx + "\tY: " + yy + "\tXOFF: " + Level.xOffset);
		
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			if(!TileCollider.isColliding(-1)) {
				vel = moveSpeed;
				moveHor();
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			if(!TileCollider.isColliding(0)) {
				vel = -moveSpeed;
				moveHor();
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			if(!TileCollider.isColliding(1)) {
				vel = moveSpeed;
				moveVer();
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			if(!TileCollider.isColliding(2)) {
				vel = -moveSpeed;
				moveVer();
			}
		}
		
		
		vel = 0;
	}
	
	private void moveHor() {
		Level.xOffset = Level.xOffset + vel;
	}
	
	private void moveVer() {
		Level.yOffset = Level.yOffset + vel;
	}
	

	public void render() {
		glColor3f(0.0f, 0.0f, 0.0f);
		glBegin(GL_QUADS);
			glVertex2f(x, y);
			glVertex2f(x + 16, y);
			glVertex2f(x + 16, y + 16);
			glVertex2f(x, y + 16);
		glEnd();
		
		if(Tempus.DRAW_HITBOX) {
			collider.render();
		}
	}

}
