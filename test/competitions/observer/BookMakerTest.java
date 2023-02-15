package competitions.observer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competitions.Competitor;
import competitions.match.Match;
import competitions.match.factory.BalancedMatchFactory;
import competitions.match.factory.MatchFactory;
import competitions.observer.BookMaker;

public class BookMakerTest {

	public static final int SIZE = 5;
	
	private BookMaker maker;
	private MatchFactory factory;
	private List<List<Competitor>> johnnyBoys;
	
	@BeforeEach
	public void init() {
		maker = new BookMaker("Johnny Test");
		factory = new BalancedMatchFactory();
		johnnyBoys = new ArrayList<>();
		createJohnnyBoys();
	}
	
	public void createJohnnyBoys() {
		Competitor j1;
		Competitor j2;
		for(int i = 2; i < SIZE; i++) {
			j1 = new Competitor("JB");
			j2 = new Competitor("JB");
			j1.setQuotation(i);
			j2.setQuotation(2*i);
			johnnyBoys.add(Arrays.asList(j1, j2));
		}
	}
	
	@Test
	public void newQuotsWorksAsExpected() {
		Match match;
		for(List<Competitor> jbs : johnnyBoys) {
			double oldQuotJ1 = jbs.get(0).getQuotation();
			double oldQuotJ2 = jbs.get(1).getQuotation();
			match = factory.createMatch(jbs.get(0), jbs.get(1));
			match.play();
			maker.newQuots(match.getWinner(), match.getLoser());
			double newQuotJ1 = jbs.get(0).getQuotation();
			double newQuotJ2 = jbs.get(1).getQuotation();
			if(match.getWinner().getQuotation() == newQuotJ1) {
				assertTrue(newQuotJ1 < oldQuotJ1);
				assertTrue(newQuotJ2 > oldQuotJ2);
			} else {
				assertTrue(newQuotJ2 < oldQuotJ2);
				assertTrue(newQuotJ1 > oldQuotJ1);
			}
		}
	}
	
}
