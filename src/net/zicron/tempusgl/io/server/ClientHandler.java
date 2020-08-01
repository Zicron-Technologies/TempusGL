package net.zicron.tempusgl.io.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler {
	private Socket client;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	public ClientHandler(Socket client) {
		this.client = client;
		
		try {
			setStream();
			listenLoop();
			close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void close() throws IOException {
		output.close();
		input.close();
		client.close();
	}
	
	private void setStream() throws IOException {
		input = new ObjectInputStream(client.getInputStream());
		output = new ObjectOutputStream(client.getOutputStream());
	}
	
	private void sendData(Object c) throws IOException {
		output.writeObject(c);
		output.flush();
	}
	
	private void listenLoop() throws ClassNotFoundException, IOException {
		while(client.isConnected()) {
			Object c = input.readObject();
		}
	}
}
