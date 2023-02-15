package competitions.match.factory;

import competitions.Competitor;
import competitions.match.Match;

/**
 * interface used to define match factories.
 */
public interface MatchFactory {

	/**
	 * returns a type of match according to the factory.
	 * @param c1 the first competitor in the match
	 * @param c2 the second competitor in the match
	 * @return a type of match according to the factory.
	 */
	public Match createMatch(Competitor c1, Competitor c2);
}
