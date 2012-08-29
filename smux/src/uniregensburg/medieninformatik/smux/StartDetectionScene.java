package uniregensburg.medieninformatik.smux;

import org.mt4j.MTApplication;

import uniregensburg.medieninformatik.smux.scenes.DetectionScene;



public class StartDetectionScene extends MTApplication {
	private static final long serialVersionUID = 1L;

	DetectionScene detectionScene;
	
	
	public static void main(String[] args) {
		initialize();
	}
	
	@Override
	public void startUp() {
		final MTApplication app = this;
		detectionScene = new DetectionScene(app, "DetectionScene");
		changeScene(detectionScene);
	}


}