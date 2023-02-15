package competitions.display.competitiondisplay;

import java.util.Set;

import competitions.Competition;
import competitions.Competitor;
import competitions.display.ConsoleDisplay;
import competitions.display.Display;
import competitions.display.util.CompetitionUtilDisplay;

/**
 * defines the display standard class for a competition
 */
public abstract class CompetitionDisplay{

	protected CompetitionUtilDisplay util;
	protected Competition competition;

	protected static Display display = new ConsoleDisplay();

	/**
	 * Creates an instance of CompetitionDisplay
	 * 
	 * @param competition the competition to display
	 */
	public CompetitionDisplay(Competition competition) {
		this.competition = competition;
		this.util = new CompetitionUtilDisplay(competition);
	}
	
	/**
	 * Displays the Competitors in the Competition
	 */
	public void displayCompetitors(String name) {
		String string = "\nList of players in this "+ name;
		display.println(string);
		display.println("-".repeat(string.length()));
		display.print(util.competitors(competition.getCompetitors()));
		display.println("-".repeat(string.length()));
	}
	
	public abstract void displayCompetitors();

	/**
	 * Displays the current Turn
	 */
	public void displayMatches(String sentence) {
		display.println(sentence);
		display.println("-".repeat(sentence.length()));
	}
	
	public abstract void displayMatches();

	/**
	 * Displays the Competitors' positions at the end of the Competition
	 * 
	 * @param rank the Competitors' positions
	 */
	public void displayRanking(Set<Competitor> rank, Boolean withPoints) {
		String string = "\nRanking";
		display.println(string);
		display.println("-".repeat(string.length()));
		int position = 0;
		int tmp = 0;
		int olderPoints = -1;
		int[] nextPosition = { position, tmp, olderPoints };
		for (Competitor c : rank) {
			util.positionNextPlayer(nextPosition, c.getPoints());
			if(withPoints) {
				String win = util.winWithS(c.getPoints());
				display.printf("%d - %s (%d %s)\n", nextPosition[0], c.getColoredName(), c.getPoints(), win);
			} else {
				display.printf("%d - %s\n", nextPosition[0], c.getColoredName());
			}
			
		}
		display.println("-".repeat(string.length()));
	}
	
	public abstract void displayRanking(Set<Competitor> rank);
}
