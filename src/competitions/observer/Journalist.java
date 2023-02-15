package competitions.observer;

import java.util.List;
import java.util.Random;

import competitions.display.observer.JournalistDisplayer;
import competitions.match.Match;

/**
 * It will observes matches in a given competition.
 * It will give the match winners and losers.
 *
 */
public class Journalist implements CompetitionObserver{
	
	/**
	 * For each reaction, the journalist will pick up
	 * a random item among all the catchPhrase models.
	 */
	private List<String> catchPhrases;
	
	/*
	 * displayer
	 */
	private JournalistDisplayer displayer;

	public Journalist(List<String> catchPhrases) {
		this.catchPhrases = catchPhrases;
		this.displayer = new JournalistDisplayer();
	};
	
	/**
	 * React to a match, and tells the winner and the loser of the match
	 * @param the match to comment
	 */
	public void reactToMatch(Match match) {
		displayer.reaction(match.getWinner(), match.getLoser(), catchPhrases.get(new Random().nextInt(catchPhrases.size())));;
	}
	
		
}
