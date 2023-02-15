package competitions.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import competitions.Competitor;
import competitions.util.UtilTest;
import exceptions.WrongAmountOfPlayersException;

public class TieBreakerTest{

	private final int NB = 10;
	
	@Test
	public void tieBreakerGivesTheGoodAmountOfPlayersWhenIsOk() {
		for (int i = 1; i < NB; i++) {
			List<Competitor> johnnyBoys = UtilTest.johnnyBoyGenerator(i + 3);
			TieBreaker breaker = new TieBreaker(johnnyBoys, i, 10);
			List<Competitor> requiredPlayers = breaker.startTieBreak();
			assertEquals(i, requiredPlayers.size());
		}
	}
	
	@Test
	public void tieBreakerThrowsWrongAmountOfPlayersExceptionWhenNotEnoughPlayers() {
		for(int i = 1; i < NB; i++) {
			List<Competitor> johnnyBoys = UtilTest.johnnyBoyGenerator(i - 1);
			TieBreaker breaker = new TieBreaker(johnnyBoys, i, 10);
			assertThrows(WrongAmountOfPlayersException.class, () -> {
				breaker.startTieBreak();
			});
		}
	}
	
	/**
	 *  checks if the first players are qualified, in a tie-break (they don't have to go for a tie-break if they have more points). Only
	 *  the player with same points have to go for it
	 */
	@Test
	public void FirstPlayersInTheTieBreakAreQualified() {
		List<Competitor> johnnyBoys = UtilTest.johnnyBoyGenerator(9);
		TieBreaker breaker;
		List<Competitor> res;
		for(int k = 3; k<9; k+=3) {
			for(int i = 0; i<9; i++) {
				johnnyBoys.get(i).setPoints((8-i)/3);
			}
			breaker= new TieBreaker(johnnyBoys, k+1, 10);
			res = breaker.startTieBreak();
			for(Competitor c: res) {
				System.out.printf("%s : %d\n", c.getName(), c.getPoints());
			}
			System.out.println("&&&&&&&&&&&");
			for(Competitor c: johnnyBoys) {
				System.out.printf("%s : %d\n", c.getName(), c.getPoints());
			}
			for(int i = 0; i<k; i++) {
				assertTrue(johnnyBoys.subList(0, k).contains(res.get(i)));
			}
		}
	}
	
	@Test
	public void GoodPlayersAreCalledInTheNewTieBreak() {
		List<Competitor> johnnyBoys = UtilTest.johnnyBoyGenerator(9);
		for(int i = 0; i<9; i++) {
			johnnyBoys.get(i).setPoints((8-i)/3);
		}
		TieBreakerMock breaker;
		List<Competitor> res;
		for(int k = 3; k<9; k+=3) {
			breaker= new TieBreakerMock(johnnyBoys, k+1);
			res = breaker.startTieBreak();
			for(Competitor competitor : res) {
				assertFalse(johnnyBoys.subList(0, k).contains(competitor));
			}
		}
	}
}

class TieBreakerMock extends TieBreaker{
	
	
	public TieBreakerMock(List<Competitor> competitors, int requiredPlayers) {
		super(competitors, requiredPlayers, 5);
	}
	
	@Override
	public List<Competitor> separateEqualities(){
		int points = this.ranking.get(this.number-1).getPoints();
		List<Competitor> equalities = new ArrayList<>(competitors);
		equalities = equalities.stream().filter(competitor -> competitor.getPoints() == points)
				.collect(Collectors.toList());
		
		return equalities;
	}
}
