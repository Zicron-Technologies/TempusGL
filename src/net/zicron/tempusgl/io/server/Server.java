package net.zicron.tempusgl.io.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import net.zicron.tempusgl.Tempus;
import net.zicron.tempusgl.io.Log;

public class Server {
	private int port;
	private ServerSocket server;
	
	public Server(int port) {
		this.port = port;
	}
	
	public void start() {
		Log.info("Starting Server on Port: " + port);
		try {
			server = new ServerSocket(port);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while(Tempus.isRunning) {
			try {				
				Socket client = server.accept();
				new Thread(() -> new ClientHandler(client)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
