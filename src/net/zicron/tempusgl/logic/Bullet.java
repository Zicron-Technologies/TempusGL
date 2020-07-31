package net.zicron.tempusgl.logic;

import net.zicron.tempusgl.gfx.Entity;
import net.zicron.tempusgl.gfx.Renderer;
import net.zicron.tempusgl.io.Log;

import static org.lwjgl.opengl.GL11.*;

public class Bullet extends Entity{

	int rise;
	int run;
	int x;
	int y;
	
	int counter = 0;
	
	private AABB collider;
	
	public Bullet(int x, int y, int rise, int run) {
		this.x = x;
		this.y = y;
		this.rise = rise;
		this.run = run;
		
		collider = new AABB(x, y, 8, 8, (byte)-2);
		
		Renderer.addToEntityQueue(this);
	}
	
	public void tick() {
		if(counter % 2 == 0) {
			x += run/10;
			y += rise/10;
		}
		
		collider.setX(x);
		collider.setY(y);
		
		if(TileCollider.isColliding(collider)) {
			Renderer.destroy(this);
		}
		
		counter++;
	}

	public void render() {
		glColor3f(1.0f, 0.5f, 1.0f);
		glBegin(GL_QUADS);
			glVertex2f(x, y);
			glVertex2f(x + 8, y);
			glVertex2f(x + 8, y + 8);
			glVertex2f(x, y + 8);
		glEnd();
		
	}

}
