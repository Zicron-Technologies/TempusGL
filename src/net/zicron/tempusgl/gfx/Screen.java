package net.zicron.tempusgl.gfx;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class Screen {
	
	public static Screen current = null;
	
	public int width;
	public int height;
	
	public Screen(int sw, int sh) {
		width = sw;
		height = sh;
		Screen.current = this;
	}
	
	public void init() {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		}catch(LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		glViewport(0, 0, width, height);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
	}
}
