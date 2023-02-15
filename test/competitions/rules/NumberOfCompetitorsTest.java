package competitions.rules;

import static competitions.util.UtilTest.johnnyBoyGenerator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competitions.Competitor;
import exceptions.WrongAmountOfPlayersException;

public class NumberOfCompetitorsTest {
	
	public final int NB = 10;
	public final int LISTES_NB = 5;
	
	protected List<NumberOfCompetitors> groupMakers;
	protected List<List<Competitor>> competitors;
	
	protected List<List<Competitor>> groups;
	
	public void createGroupMakers() {
		for (int i = 1; i<NB; i++) {
			groupMakers.add(new NumberOfCompetitors(i));
		}
	}

	public void createCompetitors() {
		for (int i = 1; i<LISTES_NB; i++) {
			competitors.add(johnnyBoyGenerator((int) Math.pow(2,i)));
		}
	}
	
	@BeforeEach
	public void init() {
		this.groupMakers = new ArrayList<>();
		this.competitors = new ArrayList<>();
		this.groups = new ArrayList<>();
		createGroupMakers();
		createCompetitors();
	}
	
	@Test
	public void MakeGroupsGivesACorrectNumberOfGroup() {
		groupMakers.forEach(maker -> {
			competitors.forEach(c -> {
				groups.clear();
				if (maker.getValue() <= c.size()) {
					maker.makeGroups(c, groups);
				}
				assertEquals(groups.size(), c.size()/maker.getValue());
			});
		});	
	}
	
	@Test
	public void MakeGroupsGivesCorrectSizeGroups() {
		groupMakers.forEach(maker -> {
			competitors.forEach(c -> {
				groups.clear();
				if (maker.getValue() <= c.size() && c.size()%maker.getValue() == 0) {
					maker.makeGroups(c, groups);
					groups.forEach(g -> assertEquals(g.size(), maker.getValue()));
				}
			});
		});	
	}
	
	@Test
	public void MakegroupsthrowsAnErrosIfGroupsSizesAreSuperirorToCompetitorsNumber() {
		groupMakers.forEach(maker -> {
			competitors.forEach(c -> {
				groups.clear();
				if (maker.getValue() > c.size()) {
					assertThrows(WrongAmountOfPlayersException.class, () -> {
						maker.makeGroups(c, groups);
					});
				}
			});
		});
	}
	
	
}
