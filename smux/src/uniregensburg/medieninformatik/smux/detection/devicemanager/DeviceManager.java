package uniregensburg.medieninformatik.smux.detection.devicemanager;

import java.util.ArrayList;

import uniregensburg.medieninformatik.smux.detection.touchdownserver.TouchDownServer;
import uniregensburg.medieninformatik.smux.scenes.DetectionScene;


public class DeviceManager extends NewTapPointListener{
	
	public int MAX_HISTORY_SIZE = 100;
	private ArrayList<TapPoint> tapPointHistory;
	private ArrayList<Device> devices;
	
	private DetectionScene scene = null;
	
	private TouchDownServer touchDownServer;

	public DeviceManager(DetectionScene scene) {
		super(scene);
		this.scene = scene;
		init();
	}
	
	private void init() {
		tapPointHistory = new ArrayList<TapPoint>();
		devices = new ArrayList<Device>();
		touchDownServer = new TouchDownServer(this, 6789);
		startTouchDownServer();
	}
	
	@Override
	public void newTapPoint(TapPoint tapPoint) {
		if(tapPointHistory.size() < MAX_HISTORY_SIZE) {
			tapPointHistory.add(tapPoint);
		} else {
			tapPointHistory.remove(0);
			tapPointHistory.add(tapPoint);
		}
		super.newTapPoint(tapPoint);
	}
	
	private void startTouchDownServer() {
		(new ServerThread(touchDownServer)).start();
	}
	
	public void newDeviceTouchedGround(Device device) {
		for(Device know_device: devices) {
			if(know_device.getPhoneId().equals(device.getPhoneId())) return;
		}
		
		System.out.println("WOW. Seems as if we really detetected a: " + device);
		devices.add(device);
		
//		for(int i=0; i < tapPointHistory.size(); i++) {
//			TapPoint tapPoint = tapPointHistory.get(i);
//			int tapPoint_timestamp = Math.round(tapPoint.getTimestamp()/10000);
//			int device_timestamp = Math.round(device.getTimestamp()/10000);
//			if(tapPoint_timestamp == device_timestamp) {
//				System.out.println("WOW. Seems as if we really detetected a: " + device);
//				device.setTapPoint(tapPoint);
//				devices.add(device);
//				scene.addSmartphoneMarker(device);
//				tapPointHistory.remove(i);
//				return;
//			}
//		}
	}
	
	public void deviceWasRemoved(Device device) {
		boolean knowit = false;
		for(Device know_device: devices) {
			if(know_device.getPhoneId().equals(device.getPhoneId())) {
				knowit = true;
			}
		}
		if(!knowit) return;
		
		for(int i=0; i < devices.size(); i++) {
			Device know_device = devices.get(i);
			if(know_device.getPhoneId().equals(device.getPhoneId())) {
				System.out.println("WOW. Seems as if we really recognized that this device is gone: " + device);
				devices.remove(i);
//				scene.removeSmartphoneMarker(know_device);
				return;
			}
		}
	}

	class ServerThread extends Thread {
		private TouchDownServer touchDownServer;
		
		public ServerThread(TouchDownServer touchDownServer) {
			this.touchDownServer = touchDownServer;
		}
		
		public void run() {
			try {
				touchDownServer.listen();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
