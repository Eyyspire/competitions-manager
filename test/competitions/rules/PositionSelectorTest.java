package competitions.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competitions.Competitor;
import competitions.util.UtilTest;
import exceptions.WrongAmountOfPlayersException;

public class PositionSelectorTest {
	
	private List<PositionSelector> ps;
	private List<List<Map<Competitor, Integer>>> rankingslist;
	private List<Competitor> finalists;
	
	@BeforeEach
	public void init() {
		int[] tab1 = {5,3};
		int[] tab2 = {4,2,1,0};
		this.ps = new ArrayList<>();
		ps.add(new PositionSelector(tab1));
		ps.add(new PositionSelector(tab2));
		this.rankingslist = UtilTest.createRankings();
		this.finalists = new ArrayList<>();
	}
	
	@Test
	public void getValueReturnsTheGoodAmountOfPlayers() {
		PositionSelector ps1 = ps.get(0);
		PositionSelector ps2 = ps.get(1);
		assertEquals(8, ps1.getValue());
		assertEquals(7, ps2.getValue());
	}
	
	@Test
	public void selectPlayersThrowsWrongAmountOfPlayerExceptionWhenNotOk() {
		ps.forEach(select -> rankingslist.forEach(rankings -> {
			int size = UtilTest.countPlayers(rankings);
			Boolean problemOfPlayersNumber = false;
			for (int i = 0; i < select.getTab().length; i++) {
				int value = select.getTab()[i];
				if (value > rankings.size()) {
					problemOfPlayersNumber = true;
					break;
				}
				for (Map<Competitor, Integer> ranking : rankings) {
					if (i > ranking.size()) {
						problemOfPlayersNumber = true;
						break;
					}
				}
			}
			if(size < select.getValue() || problemOfPlayersNumber) {
				assertThrows(WrongAmountOfPlayersException.class, () -> {
					select.selectPlayers(rankings, finalists);
				});
			}
		}));
	}
	
	@Test 
	public void selectPlayersSelectsTheGoodNumberOfPlayers() {
		for(PositionSelector select : ps) {
			for(List<Map<Competitor, Integer>> rankings : rankingslist) {
				Boolean noProblemOfPlayersNumber = true;
				for (int i = 0; i < select.getTab().length; i++) {
					int value = select.getTab()[i];
					if (value > rankings.size()) {
						noProblemOfPlayersNumber = false;
						break;
					}
					for (Map<Competitor, Integer> ranking : rankings) {
						if (i > ranking.size()) {
							noProblemOfPlayersNumber = false;
							break;
						}
					}
				}
				if(UtilTest.countPlayers(rankings) >= select.getValue() && noProblemOfPlayersNumber) {
					finalists.clear();
					select.selectPlayers(rankings, finalists);
					assertEquals(select.getValue(), finalists.size());
				}
				
			}
		}
	}
	
}
