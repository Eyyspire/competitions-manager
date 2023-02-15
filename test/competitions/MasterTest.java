package competitions;
import static competitions.util.UtilTest.johnnyBoyGenerator;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competitions.observer.JournalistCountMock;
import competitions.rules.NumberOfGroups;
import competitions.rules.RankSelector;
import competitions.rules.Rule;

public class MasterTest extends CompetitionTest{

	@BeforeEach
	public void init() {

		rightAmountPlayers_list = johnnyBoyGenerator(8);
		wrongAmountPlayers_list = johnnyBoyGenerator(7);
		
		super.init();
		
		int i; List<Competitor> l;
		for (i = 8; i <= 16; i++) {
			l = johnnyBoyGenerator(i);
			this.lists.add(l);
		}
	}
	
	protected Competition createCompetition(List<Competitor> list) {
		Rule rule = new RuleMock();
		return new Master(list, rule);
	}
	
	@Override
	protected int countPoints(Competition competition) {
		int sum = 0;
		for (Competitor competitor : ((Master) competition).getFinalists()) {
			sum += competitor.getPoints();
		}
		return sum;
	}
	
	@Test
	public void ReactionMethodIsAppealedWhenAddedToACompetition(){
		JournalistCountMock kev = new JournalistCountMock();
		competition.register(kev);
		competition.play();
		assertTrue(this.countPoints(competition) <= kev.getCalls());
	}
	
	public int countMatches() {
		return 0;
	}
}

class RuleMock extends Rule{
	
	public RuleMock() {
		super(new NumberOfGroups(4), new RankSelector(2));
	}
}