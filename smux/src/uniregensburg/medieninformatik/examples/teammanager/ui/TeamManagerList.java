package uniregensburg.medieninformatik.examples.teammanager.ui;

import org.mt4j.components.visibleComponents.widgets.MTList;
import org.mt4j.util.MTColor;

import processing.core.PApplet;

public class TeamManagerList extends MTList{

	public TeamManagerList(float x, float y, float width, float height,
			float cellPaddingY, PApplet applet) {
		super(x, y, width, height, cellPaddingY, applet);
		initUI();
	}
	
	private void initUI() {
		setFillColor(new MTColor(150,150,150,200));
        setNoFill(true);
        setNoStroke(true);
	}

}
