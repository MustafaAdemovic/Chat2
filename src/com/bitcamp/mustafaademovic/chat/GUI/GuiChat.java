package com.bitcamp.mustafaademovic.chat.GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GuiChat implements Runnable{
	private JTextArea display;
	private JTextField inputText;
	private Socket connection;
	private InputStream is;
	private OutputStream os;

	public GuiChat(Socket connection) throws IOException {

		this.connection = connection;
		this.is = connection.getInputStream();
		this.os = connection.getOutputStream();

		display = new JTextArea();
		display.setLineWrap(true);

		inputText = new JTextField();
		this.inputText.setPreferredSize(new Dimension(270, 40));

		JButton buttonSend = new JButton("Send");
		buttonSend.setPreferredSize(new Dimension(100, 40));

		TextHandler bh = new TextHandler();
		buttonSend.addActionListener(bh);
		inputText.addKeyListener(bh);

		JScrollPane sp = new JScrollPane(display);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setPreferredSize(new Dimension(390, 220));

		JPanel panel = new JPanel();
		panel.add(inputText);
		panel.add(buttonSend);
		panel.add(sp);

		JFrame frame = new JFrame("Chat");
		frame.setSize(400, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.setVisible(true);

	}

	public void listenForNetwork() throws IOException {
		
		BufferedReader input = new BufferedReader(new InputStreamReader(is));
		String line = null;

		while ((line = input.readLine()) != null) {
			if (!line.equals("")) {
				display.append("Client: " + line + "\n");
				line = null;
			}
		}

	}

	public class TextHandler implements ActionListener, KeyListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String str = inputText.getText();
			if (!str.equals("")) {

				String text = "Me: " + str;
				try {
					os.write(str.getBytes());
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}
				inputText.setText("");

				String displayText = display.getText();
				displayText += text;
				display.setText(displayText + "\n");

			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyPressed(KeyEvent e) {
			String str = inputText.getText();
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {

				if (!str.equals("")) {

					String text = "Me: " + str;
					try {
						os.write(str.getBytes());
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
					}
					inputText.setText("");

					String displayText = display.getText();
					displayText += text;
					display.setText(displayText + "\n");

				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}

	}
	public void run() {
		try {
			listenForNetwork();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
