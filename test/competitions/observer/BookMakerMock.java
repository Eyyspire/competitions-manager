package competitions.observer;

import competitions.match.Match;
import competitions.observer.BookMaker;

public class BookMakerMock extends BookMaker {
	
	private int i;
	
	public BookMakerMock() {
		super("Johnny Test");
		this.i = 0;
	}
	
	public void reactToMatch(Match match){
		this.i++;
	}
	
	public int getCalls() {
		return this.i;
	}

}