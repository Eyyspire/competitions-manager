package competitions.match;

import competitions.Competitor;

public class BalancedMatchTest extends MatchTest {

	protected Match createMatch(Competitor c1, Competitor c2) {
		return new BalancedMatch(c1, c2);
	}
	
	
}

