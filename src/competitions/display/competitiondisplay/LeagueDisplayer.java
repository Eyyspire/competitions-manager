package competitions.display.competitiondisplay;

import java.util.Set;

import competitions.Competitor;
import competitions.League;

/**
 * displayer used by leagues
 */
public class LeagueDisplayer extends CompetitionDisplay {

	/**
	 * Creates an instance of LeagueDisplayer
	 * 
	 * @param league the league to display
	 */
	public LeagueDisplayer(League league) {
		super(league);
	}

	/**
	 * Displays the Competitors in the Competition
	 */
	public void displayCompetitors() {
		displayCompetitors("League");
	}
	
	/**
	 * Displays the current Turn
	 */
	public void displayMatches() {
		displayMatches("\nHere are all the matchs played");
	}

	/**
	 * Displays the Competitors' positions at the end of the Competition
	 * 
	 * @param rank the Competitors' positions
	 */
	public void displayRanking(Set<Competitor> rank) {
		displayRanking(rank, true);
	}
}
