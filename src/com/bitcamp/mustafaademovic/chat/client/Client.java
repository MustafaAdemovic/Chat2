package com.bitcamp.mustafaademovic.chat.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


public class Client {
	public static final int port = 1717;
	public static final String host = "localhost";

	public static void main(String[] args){
		
		try {
			Socket client = new Socket(host, port);
			OutputStream os = client.getOutputStream();
			os.write("Hello World\n".getBytes());
			os.flush();
			while (true) {
				// Petlja da bi ostvarili stalnu konekciju
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
