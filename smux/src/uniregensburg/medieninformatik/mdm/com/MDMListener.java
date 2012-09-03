package uniregensburg.medieninformatik.mdm.com;

public abstract class MDMListener {

	protected void registerOnDeviceListener(MDMOnDeviceListener listener) {
		MDMController.addOnDeviceListener(listener);
	}
}
