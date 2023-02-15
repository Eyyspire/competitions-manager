package competitions.display.rules;

import java.util.List;

import competitions.Competitor;
import competitions.display.ConsoleDisplay;
import competitions.display.Display;
import competitions.display.util.Colors;
import competitions.rules.TieBreaker;

public class TieBreakDisplayer {

	private static Display display = new ConsoleDisplay();
	
	private TieBreaker tieBreaker;
	
	public TieBreakDisplayer(TieBreaker tieBreaker) {
		this.tieBreaker = tieBreaker;
	}
	
	public void welcome() {
		String string = Colors.colorize("We have "+ tieBreaker.getCompetitors().size() +" competitors running for "+ tieBreaker.getNumber() +" places : time for a tie-break !\n", Colors.BRIGHTYELLOW);
		display.println(string);
		display.println("-".repeat(string.length()));
	}
	
	public void equality() {
		String string = Colors.colorize("\nThere are still players with equal points... Let's do a new tie break to see who will be qualified", Colors.BRIGHTYELLOW);
		display.println(string);
		display.println("-".repeat(string.length()));
	}
	
	public void conclusion(List<Competitor> res) {
		String string = Colors.colorize("\nHere are the winners of the tie-break :", Colors.BRIGHTYELLOW);
		display.println(string);
		display.println("-".repeat(string.length()));
		for(Competitor c: res) {
			display.println(c.getColoredName());
			display.println("-".repeat(string.length()));
		}
	}
	
	public void posts() {
		display.println(Colors.colorize("\nThis is the last tie-break. The players are going to take the pole trial from Koh-Lanta.\n"
				+ "The competitors that will remain on the poles will be qualified. " + "Let's go!", Colors.GREEN));
	}
	
	public void fall(Competitor c) {
		display.printf(Colors.colorize("Splash! "+ c.getColoredName() +" fell off the post... he is eliminated from the poll\n", Colors.GREEN));
		display.println("---");
	}
	
}
