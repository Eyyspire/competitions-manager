package competitions.match;

import competitions.*;
import java.util.*;
import competitions.display.matchdisplay.BalancedMatchDisplayer;

/**
 * type of match. Gives equal chances for each competitor
 */
public class BalancedMatch extends Match {

	/**
	 * Creates an instance of BalancedMatch
	*
	 * @param c1 first competitor in the banlancedMatch
	 * @param c2 second competitor in the BalancedMatch
	 */
	public BalancedMatch(Competitor c1, Competitor c2) {
		super(c1, c2);
		displayer = new BalancedMatchDisplayer(this);
	}

	/**
	 * Decides the winner, with equal chances for each competitor
	 * 
	 */
	public void play() {
		List<Competitor> competitors = new ArrayList<>(Arrays.asList(c1, c2));
		this.winner = competitors.get(new Random().nextInt(competitors.size()));
	}
}