package net.zicron.tempusgl;

import net.zicron.tempusgl.gfx.Renderer;
import net.zicron.tempusgl.gfx.Screen;
import net.zicron.tempusgl.world.Level;
import net.zicron.tempusgl.world.Player;

public class Tempus {
	
	public static final boolean DRAW_HITBOX = true;
	
	
	
	public static void main(String args[]) {
		Screen gameScreen = new Screen(800, 480);
		gameScreen.init();
		
		Renderer gameRenderer = new Renderer();
		
		Level level = new Level();
		Player player = new Player();
		
		gameRenderer.startLoop();
	}
}
