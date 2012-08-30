package uniregensburg.medieninformatik.examples.teammanager.model;

import java.util.ArrayList;

public class TeamManager {

	private static TeamManager instance = null;

	private ArrayList<TeamMember> teamMembers;

	private TeamManager() {
		init();
	}

	public static TeamManager getInstance() {
		if (instance != null) {
			return instance;
		} else {
			instance = new TeamManager();
			return instance;
		}
	}

	private void init() {
		teamMembers = new ArrayList<TeamMember>();
		addTeamMember(new TeamMember("Alexander Bazo",
				"alexander.bazo@stud.uni-regensburg.de"));
		addTeamMember(new TeamMember("Markus Heckner",
				"markus.heckner@sprachlit.uni-regensburg.de"));
	}

	public boolean addTeamMember(TeamMember newTeamMember) {
		if (isMember(newTeamMember)) {
			return false;
		} else {
			teamMembers.add(newTeamMember);
			return true;
		}
	}

	public void removeTeamMember(TeamMember teamMember) {
		if (isMember(teamMember)) {
			teamMembers.remove(getIndexForTeamMember(teamMember));
		}
	}

	public ArrayList<TeamMember> getTeamMembers() {
		return teamMembers;
	}

	public boolean isMember(TeamMember possibleTeamMember) {
		for (TeamMember member : teamMembers) {
			if (member.getEmail().equals(possibleTeamMember.getEmail())) {
				return true;
			}
		}
		return false;
	}

	private int getIndexForTeamMember(TeamMember teamMember) {
		for (int i = 0; i < teamMembers.size(); i++) {
			TeamMember tmpMember = teamMembers.get(i);
			if (tmpMember.getEmail().equals(teamMember.getEmail())) {
				return i;
			}
		}
		return -1;
	}
}
