package competitions.match.factory;

import competitions.Competitor;
import competitions.match.BalancedMatch;
import competitions.match.Match;

public class BalancedMatchFactory implements MatchFactory{

	public BalancedMatchFactory() {};
	
	public Match createMatch(Competitor c1, Competitor c2) {
		return new BalancedMatch(c1,c2);
	}
	
}
