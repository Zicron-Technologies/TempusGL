package net.zicron.tempusgl.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

import de.matthiasmann.twl.utils.PNGDecoder;

import static org.lwjgl.opengl.GL11.*;

public class Texture {
	private String file;
	public int pw;
	public int ph;
	public int width;
	public int height;
	public int id;
	
	private ByteBuffer buf;
	
	public Texture(String filename) {
		FileInputStream file = null;
		PNGDecoder decoder = null;
		try {
			file = new FileInputStream(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			decoder = new PNGDecoder(file);
			

			width = decoder.getWidth();
			height = decoder.getHeight();
			
			buf = BufferUtils.createByteBuffer(4 * width * height);
			decoder.decode(buf, width * 4, PNGDecoder.Format.RGBA);
			
			buf.flip();
			
			glEnable(GL_TEXTURE_2D);
			id = glGenTextures();
			glBindTexture(GL_TEXTURE_2D, id);
			glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);
			buf.clear();
			buf = null;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}

}
