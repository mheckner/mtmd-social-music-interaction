package uniregensburg.medieninformatik.mdm.device;

public abstract class MDMAbstractDevice {

	private String device_id;
	private String device_ip;
	
	public static final int DISCONNECTED = 0;
	public static final int CONNECTED = 1;
	public static final int CONNECTING = 2;
	
	private int connectionStatus = MDMMobileDevice.DISCONNECTED;

	public MDMAbstractDevice(String device_id, String device_ip) {
		init(device_id, device_ip);
	}

	private void init(String id, String ip) {
		this.device_id = id;
		this.device_ip = ip;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getDevice_ip() {
		return device_ip;
	}

	public void setDevice_ip(String device_ip) {
		this.device_ip = device_ip;
	}
	
	public int getConnectionStatus() {
		return connectionStatus;
	}
	
	public void setConnectionStatus(int connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
	
	public String toString() {
		return device_id + "(via:" + device_ip + ")";
	}
	
}
