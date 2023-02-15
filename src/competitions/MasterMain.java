package competitions;

import java.util.Arrays;
import java.util.List;

import competitions.display.util.Colors;
import competitions.rules.NumberOfCompetitors;
import competitions.rules.RankSelector;
import competitions.rules.Rule;

public class MasterMain {

	public static void main(String[] args) {
		List<Competitor> competitors = Arrays.asList(new Competitor("Atomic Betty", Colors.RED),
				new Competitor("Ben 10", Colors.GREEN), new Competitor("Johnny Test", Colors.BRIGHTYELLOW),
				new Competitor("Johnny Boy", Colors.BLUE), new Competitor("Po", Colors.BRIGHTBLACK),
				new Competitor("Shifu", Colors.PURPLE), new Competitor("Dexter", Colors.WHITE),
				new Competitor("Jimmy Neutron", Colors.YELLOW));
		
		Rule rule = new Rule(new NumberOfCompetitors(3), new RankSelector(2));
		
		Competition master = new Master(competitors, rule);
		System.out.println("Master :");
		master.play();
		master.ranking();
	}
}