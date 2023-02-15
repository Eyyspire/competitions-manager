package competitions.display.competitiondisplay;

import java.util.Set;

import competitions.Competitor;
import competitions.Master;

/**
 * displayer used by masters
 */
public class MasterDisplayer extends CompetitionDisplay {

	/**
	 * Creates an instance of MasterDisplay
	 * 
	 * @param master the master to display
	 */
	public MasterDisplayer(Master master) {
		super(master);
	}

	/**
	 * Displays the Competitors in the Master
	 */
	public void displayCompetitors() {
		displayCompetitors("Master");
	}

	/**
	 * Introduce the current group
	 */
	public void displayMatches() {
		displayMatches("** Presentation of group "+ ((Master) competition).getCurrentGroup() +" **");
	}

	/**
	 * Displays the Competitors' positions at the end of the Master
	 * 
	 * @param rank the Competitors' positions
	 */
	public void displayRanking(Set<Competitor> rank) {
		displayRanking(rank, false);
	}
}
