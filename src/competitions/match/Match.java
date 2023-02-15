package competitions.match;

import competitions.*;
import competitions.display.matchdisplay.MatchDisplay;

import java.util.*;

/**
 * abstract class defining a match. Each match is composed by two competitors.
 */
public abstract class Match {

	/** the first competitor in the match */
	protected final Competitor c1;
	/** the second competitor in the match */
	protected final Competitor c2;
	/** the winner of the match */
	protected Competitor winner;
	/** the displayer used */
	protected MatchDisplay displayer;

	/**
	 * Creates an instance of Match
	 * 
	 * @param c1 first competitor in the Match
	 * @param c2 second competitor in the Match 
	 */
	public Match(Competitor c1, Competitor c2) {
		this.c1 = c1;
		this.c2 = c2;
		this.winner = null;
	}

	/**
	 * Gets a list of the Competitors
	 * 
	 * @return a list of the Competitors
	 */
	public List<Competitor> getCompetitors() {
		return Arrays.asList(c1, c2);
	}

	/**
	 * gets the winner
	 * 
	 * @return the winner
	 */
	public Competitor getWinner() {
		return this.winner;
	}
	
	/**
	 * gets the loser
	 * 
	 * @return the loser
	 */
	public Competitor getLoser() {
		return (this.winner == this.c1) ? c2 : c1;
	}

	/**
	 * Defines the winner of the Match
	 */
	public abstract void play();

	/**
	 * Display the match
	 */
	public void displayMatch() {
		displayer.displayMatch();
	}

}
