package testo.roed.simulation.game;

import java.time.Duration;

import testo.roed.simulation.model.type.PlayType;
import testo.roed.simulation.model.type.TurnoverType;

public class Play {

	private int gain;
	private boolean isIncomplete;
	private boolean isFieldGoal;
	private PlayType playType;
	private TurnoverType turnoverType;
	private Duration duration;

	public Play(int gain, PlayType playType, boolean isIncomplete, Duration duration, TurnoverType turnoverType, boolean isFieldGoal) {
		this.gain = gain;
		this.playType = playType;
		this.isIncomplete = isIncomplete;
		this.turnoverType = turnoverType;
		this.duration = duration;
		this.isFieldGoal = isFieldGoal;
	}

	public static Play kickoff() {
		return null;
	}

	public int getGain() {
		return gain;
	}

	public void setGain(int gain) {
		this.gain = gain;
	}

	public boolean isIncomplete() {
		return isIncomplete;
	}

	public boolean isFieldGoal() {
		return isFieldGoal;
	}

	public void setFieldGoal(boolean isFieldGoal) {
		this.isFieldGoal = isFieldGoal;
	}

	public void setIncomplete(boolean isIncomplete) {
		this.isIncomplete = isIncomplete;
	}

	public PlayType getPlayType() {
		return playType;
	}

	public void setPlayType(PlayType playType) {
		this.playType = playType;
	}

	public TurnoverType getTurnoverType() {
		return turnoverType;
	}

	public void setTurnoverType(TurnoverType turnoverType) {
		this.turnoverType = turnoverType;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

}
