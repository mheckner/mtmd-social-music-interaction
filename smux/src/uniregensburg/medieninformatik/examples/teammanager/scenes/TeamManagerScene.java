package uniregensburg.medieninformatik.examples.teammanager.scenes;

import org.mt4j.AbstractMTApplication;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MTColor;

import uniregensburg.medieninformatik.examples.teammanager.model.TeamManager;
import uniregensburg.medieninformatik.examples.teammanager.model.TeamMember;
import uniregensburg.medieninformatik.examples.teammanager.ui.TeamManagerList;
import uniregensburg.medieninformatik.examples.teammanager.ui.TeamMemberListCell;


public class TeamManagerScene extends AbstractScene {


	private TeamManager teamManager = null;
	private TeamManagerList teamList = null;

	public TeamManagerScene(AbstractMTApplication mtApplication, String name) {
		super(mtApplication, name);
		initModel();
		initUI();
	}
	
	private void initModel() {
		teamManager = TeamManager.getInstance();
	}

	private void initUI() {
		setClearColor(new MTColor(55, 55, 55, 255));
		getCanvas().setDepthBufferDisabled(true);
		initTeamList();
	}
	
	private void initTeamList() {
		teamList = new TeamManagerList(100, 100, 200, 400, 5, getMTApplication());
		teamList.setName("TeamList");
		for(TeamMember teamMember: teamManager.getTeamMembers()) {
			TeamMemberListCell cell = new TeamMemberListCell(180, 100, getMTApplication(), teamMember);
			cell.setName(teamMember.getEmail());
			teamList.addListElement(cell);
		}
		getCanvas().addChild(teamList);
	}

}
