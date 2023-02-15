package competitions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import competitions.display.competitiondisplay.MasterDisplayer;
import competitions.match.factory.BalancedMatchFactory;
import competitions.match.factory.MatchFactory;
import competitions.rules.Rule;
import exceptions.WrongAmountOfPlayersException;
import util.MapUtil;

public class Master extends Competition{
	
	/** the list of the different groups in the master */
	private List<List<Competitor>> groups;
	/** the lists of the ranking of the different pools */
	private List<Map<Competitor, Integer>> rankings;
	/** list of the finalist (participants of the final tournament) */
	private List<Competitor> finalists;
	/** the rule used for the master */
	private Rule rule;
	/** the currentGroup used in the functions */
	private int currentGroup;
	
	/**
	 * Creates a new Master competition, with given competitors, and a given selection rule to make the groups and choose players when group stages are finished.
	 * The matches are by default balanced matches.
	 * @param competitors is the list of competitors participating in the Master
	 *                    competition
	 * @param rule is the rule used to make the groups and choose competitors when group stages are finished
	 */
	public Master(List<Competitor> competitors, Rule rule) {
		super(competitors);
		create(rule);
		this.matchFactory = new BalancedMatchFactory();
		this.finalists = new ArrayList<>();
	}
	
	/**
	 * Creates a new Master competition, with given competitors, and a given selection rule to make the groups and choose players when group stages are finished.
	 * 
	 * 
	 * @param competitors is the list of competitors participating in the Master
	 *                    competition
	 * @param rule is the rule used to make the groups and choose competitors when group stages are finished
	 */
	public Master(List<Competitor> competitors, Rule rule, MatchFactory matchFactory) {
		super(competitors);
		create(rule);
		this.matchFactory = matchFactory;
		this.finalists = new ArrayList<>();
	}
	
	/**
	 * init the basic and common attributes of a tournament
	 * @param is the rule used to make the groups and choose competitors when group stages are finished
	 */
	public void create(Rule rule) {
		this.rule = rule;
		this.displayer = new MasterDisplayer(this);
	}
	
	protected void play(List<Competitor> competitors) throws WrongAmountOfPlayersException {
			this.init(competitors);
			this.displayCompetitors();
			rule.makeGroups(competitors, this.groups);
			//Make pools play
			List<Competition> leagues = new ArrayList<>();
			this.playGroups(leagues);
			for(Competition league : leagues) {
				rankings.add(league.ranking());
			}
			//Selecting
			rule.selectPlayers(rankings, this.finalists);
			super.init(finalists);
			//Make finalists play
			Tournament tournament = new Tournament(this.finalists);
			tournament.register(observers);
			tournament.play();
	}
	
	/**
	 * init the attributes of the master to run a new master
	 * @param competitors : the list of the competitors running in the master
	 */
	public void init(List<Competitor> competitors) {
		this.groups = new ArrayList<>();
		this.rankings = new ArrayList<>();
		this.finalists = new ArrayList<>();
		this.currentGroup = 0;
		super.init(competitors);
	}

	/**
	 * play the different pools of the master
	 * @param leagues the differents pools
	 */
	protected void playGroups(List<Competition> leagues){
		for(List<Competitor> pool : groups) {
			this.currentGroup++;
			this.displayMatches();
			League league = new League(pool);
			league.register(observers);
			league.play();
			leagues.add(league);
		}
	}
	
	/**
	 * Ranks the competitors after the second phase (tournament)
	 * @return the rank of the final tournament
	 */
	@Override
	public Map<Competitor, Integer> ranking(){ 
		Map<Competitor, Integer> tmp_rank = new HashMap<>();
		this.finalists.forEach(competitor -> tmp_rank.put(competitor, competitor.getPoints()));
		Map<Competitor, Integer> rank = MapUtil.sortByDescendingValue(tmp_rank);
		this.displayRanking(rank.keySet());
		return rank;
	}
	
	/**
	 * returns the finalists (the tournament participants)
	 * @return the finalists 
	 */
	public List<Competitor> getFinalists(){
		return this.finalists;
	}

	/**
	 * return the current group
	 * @return the current group
	 */
	public int getCurrentGroup() {
		return this.currentGroup;
	}
}
