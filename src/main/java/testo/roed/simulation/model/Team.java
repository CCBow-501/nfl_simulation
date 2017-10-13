package testo.roed.simulation.model;

import java.util.Set;

import testo.roed.simulation.game.Playbook;

public class Team {

	private String name;
	private Set<Player> players;
	private Playbook playbook;

	public Team() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Playbook getPlaybook() {
		return playbook;
	}

	public void setPlaybook(Playbook playbook) {
		this.playbook = playbook;
	}

	@Override
	public String toString() {
		return name;
	}

}
