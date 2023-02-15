package competitions.rules;

import java.util.ArrayList;
import java.util.List;

import competitions.Competitor;
import exceptions.WrongAmountOfPlayersException;

/**
 * defines a new type of groupmaker
 * This type takes in his constructor an integer that will defines the number of competitors in each group.
 * The class will have to define itself the number of groups that this will produce.
 */
public class NumberOfCompetitors implements GroupMaker {

	/** the number of players in each group*/
    private int number;

    /**
     * ceeate a new numberOfCompetitors instance given a number that represernts the number of competitors in each group
     * @param number the number of competitors in each group
     */
    public NumberOfCompetitors(int number){
        this.number = number;
    }
    
    /**
     * Create groups of x competitors given a certain list of competitors, x being the number attribute
     * The rule is by default flexible, meaning the method will adapt if the congruencies are not perfect, changing the groups a little bit.
     * It can become strict, applying the strict decorator. When strict, if a congruency is not perfet, it throws an error
     * @param competitors the competitors list to arrange in the different groups
     * @param groups the list where the different groups will be fulfilled
     */
    public void makeGroups(List<Competitor> competitors, List<List<Competitor>> groups){
    	if(competitors.size() < number) {
        	throw new WrongAmountOfPlayersException("Wrong amount of players for NumberOfCompetitors maker !");
        }
    	int j = 0;
        for(int i = 0; i<competitors.size(); i=j) {
        	if (competitors.size() - i >= number) {
        		List<Competitor> newList = new ArrayList<>();
        		for(j = i; j < number + i; j++) {
        			newList.add(competitors.get(j));
        		}
        		groups.add(newList);
        	} else {
        		for(j = i; j < competitors.size(); j++) {
        			groups.get(j%groups.size()).add(competitors.get(j));
        		}
        	}
        }
    }
    
    /**
     * returns the number of players in each group
     * @return the number player in each group
     */
    public int getValue() {
    	return this.number;
    }
}

