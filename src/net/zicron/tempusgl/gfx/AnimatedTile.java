package net.zicron.tempusgl.gfx;

public class AnimatedTile {
	
	private int[] sprites;
	private int index;
	private int delay;
	private int counter;
	
	public AnimatedTile(int[] sprites, int delay) {
		this.sprites = sprites;
		this.delay = delay;
	}
	
	public void tick() {
		if(counter % delay == 0) {
			if(index + 1 < sprites.length) {
				index++;
			}else {
				index = 0;
			}
			counter = 0;
		}
		counter++;
	}
	
	public int getSprite() {
		return sprites[index];
	}
}
