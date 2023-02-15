package competitions.rules;

import java.util.List;

import competitions.Competitor;
import exceptions.WrongAmountOfPlayersException;

/**
 * defines the methods used by the different groupmakers : the classes that will make the groups for a master compeitition
 */
public interface GroupMaker {

	/**
	 * make the group of the master competition
	 * @param competitors the competitors to send in different groups
	 * @param pools the list of pools in which the competitors will be sent
	 * @throws WrongAmountOfPlayersException if the group numbers and competitors does not match
	 */
	public void makeGroups(List<Competitor> competitors, List<List<Competitor>> pools) throws WrongAmountOfPlayersException;
	
	/**
	 * returns the number of groups
	 * @return the number of group
	 */
	public int getValue();

}
