package uniregensburg.medieninformatik.smux.detection.devicemanager;

public class Device {
	
	private String manufacturer = "";
	private String product = "";
	private String phoneId = "";
	private long timestamp;
	
	private TapPoint tapPoint = null;
	
	public Device(String manufacture, String product, String phoneId, long timestamp) {
		this.manufacturer = manufacture;
		this.product = product;
		this.phoneId = phoneId;
		this.timestamp = timestamp;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public String getProduct() {
		return product;
	}

	public String getPhoneId() {
		return phoneId;
	}
	
	public long getTimestamp() {
		return timestamp;
	}

	public TapPoint getTapPoint() {
		return tapPoint;
	}

	public void setTapPoint(TapPoint tapPoint) {
		this.tapPoint = tapPoint;
	}

	@Override
	public String toString() {
		return "Device [manufacturer=" + manufacturer + ", product=" + product
				+ ", phoneId=" + phoneId + ", timestamp=" + timestamp + "]";
	}

}
