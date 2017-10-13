package testo.roed.simulation;

import testo.roed.simulation.game.Playbook;
import testo.roed.simulation.model.Team;

public class Main {

	public static void main(String[] args) {

		Team team1 = new Team();
		team1.setName("Arizona Cardinals");
		team1.setPlaybook(new Playbook());
		Team team2 = new Team();
		team2.setName("Dallas Cowboys");
		team2.setPlaybook(new Playbook());

		Game.simulate(team1, team2);
	}

}
