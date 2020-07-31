package net.zicron.tempusgl.io;

public class Log {
	public static void info(String msg) {
		System.out.println("[INFO: ]: " + msg);
	}
	
	public static void warn(String msg) {
		System.out.println("[WARN: ]: " + msg);
	}
	
	public static void error(String msg) {
		System.err.println("[ERROR: ]: " + msg);
	}
}
