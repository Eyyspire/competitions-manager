package competitions;

import java.util.*;
import competitions.display.util.Colors;

public class Main {

	public static void main(String[] args) {
		List<Competitor> competitors = Arrays.asList(new Competitor("Atomic Betty", Colors.RED),
				new Competitor("Ben 10", Colors.GREEN), new Competitor("Johnny Test", Colors.BRIGHTYELLOW),
				new Competitor("Johnny Boy", Colors.BLUE), new Competitor("Po", Colors.BRIGHTBLACK),
				new Competitor("Shifu", Colors.PURPLE), new Competitor("Dexter", Colors.WHITE),
				new Competitor("Jimmy Neutron", Colors.YELLOW));
		List<Competitor> competitorsCopy = List.copyOf(competitors);
		Competition league = new League(competitors);
		Competition tournament = new Tournament(competitorsCopy);

		System.out.println("League :");
		league.play();
		league.ranking();
		System.out.println("\nTournament :");
		tournament.play();
		tournament.ranking();
	}
}