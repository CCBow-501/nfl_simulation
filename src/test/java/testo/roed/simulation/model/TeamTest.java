package testo.roed.simulation.model;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class TeamTest {

	@Test
	public void test_team_name_notnull() {
		Team t = new Team();
		t.setName("name");

		assertThat(t.getName(), notNullValue());
	}

}
