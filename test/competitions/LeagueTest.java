package competitions;
import static competitions.util.UtilTest.johnnyBoyGenerator;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competitions.observer.BookMakerMock;
import competitions.observer.JournalistCountMock;

public class LeagueTest extends CompetitionTest {

	private League league;

	@BeforeEach
	public void init() {
		rightAmountPlayers_list = johnnyBoyGenerator(2);
		wrongAmountPlayers_list = johnnyBoyGenerator(1);
		
		super.init();
		
		int i; List<Competitor> l;
		for (i = 3; i<= 11; i+=2) {
			l = johnnyBoyGenerator(i);
			this.lists.add(l);
		}
	}

	protected Competition createCompetition(List<Competitor> list) {
		return new League(list);
	}

	@Test
	public void playMakesCompetitorsPlayEachOtherTwice() {
		for(List<Competitor> competitors: lists) {
			league = new League(competitors);
			league.play();
			int sum = 0;
			for (Competitor competitor : competitors) {
				sum += competitor.getPoints();
			}
			assertEquals((competitors.size() - 1) * competitors.size(), sum);
		}
		
	}
	
	@Test
	public void ReactionMethodIsAppealedWhenAddedToACompetition(){
		JournalistCountMock kev = new JournalistCountMock();
		BookMakerMock fab = new BookMakerMock();
		competition.register(kev);
		competition.register(fab);
		competition.play();
		assertEquals(this.countMatches(), kev.getCalls());
		assertEquals(this.countMatches(), fab.getCalls());
	}

	
	public int countMatches() {
		return competition.getCompetitors().size() * (competition.getCompetitors().size() - 1);
	}
	
	

}
