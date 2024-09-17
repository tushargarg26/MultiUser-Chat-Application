package com.tushar.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
// Thread == Worker
	//Worker need a job to perform
	// For job you give runnable
	//once job is created via Runnable so write the job logic inside a run function
	//Assign the job to a Thread/worker
	//public class ServerWorker implements Runnable {
public class ServerWorker extends Thread{
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
	
	public ServerWorker(Socket clientSocket, Server server) throws IOException{
		this.server=server;
		this.clientSocket = clientSocket;
		in = clientSocket.getInputStream(); //Client DATA READ
		out = clientSocket.getOutputStream(); //Client DATA WRITE
		System.out.println("New Client Comes");
	}
	@Override
	public void run() {
		//Job to perform- Read Data from the Client and Broadcast the data to ALL
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		try {
			while(true) {
			
				line=br.readLine();  //  \n
				System.out.println("Line Read..."+ line);
				if(line.equalsIgnoreCase("quit")){
					break;  //Client chat end for that particular client
				}
				//out.write(line.getBytes()); //Client send
				for(ServerWorker serverWorker : server.workers) {
					line= line + "\n";
					serverWorker.out.write(line.getBytes());
					
					
				}
							
			} 
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally {
			try {
			if(br!=null) {
				br.close();
			}
			if(in!=null) {
				in.close();
			}
			if(out!=null) {
				out.close();
			}
			if(clientSocket!=null) {
				clientSocket.close();
			}
			}
			catch(Exception ex){
				ex.printStackTrace();
			}//end of catch
		}//end of finally
		
	} //end of run
}//end of class
	