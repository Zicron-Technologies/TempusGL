package net.zicron.tempusgl.logic;

import static org.lwjgl.opengl.GL11.*;

public class AABB {
	
	public int x;
	public int y;
	public int ox;
	public int oy;
	public int width;
	public int height;
	
	public AABB(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		ox = x;
		oy = y;
		this.width = width;
		this.height = height;
	}
	
	public static boolean checkCollision(AABB b1, AABB b2) {
		if(b1.getLeft() < b2.getRight() && b1.getRight() > b2.getLeft() && b1.getTop() < b2.getBottom() && b1.getBottom() > b2.getTop()) {
			return true;
		}
		return false;
	}
	
	public int getBottom() {
		return y + height;
	}
	
	public int getTop() {
		return y;
	}
	
	public int getLeft() {
		return x;
	}
	
	public int getRight() {
		return x + width;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void render() {
		glColor3f(0.11f, 0.82f, 1f);
		glLineWidth(1);
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glBegin(GL_POLYGON);
			glVertex2f(x, y);
			glVertex2f(x + width, y);
			glVertex2f(x + width, y + height);
			glVertex2f(x, y + height);
		glEnd();
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
	}
}
