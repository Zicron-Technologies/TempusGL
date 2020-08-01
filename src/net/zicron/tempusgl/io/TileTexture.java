package net.zicron.tempusgl.io;

public class TileTexture {
	public static final float[] STONE_TILE =
	{ 
	  0f, 0f,
	  1f/16f, 0,
	  1f/16f, 1f/16f,
	  0f, 1f/16f
	};
	
	public static final float[] BRICKS =
	{
	  1f/16f, 0f,
	  2f/16f, 0,
	  2f/16f, 1f/16f,
	  1f/16f, 1f/16f
	};
	
	public static final float[] NULL =
	{ 
	  0f, 15f/16f,
	  1f/16f, 15f/16f,
	  1f/16f, 16f/16f,
	  0f, 16f/16f
	};

	
	public static float[] getTextureById(byte id) {
		switch(id) {
			case 0:
			case 2:
				return STONE_TILE;
			case 1:
				return BRICKS;
			default:
				return NULL;
		}
	}
}
