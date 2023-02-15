package competitions.rules;

import java.util.*;
import competitions.*;

/**
 * A rule is defined by a specific groupMaker and a Selection
 */
public class Rule{

	/** the groupmaker of the rule */
	private GroupMaker groupMaker;
	/** the selecter of the rule*/
	private Selection selection;

	/**
	 * create a new rule given a groupMaker and a selecter
	 * @param poolMaker poolMaker used in the rule
	 * @param selection selecter used in the rule
	 */
	public Rule(GroupMaker groupMaker, Selection selection){
		this.groupMaker = groupMaker;
		this.selection = selection;
	}

	/**
	 * calls the selection method of the selecter
	 * @param rankings the rankings of the pools
	 * @param finalists the finalists in which the qualified will be put
	 */
	public void selectPlayers(List<Map<Competitor, Integer>> rankings, List<Competitor> finalists) {
		selection.selectPlayers(rankings, finalists); 
	}

	/**
	 * calls the makegroups method of the groupMaker
	 * @param competitors the list of the competitors
	 * @param groups the list in which the groups will be created
	 */
	public void makeGroups(List<Competitor> competitors, List<List<Competitor>> groups) {
		groupMaker.makeGroups(competitors, groups); 
	}
}
