package testo.roed.simulation.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class ResultTest {

	@Test
	public void test_result_has_scores() {
		Result r = new Result();
		r.setHomescore(2);
		r.setRoadscore(1);

		assertThat(r.getHomescore(), equalTo(2));
		assertThat(r.getRoadscore(), equalTo(1));
	}

}
