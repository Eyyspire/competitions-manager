package competitions.observer;

import competitions.Competitor;
import competitions.display.observer.BookmakerDisplayer;
import competitions.match.Match;

/**
 * For each match, update the competitors quotations, according to the result and their opponent
 */
public class BookMaker implements CompetitionObserver {
	
	/* strategy pattern */
	private Algorithm algorithm;
	private BookmakerDisplayer d;
	String phrase;
	String name;

	public BookMaker() {
		this.name = "bookmaker";
		algorithm = new QuotDependingAlgorithm(0.1);
		this.d = new BookmakerDisplayer();
		this.phrase = "%s: The winner quotation went from %.2f to %.2f , while the loser quotation went from %.2f to %.2f ";
	};

	public BookMaker(String name) {
		this.name = name;
		algorithm = new QuotDependingAlgorithm(0.1);
		this.d = new BookmakerDisplayer();
		this.phrase = "%s: The winner quotation went from %.2f to %.2f , while the loser quotation went from %.2f to %.2f ";
	};
	
	/**
	 * updates the quotations
	 * @param c1 match winner
	 * @param c2 match loser
	 */
	public void reactToMatch(Match match) {
		double q1 = match.getWinner().getQuotation();
		double q2 = match.getLoser().getQuotation();
		newQuots(match.getWinner(), match.getLoser());
		d.reaction(this.name, q1, q2, match.getWinner().getQuotation(), match.getLoser().getQuotation(), this.phrase);
	}
	
	/**
	 * Computes the new quotations for each Competitor
	 * @param winner the match winner
	 * @param loser the match loser
	 */
	public void newQuots(Competitor winner, Competitor loser) {
		winner.setQuotation(algorithm.compute(winner.getQuotation(), loser.getQuotation()));
		loser.setQuotation(2 * loser.getQuotation() - algorithm.compute(loser.getQuotation(), winner.getQuotation()));
	}
}
