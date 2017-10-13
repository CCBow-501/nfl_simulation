package testo.roed.simulation.game;

import java.time.Duration;

public class Gameclock {

	private Duration duration;

	public Gameclock() {
	}

	public void subtractPlayLength(long playLength) {
		this.duration = this.duration.minusSeconds(playLength);
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(long l) {
		duration = duration.ofMinutes(l);
	}

}
