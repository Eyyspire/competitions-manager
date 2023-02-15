package competitions.observer;

import competitions.match.Match;

/**
 * A interface representing the observers of competitions
 */
public interface CompetitionObserver {

	/** does something as soons as a match is played */
		public void reactToMatch(Match match);
}
