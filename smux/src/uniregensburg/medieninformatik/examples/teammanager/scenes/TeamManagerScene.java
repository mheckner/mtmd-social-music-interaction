package uniregensburg.medieninformatik.examples.teammanager.scenes;

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


public class TeamManagerScene extends AbstractScene {


	private AbstractMTApplication app = null;

	public TeamManagerScene(AbstractMTApplication mtApplication, String name) {
		super(mtApplication, name);
		this.app = mtApplication;
		initUI();
	}

	private void initUI() {
		this.setClearColor(new MTColor(55, 55, 55, 255));
		getCanvas().setDepthBufferDisabled(true);
	}

}
