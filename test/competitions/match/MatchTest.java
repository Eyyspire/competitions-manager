package competitions.match;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import competitions.*;

public abstract class MatchTest {

	protected Competitor c1;
	protected Competitor c2;
	protected Match match;

	protected abstract Match createMatch(Competitor c1, Competitor c2);

	@BeforeEach
	public void init() {
		c1 = new Competitor("Johnny Test");
		c2 = new Competitor("Atomic Betty");
		match = this.createMatch(c1, c2);
		match.play();
	}

	@Test
	public void winnerChoosesOnlyOneWinnerAmongThePlayersOfTheMatch() {
		Competitor winner = match.getWinner();
		assertTrue(winner.getName() == "Johnny Test" ^ winner.getName() == "Atomic Betty");
	}
	
	@Test
	public void WinnersAndLosersAreWellSet() {
		Match mock = new MatchMock(c1,c2);
		mock.play();
		assertSame(c2, mock.getWinner());
		assertSame(c1, mock.getLoser());
	}
}


class MatchMock extends Match{
	
	public MatchMock(Competitor c1, Competitor c2){
		super(c1,c2);
	}
	
	public void play() {
		this.winner = c2;
	}
}