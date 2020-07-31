package net.zicron.tempusgl;

import net.zicron.tempusgl.gfx.Renderer;
import net.zicron.tempusgl.gfx.Screen;
import net.zicron.tempusgl.io.Texture;
import net.zicron.tempusgl.world.Level;
import net.zicron.tempusgl.world.Player;

public class Tempus {
	
	public static boolean DRAW_HITBOX = false;
	
	public static Texture gameTextures;
	public static Texture fontTextures;
	
	public static void main(String args[]) {
		Screen gameScreen = new Screen(800, 480);
		gameScreen.init();
		
		Renderer gameRenderer = new Renderer();
		gameTextures = new Texture("res/atlas.png");
		fontTextures = new Texture("res/fontAtlas.png");
		fontTextures.pw = 8;
		fontTextures.ph = 8;
		
		Level level = new Level();
		Player player = new Player();
		
		gameRenderer.startLoop();
	}
}
