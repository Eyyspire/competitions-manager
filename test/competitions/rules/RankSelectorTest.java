package competitions.rules;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competitions.Competitor;
import competitions.util.UtilTest;
import exceptions.WrongAmountOfPlayersException;

public class RankSelectorTest {
	
	public final int NB = 10;
	
	private List<RankSelector> ranks;
	private List<List<Map<Competitor, Integer>>> rankingslist;
	private List<Competitor> finalists;
	
	@BeforeEach
	public void init() {
		this.ranks = new ArrayList<>();
		this.finalists = new ArrayList<>();
		createRanks();
		this.rankingslist = UtilTest.createRankings();
	}
	
	public void createRanks() {
		for (int i = 1; i<NB; i++) {
			ranks.add(new RankSelector(i));
		}
	}
	
	@Test
	public void selectPlayersThrowsWrongAmountOfPlayerExceptionWhenNotOk() {
		ranks.forEach(rank -> rankingslist.forEach(rankings -> {
			int size = UtilTest.countPlayers(rankings);
			if(size < rankings.size() * rank.getValue()) {
				assertThrows(WrongAmountOfPlayersException.class, () -> {
					rank.selectPlayers(rankings, finalists);
				});
			}
		}));
	}

}
