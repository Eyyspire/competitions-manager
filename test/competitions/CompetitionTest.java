package competitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import exceptions.WrongAmountOfPlayersException;

public abstract class CompetitionTest {

	protected Competition competition;

    protected List<Competitor> rightAmountPlayers_list;
    protected List<Competitor> wrongAmountPlayers_list;
    
    protected List<List<Competitor>> lists;
    
    protected abstract Competition createCompetition(List<Competitor> list);


	public void init() {
		competition = createCompetition(rightAmountPlayers_list);
		lists = new ArrayList<>();
	}

	@Test
	public void playMatchGivesExactlyOnePointToOnePlayer() {

		assertEquals(0, competition.getCompetitors().get(0).getPoints());
		assertEquals(0, competition.getCompetitors().get(1).getPoints());

		competition.playMatch(competition.getCompetitors().get(0), competition.getCompetitors().get(1));

		assertTrue(competition.getCompetitors().get(0).getPoints() == 1
				|| competition.getCompetitors().get(1).getPoints() == 1);
		assertFalse(competition.getCompetitors().get(0).getPoints() == 1
				&& competition.getCompetitors().get(1).getPoints() == 1);
	}

	@Test
	public void rankingDisplaysRightPlayers() { //Test not good enough
		competition.play();
		Map<Competitor, Integer> rank = competition.ranking();
		List<Competitor> competitors = competition.getCompetitors();
		for (Competitor competitor : rank.keySet()) {
			assertTrue(competitors.contains(competitor));
		}
	}
	
	@Test
	public void rankingGivesCorrectPointsForEachPlayer() {
		competition.play();
		Map<Competitor, Integer> rank = competition.ranking();
		for (Competitor competitor : rank.keySet()) {
			assertEquals(competitor.getPoints(), rank.get(competitor));
		}
	}
	
	@Test
	public void rankingDisplaysCompetitorsInRightOrder() {
		competition.play();
		Map<Competitor, Integer> rank = competition.ranking();
		int previousPoints = -1;
		for (Competitor competitor : rank.keySet()) {
			assertTrue(previousPoints == -1 || rank.get(competitor) <= previousPoints);
			previousPoints = rank.get(competitor);
		}
	}

	@Test
	public void playThrowsWrongAmountOfPlayersException() {
		Competition wrongComp = createCompetition(wrongAmountPlayers_list);
		assertThrows(WrongAmountOfPlayersException.class, () -> {
			wrongComp.play();
		});
	}

	@Test
	public void playIsOkWhenRightAmountCompetitors() {
		try {
			competition.play();
		} catch (WrongAmountOfPlayersException e) {
			fail();
		}
	}

	@Test
	public void pointsAreResetWhenNewCompetitionStarts() {
		int oldSum = this.countPoints(competition);
		assertEquals(0, oldSum);
		competition.play();
		oldSum = this.countPoints(competition);
		competition.play();
		assertEquals(oldSum, this.countPoints(competition));
	}
	
	protected int countPoints(Competition competition) {
		int sum = 0;
		for (Competitor competitor : competition.getCompetitors()) {
			sum += competitor.getPoints();
		}
		return sum;
	}
}
