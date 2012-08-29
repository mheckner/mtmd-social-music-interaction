package uniregensburg.medieninformatik.smux.scenes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mt4j.AbstractMTApplication;
import org.mt4j.components.visibleComponents.shapes.MTEllipse;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;
import processing.core.PApplet;
import uniregensburg.medieninformatik.smux.detection.TapDownListener;
import uniregensburg.medieninformatik.smux.detection.devicemanager.Device;
import uniregensburg.medieninformatik.smux.detection.devicemanager.DeviceManager;
import uniregensburg.medieninformatik.smux.detection.devicemanager.NewTapPointListener;
import uniregensburg.medieninformatik.smux.detection.devicemanager.TapPoint;


public class DetectionScene extends AbstractScene {

	private List<NewTapPointListener> listener = new ArrayList<NewTapPointListener>();
	private AbstractMTApplication app = null;
	private DeviceManager deviceManager;

	public DetectionScene(AbstractMTApplication mtApplication, String name) {
		super(mtApplication, name);
		this.app = mtApplication;
		initUI();
		initTapProcessor();
		initDeviceManager();
	}

	private void initUI() {
		this.setClearColor(new MTColor(55, 55, 55, 255));
		getCanvas().setDepthBufferDisabled(true);
	}

	private void initTapProcessor() {
		getCanvas().removeAllGestureEventListeners();
		getCanvas().registerInputProcessor(new TapProcessor(app));
		getCanvas().addGestureListener(TapProcessor.class,
				new TapDownListener(this));
	}
	
	private void initDeviceManager() {
		deviceManager = new DeviceManager(this);
	}
	
	public void newTapPoint(TapPoint tapPoint) {
		notifyListeners(tapPoint);
	}
	
	public void removeTapPoint(TapPoint tapPoint) {
		notifyListeners(tapPoint);
	}

	private void notifyListeners(TapPoint tapPoint) {
		for (Iterator<NewTapPointListener> iterator = listener.iterator(); iterator.hasNext();) {
			NewTapPointListener name = (NewTapPointListener) iterator
					.next();
			name.newTapPoint(tapPoint);
		}
	}

	public void addChangeListener(NewTapPointListener newListener) {
		listener.add(newListener);
	}
	
	public void addSmartphoneMarker(Device device) {
	}
	
	public void removeSmartphoneMarker(Device device) {
		getCanvas().removeChild(getCanvas().getChildByName(device.getPhoneId()));
	}

}
