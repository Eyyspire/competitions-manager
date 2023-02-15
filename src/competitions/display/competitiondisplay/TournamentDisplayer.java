package competitions.display.competitiondisplay;

import java.util.Set;

import competitions.Competitor;
import competitions.Tournament;

/**
 * displayer used by tournaments
 */
public class TournamentDisplayer extends CompetitionDisplay {

	/**
	 * Creates an instance of TournamentDisplayer
	 * 
	 * @param tournament the tournament to display
	 */
	public TournamentDisplayer(Tournament tournament) {
		super(tournament);

	}

	/**
	 * Displays the competitors remaining, and how many turns have been played
	 */
	public void displayCompetitors() {
		int currentTurn = ((Tournament) competition).getCurrentTurn();
		String string;
		if (currentTurn == 0) {
			string = "\nList of players in this Tournament";
		} else {
			string = "\nRemaining competitors for turn : " + (((Tournament) competition).getCurrentTurn() + 1);
		}
		display.println(string);
		display.println("-".repeat(string.length()));
		display.println(util.competitors(((Tournament) competition).getRemaining()));
		display.println("-".repeat(string.length()));
	}

	/**
	 * Displays the current turn
	 */
	public void displayMatches() {
		String string = "\nMatches of turn "+ (((Tournament) competition).getCurrentTurn() + 1);
		displayMatches(string);
	}

	/**
	 * Displays the Competitors' positions at the end of the Competition
	 * 
	 * @param rank the Competitors' positions
	 */
	public void displayRanking(Set<Competitor> rank) {
		displayRanking(rank, false);
	}
}