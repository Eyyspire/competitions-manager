package competitions.rules;

import java.util.List;
import java.util.Map;

import competitions.Competitor;
import exceptions.WrongAmountOfPlayersException;

/**
 * defines an interface for the selections. The selection will have to select the players at the end of the group phases of a master,
 * according to a selection algorithm 
 */
public interface Selection {
	
	/**
	 * select the players in the pools
	 * @param rankings the rankings of the pools
	 * @param finalists the lists in which the selected players will be sent
	 * @throws WrongAmountOfPlayersException if pools sizes and selected players does not match
	 */
	public void selectPlayers(List<Map<Competitor, Integer>> rankings, List<Competitor> finalists) throws WrongAmountOfPlayersException;
	
	/**
	 * returns the number of players at the end of the selection
	 * @return
	 */
	public int getValue();
}
