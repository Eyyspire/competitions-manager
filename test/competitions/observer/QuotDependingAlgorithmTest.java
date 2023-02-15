package competitions.observer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competitions.observer.QuotDependingAlgorithm;

public class QuotDependingAlgorithmTest {
	
	private QuotDependingAlgorithm algo;
	
	@BeforeEach
	public void init() {
		this.algo = new QuotDependingAlgorithm(0.1);
	}

	@Test
	public void QuotsAreAffectedByOpponentQuotes() {
		// goes up slower
		assertTrue(algo.compute(3, 2) < algo.compute(5,2));
		// goes down slower
		assertTrue(algo.compute(40, 50) > algo.compute(30,60));
	}
	
	@Test
	public void coeffIsBiggerWithBigQuotes() {
		assertTrue(algo.compute(50, 50) < algo.compute(100, 100));
		assertTrue(algo.compute(10, 20) < algo.compute(20, 30));
		assertTrue(algo.compute(0.10, 0.20) < algo.compute(0.2, 0.3));
	}
	
	@Test 
	public void quotesCannotBeLessThanOne() {
		double value = 1.01;
		for(int i = 0; i<30; i++){
			value = algo.compute(value, 1.01);
			assertTrue(value > 1);
		}
	}
	
	
}
