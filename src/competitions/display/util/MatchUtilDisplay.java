package competitions.display.util;

import competitions.match.*;

public class MatchUtilDisplay {

	protected Match match;

	public static final int NAME_LENGTH_NORM = 16;

	/**
	 * Creates an instance of MatchUtilDisplay
	 * 
	 * @param match the match to display
	 */
	public MatchUtilDisplay(Match match) {
		this.match = match;
	}

	/**
	 * Normalizes strings based on the max name length
	 * 
	 * @param competitors the strings to normalize
	 */
	public void normalizeStrings(String[] competitors) {
		String spaceString = " ".repeat(NAME_LENGTH_NORM);
		for (int i = 0; i < competitors.length; i += 2) {
			competitors[i] += spaceString.substring(0, NAME_LENGTH_NORM - competitors[i].length() + 1);
			competitors[i + 1] = spaceString.substring(0, NAME_LENGTH_NORM - competitors[i + 1].length() + 1)
					+ competitors[i + 1];
		}
	}

}
