package competitions.display.matchdisplay;

import competitions.Competitor;
import competitions.display.ConsoleDisplay;
import competitions.display.Display;
import competitions.display.util.Colors;
import competitions.display.util.MatchUtilDisplay;
import competitions.match.Match;

/**
 * standard model for match displayers. Different types of match will extend their own match displayer
 */
public abstract class MatchDisplay {

	private static Display display = new ConsoleDisplay();
	protected Match match;

	private MatchUtilDisplay util;

	/**
	 * Creates new instance of MatchDisplay
	 * 
	 * @param match the match to display
	 */
	public MatchDisplay(Match match) {
		this.match = match;
		this.util = new MatchUtilDisplay(match);
	}

	/**
	 * Displays the Match played between two Competitors
	 */
	public void displayMatch() {
		Competitor comp1 = match.getCompetitors().get(0);
		Competitor comp2 = match.getCompetitors().get(1);
		String[] competitors = { comp1.getName(), comp2.getName() };
		util.normalizeStrings(competitors);
		display.printf("%s vs %s      -->  %s wins !\n", Colors.colorize(competitors[0], comp1.getColor()),
				Colors.colorize(competitors[1], comp2.getColor()), match.getWinner().getColoredName());
		display.println("---");
	}
}
