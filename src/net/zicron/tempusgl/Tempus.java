package net.zicron.tempusgl;

import net.zicron.tempusgl.gfx.Renderer;
import net.zicron.tempusgl.gfx.Screen;
import net.zicron.tempusgl.io.Texture;
import net.zicron.tempusgl.io.server.Server;
import net.zicron.tempusgl.world.Level;
import net.zicron.tempusgl.world.Player;

public class Tempus {
	
	public static boolean DRAW_HITBOX = false;
	public static boolean isRunning = false;
	
	public static Texture gameTextures;
	public static Texture fontTextures;
	public static Texture entityTextures;
	
	public static void main(String args[]) {
		Screen gameScreen = new Screen(1200, 800);
		gameScreen.init();
		
		Renderer gameRenderer = new Renderer();
		
		isRunning = true;
		
		new Thread(() -> new Server(800).start()).start();
		
		gameTextures = new Texture("res/atlas.png");
		entityTextures = new Texture("res/entityAtlas.png");
		entityTextures.pw = 8;
		entityTextures.ph = 8;
		fontTextures = new Texture("res/fontAtlas.png");
		fontTextures.pw = 8;
		fontTextures.ph = 8;
		
		Level level = new Level();
		Player player = new Player();
		
		gameRenderer.startLoop();
	}
}
