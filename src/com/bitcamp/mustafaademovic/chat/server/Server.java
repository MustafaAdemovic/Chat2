package com.bitcamp.mustafaademovic.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.bitcamp.mustafaademovic.chat.GUI.GuiChat;

public class Server {

	public static final int port = 1717;

	public static void serverStart() throws IOException {
		ServerSocket server = new ServerSocket(port);

		while (true) {
			String str = "Waiting for conection";
			System.out.println(str);
			Socket client = server.accept();
			GuiChat gc = new GuiChat(client);
			new Thread(gc).start();
					

		}
	}
	public static void main(String[] args) {
		try {
			serverStart();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
