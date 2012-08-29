package uniregensburg.medieninformatik.smux.detection.touchdownserver;


import java.io.*;
import java.net.*;

import uniregensburg.medieninformatik.smux.detection.devicemanager.Device;
import uniregensburg.medieninformatik.smux.detection.devicemanager.DeviceManager;


public class TouchDownServer {

    private static DataOutputStream outToClient;
    private static BufferedReader inFromClient;
    private static ServerSocket welcomeSocket;
    private int serverPort = 0;
    
    private DeviceManager deviceManager = null;
    
    public TouchDownServer(DeviceManager deviceManager, int serverPort) {
    	this.deviceManager = deviceManager;
    	this.serverPort = serverPort;
    }

    public void listen() throws Exception {

        welcomeSocket = new ServerSocket(serverPort);
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            getClientMessage(inFromClient.readLine());
        }
    }

    private void getClientMessage(String msg) throws IOException {
    	String[] msg_parts = msg.split("###");
    	Device device = new Device(msg_parts[0], msg_parts[1], msg_parts[2], Long.parseLong(msg_parts[3]));
    	
    	if(msg_parts[4].equals("TOUCHEDGROUND")) {
    		deviceManager.newDeviceTouchedGround(device);
    	} else if(msg_parts[4].equals("WASREMOVED")) {
    		deviceManager.deviceWasRemoved(device);
    	}
    	
        outToClient.writeBytes("42\n");
    }
}