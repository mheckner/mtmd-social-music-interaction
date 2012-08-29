package uniregensburg.medieninformatik.smux.detection;

import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;

import uniregensburg.medieninformatik.smux.detection.devicemanager.TapPoint;
import uniregensburg.medieninformatik.smux.scenes.DetectionScene;


public class TapDownListener implements IGestureEventListener {
	
	private DetectionScene app;
	
	public TapDownListener(DetectionScene app) {
		super();
		this.app = app;
	}

	@Override
	public boolean processGestureEvent(MTGestureEvent ge) {
		TapEvent te = (TapEvent)ge;
		TapPoint tapPoint;
		switch (te.getId()) {
		case MTGestureEvent.GESTURE_STARTED:
			tapPoint = new TapPoint(te.getTimeStamp(), te.getCursor(), te.getId(), false);
			app.newTapPoint(tapPoint);
			break;
		case MTGestureEvent.GESTURE_UPDATED:
			break;
		case MTGestureEvent.GESTURE_ENDED:
			tapPoint = new TapPoint(te.getTimeStamp(), te.getCursor(), te.getId(), true);
			app.removeTapPoint(tapPoint);
			break;
		}
		return false;
	}

}
