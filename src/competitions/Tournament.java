package competitions;

import java.util.List;
import java.util.stream.Collectors;

import competitions.display.competitiondisplay.TournamentDisplayer;
import competitions.match.factory.BalancedMatchFactory;
import competitions.match.factory.MatchFactory;
import exceptions.WrongAmountOfPlayersException;

/**
 * Creates a kind of competition with number of players corresponding to a power of 2.
 * At each turn, the competitors will play another person, and the winner will be qualfiied for the following turn.
 * The winner is the one winning the last turn.
 *
 */
public class Tournament extends Competition {

	/** the remaining competitors running for the win at the current state of the tournament */
	protected List<Competitor> remaining;
	/** the number of turns that will occur in the tournament */
	protected int total_turns;
	/** the current turn in the tournament, starting at 0 */
	protected int current_turn;

	/**
	 * Creates an instance of Tournament, with given competitors and a balancedMatch Factory by default
	 * 
	 * @param competitors list of competitors in the Tournament
	 */
	public Tournament(List<Competitor> competitors) {
		super(competitors);
		create(competitors);
		this.matchFactory = new BalancedMatchFactory();
	}
	
	/**
	 * Creates an instance of Tournament, with given competitors and matchFactory
	 * 
	 * @param competitors list of competitors in the Tournament
	 * @param matchFactory the factory creating the matches
	 */
	public Tournament(List<Competitor> competitors, MatchFactory matchFactory) {
		super(competitors);
		create(competitors);
		this.matchFactory = matchFactory;
	}
	
	/**
	 * init the basic and common attributes of a tournament
	 * @param competitors the competitors running in the tournament
	 */
	public void create(List<Competitor> competitors) {
		this.remaining = competitors;
		this.total_turns = (int) (Math.log(competitors.size()) / Math.log(2));
		this.current_turn = 0;
		this.displayer = new TournamentDisplayer(this);
	}

	/**
	 * Plays the entire Tournament
	 * 
	 * @param competitors list of competitors in the Tournament
	 * @exception WrongAmountOfPlayersException if the amount of competitors is not
	 *                                          allowed by the Competition
	 */
	protected void play(List<Competitor> competitors) throws WrongAmountOfPlayersException {
		if (Math.log(competitors.size()) / Math.log(2) % 1 != 0 || competitors.size() < 2) {
			throw new WrongAmountOfPlayersException("Wrong amount of competitors for this Competition.");
		}
		this.init(competitors);
		while (this.current_turn < this.total_turns) {
			this.displayCompetitors();
			this.playTurn();
		}
	}

	/**
	 * Play a turn of the Tournament
	 */
	public void playTurn() {
		displayMatches();
		for (int c = 0; c < remaining.size(); c += 2) {
			playMatch(remaining.get(c), remaining.get(c + 1));
		}
		this.current_turn++;
		this.filter();
	}

	/**
	 * Initialize a new competition by reseting parameters
	 * 
	 * @param competitors list of competitors in the Competition
	 */
	public void init(List<Competitor> competitors) {
		this.remaining = competitors;
		this.current_turn = 0;
		super.init(remaining);
	}

	/**
	 * Filters the Competitors based on their number of wins and the current turn
	 */
	protected void filter() {
		this.remaining = this.remaining.stream().filter(competitor -> competitor.getPoints() == this.current_turn)
				.collect(Collectors.toList());
	}

	/**
	 * Gets the remaining Competitors
	 * 
	 * @return the remaining Competitors
	 */
	public List<Competitor> getRemaining() {
		return this.remaining;
	}

	/**
	 * Gets the total number of turns
	 * 
	 * @return the total number of turns
	 */
	public int getTotalTurns() {
		return this.total_turns;
	}

	/**
	 * Gets the current turn
	 * 
	 * @return the current turn
	 */
	public int getCurrentTurn() {
		return this.current_turn;
	}
	
}