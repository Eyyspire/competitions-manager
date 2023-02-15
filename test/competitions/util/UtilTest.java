package competitions.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import competitions.Competition;
import competitions.Competitor;
import competitions.League;
import competitions.rules.GroupMaker;
import competitions.rules.NumberOfGroups;

public class UtilTest {
	
	public static List<Competitor> johnnyBoyGenerator(int number){
		List<Competitor> johnnyBoys = new ArrayList<>();
		for(int i = 0; i<number; i++) {
			johnnyBoys.add(new Competitor("Johnny Boy"));
		}
		return johnnyBoys;
	}
	
	public static List<List<Map<Competitor, Integer>>> createRankings() {
		List<List<Competitor>> groups = new ArrayList<>();
		List<List<Map<Competitor, Integer>>> rankingslist = new ArrayList<>();
		List<Competitor> competitors = new ArrayList<>();
		for (int i = 1; i<5; i++) {
			for (int j = 1; j<5; j++) {
				competitors.clear();
				competitors = johnnyBoyGenerator((int) Math.pow(2, i));
				if (competitors.size() >= j*2) {
					groups.clear();
					GroupMaker maker = new NumberOfGroups(j);
					maker.makeGroups(competitors, groups);
					List<Map<Competitor, Integer>> tmp = new ArrayList<>();
					groups.forEach(group -> {
						Competition league = new League(group);
						league.play();
						tmp.add(league.ranking());
					});
					rankingslist.add(tmp);
				}
			}
		}
		return rankingslist;
	}
	
	public static int countPlayers(List<Map<Competitor, Integer>> rankings) {
		int tmp = 0;
		for(Map<Competitor, Integer> ranking : rankings) {
			tmp+=ranking.size();
		}
		return tmp;
	}
}
