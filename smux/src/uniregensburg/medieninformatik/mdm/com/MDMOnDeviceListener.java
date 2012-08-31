package uniregensburg.medieninformatik.mdm.com;

import uniregensburg.medieninformatik.mdm.device.MDMAbstractDevice;

public interface MDMOnDeviceListener {

	public void onNewDeviceConnected(MDMAbstractDevice device);
	public void onDeviceDisconnected(MDMAbstractDevice device);
	
}
