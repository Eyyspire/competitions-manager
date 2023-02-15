package competitions.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import competitions.Competitor;
import competitions.League;
import competitions.display.rules.TieBreakDisplayer;
import exceptions.WrongAmountOfPlayersException;

/**
 * The tie breaker class will decide equal players looking for the same qualification place.
 * It will call a league with the players not decided and select the first number(number of qualified) players.
 * However, this league could also end in a draw, in this case it will call a new League with the few people still not decided,
 * until the players are decided, or, that we have reached the final tie break. In this case, it will be chosen randomly.
 */
public class TieBreaker {
	
	/** the list of competitors*/
	protected List<Competitor> competitors;
	/** the number of people to qualify */
	protected int number;
	/** the tiebreaker displayer */
	protected TieBreakDisplayer displayer;
	/** the ranking of the players */
	protected List<Competitor> ranking;
	/** the number of remaining tiebreaks attempts to do. the last one will decide the winners if the players are still not decided */
	protected int remainings;
	/** if the tiebreaker has to display the matches*/
	private Boolean display;
	
	/**
	 * creates a new tie break with display
	 * @param competitors the competitors of the group
	 * @param number the number of qualified players for this group
	 * @param remainings the number of remaining tie-breaks
	 */
	public TieBreaker(List<Competitor> competitors, int number, int remainings) {
		this.display = true;
		create(competitors, number, remainings);
	}
	
	/**
	 * creates a new tie break
	 * @param competitors the competitors of the group
	 * @param number the number of qualified players for this group
	 * @param remainings the number of remaining tie-breaks
	 * @param display tells if the tie break will display
	 */
	public TieBreaker(List<Competitor> competitors, int number, int remainings, Boolean display) {
		this.display = display;
		create(competitors, number, remainings);
	}
	
	/**
	 * init the current and basics attributes of the tie breaker
	 * @param competitors the competitors of the group
	 * @param number the number of poeple to qualify
	 * @param remainings the number of remainings tie break to do
	 */
	public void create(List<Competitor> competitors, int number, int remainings) {
		this.competitors = new ArrayList<>(competitors);
		this.ranking = new ArrayList<>(competitors);
		this.number = number;
		this.displayer = new TieBreakDisplayer(this);
		this.remainings = remainings;
	}
	
	/**
	 * Start a Tie-Break
	 * @return all the qualified players for this poll
	 * @throws WrongAmountOfPlayersException if the number of players is lower than the people to qualify
	 */
	public List<Competitor> startTieBreak(){
		if (competitors.size() < number) {
			throw new WrongAmountOfPlayersException("Not enough players in the tie-break");
		}
		if (competitors.size() == number) {
			return ranking;
		}
		List<Competitor> res;
		if (display) displayer.welcome();
		res = separateEqualities();
		if (display) displayer.conclusion(res);
		return res;
	}
	
	/**
	 * Get the selected players : launch a league and take the number firsts players
	 * If people are still not decided (they have equal points in this tie break, and looking for the same
	 * qualification place), call separateEqualities
	 * @return the qualified competitors
	 */
	public List<Competitor> selectPlayers() {
		if (remainings == 0) {
			return finalTieBreak();
		}
		League league = new League(competitors);
		league.play();
		Map<Competitor, Integer>leagueRanking = league.ranking();
		ranking = Arrays.asList(leagueRanking.keySet().toArray(new Competitor[0]));
		System.out.println(ranking.size());
		System.out.println(number);
		if ( leagueRanking.get(ranking.get(number-1)) != leagueRanking.get(ranking.get(number))) {
			return ranking.subList(0, number);
		}
		else {
			if (display) displayer.equality();
			return separateEqualities();
		}
	}
	
	/**
	 * qualify the first players, and make a tie break for the one running for the same qualification places (this method might be used
	 * when the firsts tie-break also ended in a tie)
	 * @return the firsts qualified, concatenated to the call of a new TieBreak, with people not decided for a qualification place
	 */
	public List<Competitor >separateEqualities() {
		int points = ranking.get(number-1).getPoints();
		int index;
		List<Competitor> equalities = new ArrayList<>(ranking);
		equalities = filter(equalities, points);
		index = 0;
		while(ranking.get(index).getPoints() != points) {
			index ++;
		}
		if (equalities.size() == number-index) {
			return ranking.subList(0, number);
		}
		return Stream.concat(ranking.subList(0, index).stream(), new TieBreaker(equalities, number-index, remainings-1).selectPlayers().stream())
			 	.collect(Collectors.toList());
	}
	
	/**
	 * If all the previous tie break ended in a draw, the last tie break will decide randomly and select the winners (called when remaining 
	 * is equal to 1, meaning it's the last tie break).
	 * This method will remove one player by one chosen randomly of the competitors list, until we have the 
	 * good number of qualified players.
	 * @return the tie-break winners
	 */
	public List<Competitor> finalTieBreak() {
		displayer.posts();
		int compteur = competitors.size();
		Competitor fall;
		while(compteur > number) {
			int random = (int)((Math.random()*compteur));
			fall = competitors.get(random);
			displayer.fall(fall);
			competitors.remove(random);
			compteur --;
		}
		return competitors;
	}
	
	/**
	 * filter a list of competitors, the condition is that the players have points equal to pointReference
	 * @param competitors the list to filter
	 * @param pointReference the number of points to have
	 */
	private List<Competitor> filter(List<Competitor> toFilter, int pointReference) {
		return toFilter.stream().filter(competitor -> competitor.getPoints() == pointReference)
				.collect(Collectors.toList());
	}
	
	/**
	 * returns the number of competitors
	 * @return the number of competitors
	 */
	public List<Competitor> getCompetitors(){
		return this.competitors;
	}
	
	/**
	 * returns the number of qualified players
	 * @return the number of qualified players
	 */
	public int getNumber() {
		return this.number;
	}
}
