package competitions.display.observer;

import competitions.display.ConsoleDisplay;
import competitions.display.Display;

public class BookmakerDisplayer {

		private static Display display = new ConsoleDisplay();

		public void reaction(String name, Double oldQ1, Double oldQ2, Double newQ1, Double newQ2, String phrase) {
			display.println(String.format(phrase, name, oldQ1, newQ1, oldQ2, newQ2));
		}

}
