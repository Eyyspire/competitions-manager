package competitions;

import java.util.*;

import competitions.display.competitiondisplay.LeagueDisplayer;
import competitions.match.factory.MatchFactory;
import exceptions.WrongAmountOfPlayersException;

/**
 * A kind of competition making each competitor playing each other twice
 * The winner is the player with the most points
 */
public class League extends Competition {

	/**
	 * Creates a new League competition, with given competitors, and by default balanced matches
	 * 
	 * @param competitors is the list of competitors participating in the League
	 *                    competition
	 */
	public League(List<Competitor> competitors) {
		super(competitors);
		this.displayer = new LeagueDisplayer(this);
	}
	
	/**
	 * Creates a new League competition, with given competitors, and a match factory
	 * 
	 * @param competitors is the list of competitors participating in the League
	 *                    competition
	 * @param matchFactory the factory that will create the matches
	 */
	public League(List<Competitor> competitors, MatchFactory matchFactory) {
		super(competitors, matchFactory);
		this.displayer = new LeagueDisplayer(this);
	}

	/**
	 * Plays a league competition. Each player will play twice against each
	 * competitor
	 * 
	 * @param competitors the list of players willing to play the league
	 *                    competition.
	 * @exception WrongAmountOfPlayersException if the amount of competitors is not
	 *                                          allowed by the Competition
	 */
	protected void play(List<Competitor> competitors) throws WrongAmountOfPlayersException {
		this.displayCompetitors();
		if (competitors.size() < 2) {
			throw new WrongAmountOfPlayersException("Wrong amount of players for this League.");
		}
		super.init(competitors);
		this.displayMatches();
		competitors.forEach(c1 -> {
			competitors.forEach(c2 -> {
				if (c1 != c2) {
					playMatch(c1, c2);
				}
			});
		});
	}
}