package competitions.display.util;

import competitions.*;
import java.util.*;

public class CompetitionUtilDisplay {

	protected Competition competition;

	/**
	 * Creates new instance of CompetitionDisplayer
	 * 
	 * @param competition the competition to display
	 */
	public CompetitionUtilDisplay(Competition competition) {
		this.competition = competition;
	}

	/**
	 * Concatenates all Competitors' names
	 * @param competitors the competitors to display  
	 * @return all Competitors' names
	 */
	public String competitors(List<Competitor> competitors) {
		String result = "";
		for (Competitor c : competitors) {
			result += c.getColoredName() + "\n";
		}
		return result;
	}

	/**
	 * Calculates the position of the next player in the ranking list
	 * 
	 * @param position     position to calculate
	 * @param playerPoints points of the next player
	 */
	public void positionNextPlayer(int[] position, int playerPoints) {
		if (playerPoints == position[2]) {
			position[1]++;
		} else {
			position[0] += position[1] + 1;
			position[1] = 0;
		}
		position[2] = playerPoints;
	}

	/**
	 * Determines the necessity of "s" at the end of "win" when displaying points
	 * 
	 * @param playerPoints points of the player
	 * @return the correct String
	 */
	public String winWithS(int playerPoints) {
		if (playerPoints > 1) {
			return "wins";
		}
		return "win";
	}
}