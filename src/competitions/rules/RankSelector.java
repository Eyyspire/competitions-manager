package competitions.rules;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import competitions.Competitor;
import competitions.display.rules.RankSelectorDisplayer;
import exceptions.WrongAmountOfPlayersException;

/**
 * defines a new type of selector. An instance of this selector is defined by a rank (integer). For a given number x, all
 * the players in each pool ranked x or better, will be qualified for the next phase.
 * For example if rank = 2, the first two players of each groups  are qualified
 */
public class RankSelector implements Selection{

	/** the rank needed to be qualified */
    private int rank;
    /** the displayer */
    private RankSelectorDisplayer display;

    /**
     * create a new RankSelector, given a certain rank
     * @param rank the firsts *ranks* players will be selected for the final tournament
     */
    public RankSelector(int rank){
        this.rank = rank;
        this.display = new RankSelectorDisplayer();
    }

    /**
     * select all the qualified players among the different groups. If the first non selected have the same amount of points than the
     * first non selected, start a tie-break to decide the winners
     * @param rankings all the group in which the selecter will decide for each group the qualified players
     * @param finalists the list in which the selecter will add all the qualified players
     */
    public void selectPlayers(List<Map<Competitor, Integer>> rankings, List<Competitor> finalists) throws WrongAmountOfPlayersException{
        for(Map<Competitor, Integer> ranking: rankings){
        	select(ranking, finalists);
        }
    }
    
    /**
     * for a given group of the master, decides the qualified players. If we have a tie for a decisive place, start a tie-break
     * with the players with equal points
     * @param ranking the ranking of the pool
     * @param finalists the lists of finalists
     * @throws WrongAmountOfPlayersException if pool size and selection number does not match
     */
    public void select(Map<Competitor, Integer> ranking, List<Competitor> finalists) throws WrongAmountOfPlayersException{
        	List<Competitor> r = Arrays.asList(ranking.keySet().toArray(new Competitor[0]));
        	if (r.size() < rank) {
    			throw new WrongAmountOfPlayersException("Wrong amount of players in the group.");
    		}
        	if(r.size() > rank) {         
	        	if (r.get(rank-1).getPoints() != r.get(rank).getPoints()) {
	        		r = r.subList(0, rank);
	    		}
	    		else {
	    			r = new TieBreaker(r, rank, 10).startTieBreak();
	    		}
        	}
        	display.conclusion(r);
        	finalists.addAll(r);  
    	}
    
    /**
     * returns the rank value (firsts qualified for each group)
     * @return the rank value (firsts qualified for each group)
     */
    public int getValue() {
    	return rank;
    }
}