package net.zicron.tempusgl.logic;

import java.util.ArrayList;
import java.util.List;

import net.zicron.tempusgl.Tempus;
import net.zicron.tempusgl.io.Log;
import net.zicron.tempusgl.world.Level;
import net.zicron.tempusgl.world.Player;

public class TileCollider {
	public static List<AABB> colliders = new ArrayList<>();
	private static AABB t = null;
	
	public static boolean checkCollision() {
		for(AABB collider: TileCollider.colliders) {
			if(AABB.checkCollision(collider, Player.collider)) {
				Log.info("Player Hit");
				return true;
			}
		}
		return false;
	}
	
	public static boolean isColliding(int dir) {
		AABB playerCol = Player.collider;
		
		if(dir == 0)
			t = new AABB(playerCol.x, playerCol.y, 20, 16, (byte)-1);
		else if(dir == -1)
			t = new AABB(playerCol.x-4, playerCol.y, 16, 16, (byte)-1);
		else if(dir == 1)
			t = new AABB(playerCol.x, playerCol.y-4, 16, 16, (byte)-1);
		else if(dir == 2)
			t = new AABB(playerCol.x, playerCol.y, 16, 20, (byte)-1);
		
		for(AABB collider: colliders) {
			if(AABB.checkCollision(collider, t)) {
				t = null;
				return  true;
			}
		}
		
		return false;
	}
	
	public static boolean isColliding(AABB b1) {
		for(AABB collider: colliders) {
			if(AABB.checkCollision(collider, b1)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void updateAABB() {
		for(AABB collider: TileCollider.colliders) {
			collider.x = Level.xOffset + (collider.ox);
			collider.y = Level.yOffset + (collider.oy);
		}
	}
	
	public void render() {
		if(Tempus.DRAW_HITBOX) {
			if(t != null) {
				t.render();
			}
			for(AABB collider: TileCollider.colliders) {
				collider.render();
			}
		}
	}
}
