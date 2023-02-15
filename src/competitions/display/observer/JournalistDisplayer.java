package competitions.display.observer;

import competitions.Competitor;
import competitions.display.ConsoleDisplay;
import competitions.display.Display;

public class JournalistDisplayer {

	private static Display display = new ConsoleDisplay();

	public void reaction(Competitor c1, Competitor c2, String phrase) {
		display.println(String.format(phrase, c1.getColoredName(), c2.getColoredName()));
	}
}
