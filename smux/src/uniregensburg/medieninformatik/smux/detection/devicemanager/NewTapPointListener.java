package uniregensburg.medieninformatik.smux.detection.devicemanager;

import uniregensburg.medieninformatik.smux.scenes.DetectionScene;

public class NewTapPointListener {
	
	public NewTapPointListener(DetectionScene scene) {
		scene.addChangeListener(this);
	  }

	  public void newTapPoint(TapPoint tapPoint) {
		  System.out.println(this.getClass().toString() + ": new TapPoint (" + tapPoint.getId() + ") wasremoved: " + tapPoint.wasRemoved());
	  }

}
