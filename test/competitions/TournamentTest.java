package competitions;

import static competitions.util.UtilTest.johnnyBoyGenerator;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competitions.observer.BookMakerMock;
import competitions.observer.JournalistCountMock;

public class TournamentTest extends CompetitionTest {

	private Tournament tournament;

	@BeforeEach
	public void init() {

		rightAmountPlayers_list = johnnyBoyGenerator(2);
		wrongAmountPlayers_list = johnnyBoyGenerator(1);
		
		super.init();
		
		int i; List<Competitor> l;
		for (i = 2; i <= 16; i *= 2) {
			l = johnnyBoyGenerator(i);
			this.lists.add(l);
		}
	}

	protected Competition createCompetition(List<Competitor> list) {
		return new Tournament(list);
	}

	@Test
	public void GoodHalfOfThePlayersRemainAfterATurn() {
		
		for (List<Competitor> list : this.lists) {
			tournament = new Tournament(list);
			tournament.playTurn();
			assertEquals(list.size() / 2, tournament.getRemaining().size());
			for (Competitor competitor : tournament.getRemaining()) {
				assertEquals(tournament.getCurrentTurn(), competitor.getPoints());
			}
		}
	}

	@Test
	public void OnlyTheWinningPlayerIsRemainingAtTheEnd() {
		for (List<Competitor> list : lists) {
			tournament = new Tournament(list);
			tournament.play();
			assertEquals(tournament.getRemaining().size(), 1);
			assertEquals(tournament.getTotalTurns(), tournament.getRemaining().get(0).getPoints());
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
		return competition.getCompetitors().size()-1;
	}
}