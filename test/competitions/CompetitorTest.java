package competitions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import competitions.display.util.Colors;

public class CompetitorTest {

	private Competitor c;

	@BeforeEach
	public void init() {
		c = new Competitor("Johnny Test", Colors.BLUE);
	}

	@Test
	public void addPointsIsOk() {
		c.setPoints(3);
		assertEquals(3, c.getPoints());
		c.addPoints(3);
		assertEquals(6, c.getPoints());
	}
}