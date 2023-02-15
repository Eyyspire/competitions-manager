package competitions.display.rules;

import java.util.List;

import competitions.Competitor;
import competitions.display.ConsoleDisplay;
import competitions.display.Display;

public class RankSelectorDisplayer {
	
private static Display display = new ConsoleDisplay();
	
	public RankSelectorDisplayer() {
	}
	
	public void conclusion(List<Competitor> finalists) {
		String string = "\nHere are all the qualified for this poll :";
		display.println(string);
		display.println("-".repeat(string.length()));
		for(Competitor c: finalists) {
			display.println(c.getColoredName());
		}
		display.println("-".repeat(string.length()));
		
	}
}
