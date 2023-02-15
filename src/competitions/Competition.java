package competitions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import competitions.display.competitiondisplay.CompetitionDisplay;
import competitions.match.Match;
import competitions.match.factory.BalancedMatchFactory;
import competitions.match.factory.MatchFactory;
import competitions.observer.CompetitionObserver;
import exceptions.WrongAmountOfPlayersException;
import util.MapUtil;

/**
 * The abstract class defining a competition. The concrete type of competitions will extend this class
 */

public abstract class Competition {

	/** competitors runnin in the competition */
	private final List<Competitor> competitors;
	/** the factory creating the matches*/
	protected MatchFactory matchFactory;
	/** the displayer */
	protected CompetitionDisplay displayer;
	/**the observers*/
	protected List<CompetitionObserver> observers;

	/**
	 * Creates a new Competition, with given competitors, and a balanced factory match by default
	 * 
	 * @param competitors is the list of competitors participating in the
	 *                    competition
	 */
	public Competition(List<Competitor> competitors) {
		this.competitors = competitors;
		create();
		this.matchFactory = new BalancedMatchFactory();
	}
	
	/**
	 * Creates a new Competition, with given competitors, and a given match factory
	 * 
	 * @param competitors is the list of competitors participating in the
	 *                    competition
	 * @param matchFactory the factory that will create the matches
	 */
	public Competition(List<Competitor> competitors, MatchFactory matchFactory) {
		this.competitors = competitors;
		create();
		this.matchFactory = matchFactory;
	}
	
	/**
	 * create basic field for a competition
	 * @param competitors
	 */
	public void create() {
		this.observers = new ArrayList<>();
	}

	/**
	 * Plays the competition until there is a winner
	 * 
	 * @exception WrongAmountOfPlayersException if the amount of competitors is not
	 *                                          allowed by the Competition
	 */
	public void play() throws WrongAmountOfPlayersException {
		this.play(this.competitors);
	}

	/**
	 * Plays the competition. The competition's rules will be defined in the
	 * subclasses
	 * 
	 * @param list the list of players willing to play the competition.
	 * @exception WrongAmountOfPlayersException if the amount of competitors is not
	 *                                          allowed by the Competition
	 */
	protected abstract void play(List<Competitor> list) throws WrongAmountOfPlayersException;

	/**
	 * Plays a match given two Competitors
	 * 
	 * @param c1 Competitor 1
	 * @param c2 Competitor 2
	 */
	protected void playMatch(Competitor c1, Competitor c2) {
		Match match = matchFactory.createMatch(c1, c2);
		match.play();
		Competitor winner = match.getWinner();
		winner.addPoints(1);
		match.displayMatch();
		//Competitor loser = match.getLoser();
		for(CompetitionObserver observer: this.observers) {
			observer.reactToMatch(match);
		}
	}

	/**
	 * Gives the current rank of the competition, with the points of each player
	 * 
	 * @return map with each competitors with their corresponding points
	 */
	public Map<Competitor, Integer> ranking() {
		Map<Competitor, Integer> tmp_rank = new HashMap<>();
		this.competitors.forEach(competitor -> tmp_rank.put(competitor, competitor.getPoints()));
		Map<Competitor, Integer> rank = MapUtil.sortByDescendingValue(tmp_rank);
		this.displayRanking(rank.keySet());
		return rank;
	}

	/**
	 * Returns list of competitors
	 * 
	 * @return competitors
	 */
	public List<Competitor> getCompetitors() {
		return this.competitors;
	}

	/**
	 * Initialize a new competition by reseting parameters
	 * 
	 * @param competitors list of competitors in the Competition
	 */
	public void init(List<Competitor> competitors) {
		for (Competitor competitor : competitors) {
			competitor.setPoints(0);
		}
	}

	/**
	 * Displays competitors' positions at the end of the Competition
	 * 
	 * @param rank the list to display
	 */
	public void displayRanking(Set<Competitor> rank) {
		displayer.displayRanking(rank);
	}

	/**
	 * Displays competitors in the Competition
	 */
	public void displayCompetitors() {
		displayer.displayCompetitors();
	}

	/**
	 * Displays current Turn
	 */
	public void displayMatches() {
		displayer.displayMatches();
	}
	
	/**
	 * register a new observer
	 * @param CompetitionObserver the new observer
	 */
	public void register(CompetitionObserver observer) {
		this.observers.add(observer);
	}
	
	/**
	 * register a new observer
	 * @param CompetitionObserver the new observer
	 */
	public void register(List<CompetitionObserver> observer) {
		this.observers.addAll(observer);
	}
	
	/**
	 * unregister an observer
	 * @param CompetitionObserver the new observer
	 */
	public void unregister(CompetitionObserver observer) {
		this.observers.remove(observer);
	}
	
	
}
