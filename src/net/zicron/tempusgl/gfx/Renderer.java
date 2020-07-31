package net.zicron.tempusgl.gfx;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import net.zicron.tempusgl.io.Log;

import static org.lwjgl.opengl.GL11.*;


public class Renderer {
private int fps;
	
	public static List<Entity> entities = new ArrayList<>();
	public static List<Entity> entityQueue = new ArrayList<>();
	public static List<Entity> entityRemoveQueue = new ArrayList<>();
	
	public Renderer() {
		
	}
	
	public void startLoop() {
		long lastTime = System.nanoTime();
        double unprocessed = 0;
        double nsPerTick = 1000000000.0 / 60;
        int frames = 0;
        int ticks = 0;
        long lastTimer1 = System.currentTimeMillis();

        while (!Display.isCloseRequested()) {
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = true;
            while (unprocessed >= 1) {
                ticks++;
                tick();
                unprocessed -= 1;
                shouldRender = true;
            }
            
            try {
				Thread.sleep(4);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            if (shouldRender) {
                frames++;
                render();
                Display.update();
            }

            if (System.currentTimeMillis() - lastTimer1 > 1000) {
                lastTimer1 += 1000;
                System.out.println(ticks + " ticks, " + frames + " fps\t\tMem: " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024) / 1024 + " MB");
                //screen.setTitle(" FPS: " + frames);
                fps = frames;
                frames = 0;
                ticks = 0;
            }
        }
        stop();
	}
	
	public static void destroy(Entity e) {
		entityRemoveQueue.add(e);
	}
	
	public static void addToEntityQueue(Entity e) {
		entityQueue.add(e);
	}
	
	private void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		
		for(Entity e: entities) {
			e.render();
		}
		
		if(!entityQueue.isEmpty()) {
			for(Entity e: entityQueue) {
				entities.add(e);
			}
			entityQueue.clear();
		}
		
		if(!entityRemoveQueue.isEmpty()) {
			for(Entity e: entityRemoveQueue) {
				entities.remove(e);
			}
			entityRemoveQueue.clear();
		}
	}
	
	private void tick() {
		for(Entity e: entities) {
			e.tick();
		}
	}
	
	private void stop() {
		Display.destroy();
	}
}
