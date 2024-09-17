package com.tushar.chatapp.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.tushar.chatapp.utils.ConfigReader;

public class Server {
	
	ServerSocket serverSocket;
	ArrayList<ServerWorker> workers = new ArrayList<>();  //Contains all the client sockets
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server started and Waiting for the clients to join....");
		handleClientRequest();
	}
	//Multiple Client Handshaking
	public void handleClientRequest() throws IOException {
		while(true) {
			Socket clientSocket = serverSocket.accept();
			//Per Client Per Thread
			ServerWorker serverWorker = new ServerWorker(clientSocket, this); //Creating a new Worker/Thread, this represents instance of server
			workers.add(serverWorker);
			serverWorker.start();
			}
	}
	
	
	//Single Client
	/*
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Started and waiting for the Client Connection....");
		Socket socket = serverSocket.accept(); //Handshaking with the client
		System.out.println("Client Joins the Server");
		InputStream in= socket.getInputStream();  //Read bytes from the network
		byte arr[]= in.readAllBytes();  //.readAllBytes() is reading all bytes at once otherwise we have to read each byte separately
		String str = new String(arr); //bytes converting to string
		System.out.println("Message Received from the Client- " + str);
		in.close();
		
		socket.close();
	}
	*/
	
	public static void main(String[] args) throws IOException {
		//TODO Auto-generatted method stub
		Server server = new Server();
		
	}
}
