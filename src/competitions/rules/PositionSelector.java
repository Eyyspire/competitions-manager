package competitions.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import competitions.Competitor;
import exceptions.WrongAmountOfPlayersException;
import util.MapUtil;

/**
 * a specific type of selector.
 * It will select players according to a table representing the number of players to qualify for each place. it will be used as a map, meaning that
 * the number x at index i will means the x players at position (i+1) will be qualified.
 * For example the table [4,2,0,1] means : 
 * 4 players at first place are qualified
 * 2 at second place
 * 0 at third place
 * 1 at the last place
 */
public class PositionSelector implements Selection{
	
	/**
	 *  lists all the qualified players example (1,5) in the Map means 5 first players among all the first players are qualified
	 */
	private int[] tab;
	private List<List<Competitor>> lists;
	List<List<Competitor>> players_according_to_position;
	
	/**
	 * create a new Position given a table representing the number of players to qualify for each place
	 * @param a table representing the number of players to qualify for each place. it will be used as a map, meaning that
	 * the number x at index i will means the x players at position (i+1) will be qualified
	 */
	public PositionSelector(int[] tab) {
		this.tab = tab;
		lists = new ArrayList<>();
		players_according_to_position = new ArrayList<>();
	}
	
	/**
	 * select players in the different pools according to all the ranking of pools and the table of qualified players
	 * this method will handle the possible exceptions, create the rankings as lists and call the method selecting
	 * @param rankings the different rankings
	 * @param finalist the list in which the qualified players will be set
	 * @throws WrongAmountOfPlayersException if we have less players than selected people, or selected players for a given index
	 * negative, or an index bigger than the number of ranks
	 */
	public void selectPlayers(List<Map<Competitor,Integer>> rankings, List<Competitor> finalists) throws WrongAmountOfPlayersException {
		if (countPlayers(rankings) < getValue()) {
			throw new WrongAmountOfPlayersException("Wrong amount of players in the groups for this PositionSelector !");
		}
		for(int value : tab) {
			if (value > rankings.size() || value < 0) {
				throw new WrongAmountOfPlayersException("Wrong amount of groups for this PositionSelector !");
			}
		}
		// create lists of ranking
		for(Map<Competitor, Integer> ranking: rankings){
			lists.add(Arrays.asList(ranking.keySet().toArray(new Competitor[0])));
		}
		handlePositions(finalists);
	}
	
	/**
	 * the method that is concretely selecting the players, according to the table representing the number of players to qualify for each place. it will be used as a map, meaning that
	 * the number x at index i will mean the x players at position (i+1) will be qualified
	 * for each position, the method will select the corresponding player in each group (if players are aiming a same place with
	 * equal points, start a tie-break for this place). Then, it creates rankings for people at the same ranks. And finally,
	 * it selects the firsts players thanks to a rank selector.
	 * For example, if we select three firsts players in each group and we have 4 groups. The method creates a rank for each
	 * first players and then call a rank selector(3) among theses firsts players. The three first better players will be qualified.
	 * And then, we go to the next index.
	 * @param finalists the lists in which the qualified better will be sent
	 */
	public void handlePositions(List<Competitor> finalists) {
			int i;	
			for(i = 0; i<tab.length; i++) {
				if (tab[i] == 0) continue;
				List <Competitor> current_pos = new ArrayList<>();
				for(List<Competitor> list : lists) {
					if (list.size() >= i+1) {
						if (list.size() == i+1 || list.get(i+1).getPoints() != list.get(i).getPoints()) {
							current_pos.add(list.get(i));
						}
						else {
							Competitor point = list.get(i);
							int index = i;
							while(index<list.size()) {
								if (list.get(index).getPoints() == point.getPoints()) index++;
								else break;
							}
								
							Competitor select = new TieBreaker(list.subList(i, index), 1, 10).startTieBreak().get(0);
							int indice = list.indexOf(select);
							Collections.swap(list, indice, i);
							current_pos.add(select);
						}
					} else {
						throw new WrongAmountOfPlayersException("Group too small for this PositionSelector !");
					}
				}
				
				Map<Competitor, Integer> tmp_rank = new HashMap<>();
				current_pos.forEach(competitor -> tmp_rank.put(competitor, competitor.getPoints()));
				Map<Competitor, Integer> rank = MapUtil.sortByDescendingValue(tmp_rank);
				
				new RankSelector(tab[i]).select(rank, finalists);
			}
	}
	/**
	 * Counts the amount of Competitors in a list of rankings
	 * @param rankings the list in which it counts
	 * @return the number of Competitors
	 */
	public int countPlayers(List<Map<Competitor, Integer>> rankings) {
		int tmp = 0;
		for(Map<Competitor, Integer> ranking : rankings) {
			tmp+=ranking.size();
		}
		return tmp;
	}
	
	/**
	 * returns the total number of qualified players
	 * @return the total number of qualified players
	 */
	public int getValue() {
		int res = 0;
		for(int i = 0; i<tab.length; i++) {
			res += tab[i];
		}
		return res;
	}
	
	/**
	 * returns the table in which is there the number of Competitors to qualify
	 * @return the table in which is there the number of Competitors to qualify
	 */
	public int[] getTab() {
		return this.tab;
	}

}
