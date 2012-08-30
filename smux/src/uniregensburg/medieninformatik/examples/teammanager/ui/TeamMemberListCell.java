package uniregensburg.medieninformatik.examples.teammanager.ui;

import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.widgets.MTListCell;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;

import processing.core.PApplet;
import uniregensburg.medieninformatik.examples.teammanager.model.TeamMember;

public class TeamMemberListCell extends MTListCell {

	private TeamMember teamMember;
	private PApplet applet;

	public TeamMemberListCell(float width, float height, PApplet applet,
			TeamMember teamMember) {
		super(width, height, applet);
		this.teamMember = teamMember;
		this.applet = applet;
		init();
	}

	private void init() {
		MTTextArea name = new MTTextArea(applet);
		name.setText(teamMember.getName());
		addChild(name);
		name.setPositionRelativeToParent(this.getPosition(TransformSpace.GLOBAL));
	}

}
