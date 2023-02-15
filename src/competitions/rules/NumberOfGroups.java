package competitions.rules;

import java.util.ArrayList;
import java.util.List;

import competitions.Competitor;
import exceptions.WrongAmountOfPlayersException;

public class NumberOfGroups implements GroupMaker{

	/** the number of groups */
    private int number;

    /**
     * create a new numberOfgroups instance, given a number representing the number of groups
     * @param number the number of groups
     */
    public NumberOfGroups(int number){
        this.number = number;
    }

    /**
     * Create x groups of competitors given a certain list of competitors, x being the number attribute
     * The rule is by default flexible, meaning the method will adapt if the congruencies are not perfect, changing the groups a little bit.
     * It can become strict, applying the strict decorator. When strict, if a congruency is not perfet, it throws an error
     * @param competitors the competitors list to arrange in the different groups
     * @param groups the list where the different groups will be fulfilled
     */
    public void makeGroups(List<Competitor> competitors, List<List<Competitor>> groups) throws WrongAmountOfPlayersException{
        if(competitors.size() < number) {
        	throw new WrongAmountOfPlayersException("Wrong amount of players for NumberOfGroups maker !");
        }
    	for(int i = 0; i<number; i++) {
        	groups.add(new ArrayList<>());
        }
        for (int i = 0; i < competitors.size(); i++) {
        	groups.get(i%number).add(competitors.get(i));
        }
    }
    
    /**
     * returns the number of groups
     * @return the number of groups
     */
    public int getValue() {
    	return this.number;
    }
}
