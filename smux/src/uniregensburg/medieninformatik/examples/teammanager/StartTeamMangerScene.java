package uniregensburg.medieninformatik.examples.teammanager;

import org.mt4j.MTApplication;

import uniregensburg.medieninformatik.examples.teammanager.scenes.TeamManagerScene;



public class StartTeamMangerScene extends MTApplication {
	private static final long serialVersionUID = 1L;

	TeamManagerScene teamManagerScene;
	
	
	public static void main(String[] args) {
		initialize();
	}
	
	@Override
	public void startUp() {
		final MTApplication app = this;
		teamManagerScene = new TeamManagerScene(app, "TeamManagerScene");
		changeScene(teamManagerScene);
	}


}