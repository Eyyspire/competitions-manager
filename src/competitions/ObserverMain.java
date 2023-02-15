package competitions;

import java.util.Arrays;
import java.util.List;

import competitions.display.util.Colors;
import competitions.observer.BookMaker;
import competitions.observer.Journalist;

public class ObserverMain {

	public static void main(String[] args) {
		List<Competitor> competitors = Arrays.asList(new Competitor("Atomic Betty", Colors.RED),
				new Competitor("Ben 10", Colors.GREEN), new Competitor("Johnny Test", Colors.BRIGHTYELLOW),
				new Competitor("Johnny Boy", Colors.BLUE), new Competitor("Po", Colors.BRIGHTBLACK),
				new Competitor("Shifu", Colors.PURPLE), new Competitor("Dexter", Colors.WHITE),
				new Competitor("Jimmy Neutron", Colors.YELLOW));
		// List<Competitor> competitorsCopy = List.copyOf(competitors);
		Competition league = new League(competitors);

		System.out.println("League with observers:");
		league.register(new Journalist(Arrays.asList("logical win of %s against %s",
				"%s won with difficulty against %s",
				"%s widely won with difficulty against %s")));
		league.register(new BookMaker("Winamax"));
		league.play();
		league.ranking();
	}
}
