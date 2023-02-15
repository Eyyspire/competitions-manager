package competitions.observer;

import java.util.ArrayList;

import competitions.match.Match;
import competitions.observer.Journalist;

public class JournalistCountMock extends Journalist {
	
	private int i;
	
	public JournalistCountMock() {
		super(new ArrayList<>());
		this.i = 0;
	}
	
	public void reactToMatch(Match match){
		this.i++;
	}
	
	public int getCalls() {
		return this.i;
	}

}
