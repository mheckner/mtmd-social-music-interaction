package uniregensburg.medieninformatik.smux.detection.devicemanager;

import org.mt4j.input.inputData.InputCursor;

public class TapPoint {
	
	private long timestamp;
	private InputCursor cursor;
	private int id;
	private boolean wasremoved = false;
	
	public TapPoint(long timestamp, InputCursor cursor, int id, boolean wasremoved) {
		this.timestamp = timestamp;
		this.cursor = cursor;
		this.id= id;
		this.wasremoved = wasremoved;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public InputCursor getCursor() {
		return cursor;
	}

	public int getId() {
		return id;
	}
	
	public boolean wasRemoved() {
		return wasremoved;
	}
}
