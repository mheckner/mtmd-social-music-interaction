package uniregensburg.medieninformatik.mdm.com;

import java.util.ArrayList;

import uniregensburg.medieninformatik.mdm.device.MDMAbstractDevice;

public class MDMController {
	
	private static ArrayList<MDMOnDeviceListener> onDeviceListeners = new ArrayList<MDMOnDeviceListener>();

	public static void addOnDeviceListener(MDMOnDeviceListener listener) {
		onDeviceListeners.add(listener);
	}
	
	public static void sendNewDeviceConnectedBroadcast(MDMAbstractDevice newDevice) {
		for(MDMOnDeviceListener listener: onDeviceListeners) {
			listener.onNewDeviceConnected(newDevice);
		}
	}
	
	public static void sendDeviceDisconnectedBroadcast(MDMAbstractDevice newDevice) {
		for(MDMOnDeviceListener listener: onDeviceListeners) {
			listener.onDeviceDisconnected(newDevice);
		}
	}

}
