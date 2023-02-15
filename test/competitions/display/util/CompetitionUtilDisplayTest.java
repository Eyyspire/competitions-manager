package competitions.display.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import org.junit.jupiter.api.*;
import competitions.*;

public class CompetitionUtilDisplayTest {

	private Competitor c1;
	private Competitor c2;
	private League league;
	private CompetitionUtilDisplay util;

	@BeforeEach
	public void init() {
		this.c1 = new Competitor("Atomic Betty");
		this.c2 = new Competitor("Johnny Test");
		this.league = new League(Arrays.asList(c1, c2));
		this.util = new CompetitionUtilDisplay(league);

	}

	@Test
	public void positionNextPlayerReturnsRightPosition() {
		c1.setPoints(3);
		c2.setPoints(2);
		int[] nextPosition = { 0, 0, -1 };
		util.positionNextPlayer(nextPosition, c1.getPoints());
		assertEquals(1, nextPosition[0]);
		util.positionNextPlayer(nextPosition, c2.getPoints());
		assertEquals(2, nextPosition[0]);
	}

	@Test
	public void winWithSPutsSWhenNeeded() {
		c1.setPoints(2);
		c2.setPoints(1);
		assertEquals("wins", util.winWithS(c1.getPoints()));
		assertEquals("win", util.winWithS(c2.getPoints()));
	}

}
