package uniregensburg.medieninformatik.mdm.com;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import uniregensburg.medieninformatik.mdm.device.MDMAbstractDevice;

public class MDMCommunicationServer {

	public static final int SERVERPORT = 6789;
	private ServerThread serverThread = null;

	private static MDMCommunicationServer instance = null;
	
	private MDMCommunicationServer() {
		
	}
	
	public static MDMCommunicationServer getInstance() {
		if(instance == null) {
			instance = new MDMCommunicationServer();
		}
		return instance;
	}

	public void startServer() {
		System.out.println(this.toString() + " starting communication sever");
		serverThread = new ServerThread();
		serverThread.start();
	}

	public void stopServer() {
		serverThread = null;
	}

	private void processMessage(MDMMessage msg) {
		MDMAbstractDevice device = new MDMAbstractDevice(msg.getSender_id(), msg.getSender_ip()) {
		};
		switch(msg.getMessage_type()) {
		case MDMMessage.MESSAGE_TYPE_CONNECTED:
			MDMController.sendNewDeviceConnectedBroadcast(device);
			break;
		case MDMMessage.MESSAGE_TYPE_DISCONNECTED:
			MDMController.sendDeviceDisconnectedBroadcast(device);
			break;
		default:
			break;
		}
	}

	class ServerThread extends Thread {

		private DataOutputStream outToClient;
		private BufferedReader inFromClient;
		private ServerSocket welcomeSocket;
		private Socket connectionSocket;

		public ServerThread() {
			try {
				welcomeSocket = new ServerSocket(
						MDMCommunicationServer.SERVERPORT);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			while (true) {
				try {
					connectionSocket = welcomeSocket.accept();
					inFromClient = new BufferedReader(new InputStreamReader(
							connectionSocket.getInputStream()));
					String in = inFromClient.readLine();
				
					String[] msg_parts = in.split("###");
					String sender_id = msg_parts[0];
					String sender_ip = msg_parts[1];
					int recepients = Integer.parseInt(msg_parts[2]);
					String receiver_id = msg_parts[3];
					int message_type = Integer.parseInt(msg_parts[4]);
					String message_content = msg_parts[5];
					
					MDMMessage msg = new MDMMessage();
					msg.setMessage_content(message_content);
					msg.setMessage_type(message_type);
					msg.setReceiver_id(receiver_id);
					msg.setRecepients(recepients);
					msg.setSender_id(sender_id);
					msg.setSender_ip(sender_ip);
					
					send("\n");
					
					processMessage(msg);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}
		}

		public void send(String msg) {
			try {
				outToClient = new DataOutputStream(
						connectionSocket.getOutputStream());
				outToClient.writeBytes(msg + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
