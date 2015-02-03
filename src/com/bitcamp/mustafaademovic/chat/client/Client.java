package com.bitcamp.mustafaademovic.chat.client;

import java.io.IOException;
import java.net.Socket;

import com.bitcamp.mustafaademovic.chat.GUI.ChatGui;

public class Client {
	public static final int port = 1717;
	public static final String host = "localhost";

	public static void main(String[] args) {

		try {

			Socket client = new Socket(host, port);
			ChatGui gui = new ChatGui(client);
			new Thread(gui).start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}