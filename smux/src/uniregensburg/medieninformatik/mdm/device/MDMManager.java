package uniregensburg.medieninformatik.mdm.device;

import java.util.ArrayList;

import uniregensburg.medieninformatik.mdm.com.MDMCommunicationServer;
import uniregensburg.medieninformatik.mdm.com.MDMListener;
import uniregensburg.medieninformatik.mdm.com.MDMOnDeviceListener;
import uniregensburg.medieninformatik.mdm.device.MDMAbstractDevice;

public class MDMManager extends MDMListener implements MDMOnDeviceListener {

	private ArrayList<MDMAbstractDevice> devices;
	
	private static MDMManager instance = null;
	
	private MDMCommunicationServer com = null;

	// TODO put in abstract class
	private MDMManager() {
		System.out.println("MDMManager created");
		init();
	}

	// TODO put in abstract class
	public static MDMManager getInstance() {
		System.out.println("getting instance of MDMManager");
		if (instance == null) {
			instance = new MDMManager();
		}
		return instance;
	}

	// TODO put in abstract class
	private void init() {
		com = MDMCommunicationServer.getInstance();
		com.startServer();
		devices = new ArrayList<MDMAbstractDevice>();
		registerOnDeviceListener(this);
	}


	public ArrayList<MDMAbstractDevice> getConnectedDevices() {
		ArrayList<MDMAbstractDevice> connected_device = new ArrayList<MDMAbstractDevice>();
		for (MDMAbstractDevice device : devices) {
			if (device.getConnectionStatus() == MDMAbstractDevice.CONNECTED) {
				connected_device.add(device);
			}
		}
		return connected_device;
	}

	@Override
	public void onNewDeviceConnected(MDMAbstractDevice device) {
		System.out.println(this.toString() + " - " + "NEW DEVICE CONNECTED: " + device);
		devices.add(device);
	}

	@Override
	public void onDeviceDisconnected(MDMAbstractDevice device) {
		// TODO Auto-generated method stub
		
	}

}
