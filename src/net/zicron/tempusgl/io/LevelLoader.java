package net.zicron.tempusgl.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader {
	public String filename;
	public int width;
	public int height;
	public byte[] data;
	
	public LevelLoader(String filename) {
		this.filename = filename;
		try {
			load();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void load() throws NumberFormatException, IOException {
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader((new FileInputStream(filename))));
		int y = 0;
		List<Byte> data_l = new ArrayList<>();
		while((line = reader.readLine()) != null) {
			line = line.trim();
			if(line.contains("width=")) {
				width = Integer.parseInt(line.substring(line.lastIndexOf("=")+1));
				continue;
			}
			
			if(line.contains("height=")) {
				height = Integer.parseInt(line.substring(line.lastIndexOf("=")+1));
				data = new byte[width * height];
				continue;
			}
			
			String[] str_data = line.split(",");
			for(int i= 0; i < str_data.length; i++) {
				data_l.add(Byte.parseByte(str_data[i].trim()));
			}
			y++;
		}
		
		for(int i = 0; i < data_l.size(); i++) {
			data[i] = data_l.get(i);
		}
		reader.close();
	}
}
