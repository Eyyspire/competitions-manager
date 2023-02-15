package competitions.display.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import competitions.*;
import competitions.match.*;

public class MatchUtilDisplayTest {

	private Competitor c1;
	private Competitor c2;
	private BalancedMatch match;
	private MatchUtilDisplay util;

	@BeforeEach
	public void init() {
		this.c1 = new Competitor("Atomic Betty");
		this.c2 = new Competitor("Johnny Test");
		this.match = new BalancedMatch(c1, c2);
		this.util = new MatchUtilDisplay(match);
	}

	@Test
	public void normalizeStringsIsOk() {
		String[] competitors = { c1.getName(), c2.getName() };
		assertFalse(competitors[0].length() == competitors[1].length());
		util.normalizeStrings(competitors);
		assertTrue(competitors[0].length() == competitors[1].length());
	}
}
