package testo.roed.simulation.game;

import java.time.Duration;
import java.util.Random;

import testo.roed.simulation.model.type.PlayType;
import testo.roed.simulation.model.type.TurnoverType;

public class Play {

	private static final Random random = new Random();
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

	public static Play generatePlay(PlayType playType, int yardsToEndzone) {
		int value;
		int gain = 0;
		boolean isIncomplete = false;
		boolean isFieldGoal = false;
		int fieldGoalDistance = yardsToEndzone + 17;
		TurnoverType turnoverType = TurnoverType.None;
		Duration duration = Duration.ofSeconds(0);

		if(playType == PlayType.Passing) {
			value = random.nextInt(100);
			if(value < 40) {
				gain = 0;
				isIncomplete = true;
				duration = Duration.ofSeconds(random.nextInt(10)+10);
			} else if(value >= 40 && value < 75) {
				//short pass
				gain = random.nextInt(10) + 5;
				duration = Duration.ofSeconds(random.nextInt(10)+23);
			} else if(value >= 75 && value < 88) {
				gain = random.nextInt(10) + 10;
				duration = Duration.ofSeconds(random.nextInt(10)+25);
			} else if(value >= 88 && value < 91) {
				gain = random.nextInt(10) + 15;
				duration = Duration.ofSeconds(random.nextInt(10)+29);
			} else if(value >= 91 && value < 93) {
				gain = random.nextInt(10) + 20;
				duration = Duration.ofSeconds(random.nextInt(10)+33);
			} else if(value >= 93 && value < 95) {
				gain = random.nextInt(10) + 30;
				duration = Duration.ofSeconds(random.nextInt(10)+34);
			} else if(value >= 95 && value < 96) {
				gain = random.nextInt(10) + 40;
				duration = Duration.ofSeconds(random.nextInt(10)+35);
			} else if(value >= 96 && value < 97) {
				gain = random.nextInt(10) + 50;
				duration = Duration.ofSeconds(random.nextInt(10)+36);
			} else if(value >= 97) {
				gain = random.nextInt(10) + 60;
				duration = Duration.ofSeconds(random.nextInt(10)+38);
			}
		} else if(playType == PlayType.Rushing) {
			value = random.nextInt(100);
			if(value < 2) {
				gain =  random.nextInt(10) + 5;
				turnoverType = TurnoverType.Fumble;
				duration = Duration.ofSeconds(random.nextInt(5)+23);
			} else if(value >= 2 && value < 30) {
				//stopped in backfield
				gain = random.nextInt(5) * -1;
				duration = Duration.ofSeconds(random.nextInt(5)+23);
			} else if(value >= 30 && value < 58) {
				gain = 0;
				duration = Duration.ofSeconds(random.nextInt(5)+24);
			} else if(value >= 58 && value < 80) {
				gain = random.nextInt(5) +  1;
				duration = Duration.ofSeconds(random.nextInt(5)+25);
			} else if(value >= 80 && value < 90) {
				gain = random.nextInt(10) + 5;
				duration = Duration.ofSeconds(random.nextInt(5)+26);
			} else if(value >= 90 && value < 94) {
				gain = random.nextInt(10) + 15;
				duration = Duration.ofSeconds(random.nextInt(5)+28);
			} else if(value >= 94 && value < 97) {
				gain = random.nextInt(10) + 20;
				duration = Duration.ofSeconds(random.nextInt(5)+30);
			} else if(value >= 97 && value < 98) {
				gain = random.nextInt(10) + 25;
				duration = Duration.ofSeconds(random.nextInt(5)+32);
			} else if(value >= 98) {
				gain = random.nextInt(10) + 35;
				duration = Duration.ofSeconds(random.nextInt(5)+34);
			}
		} else if (playType == PlayType.Punt) {
				gain = random.nextInt(20) + 40;
				duration = Duration.ofSeconds(random.nextInt(10)+23);
				turnoverType = TurnoverType.Punt;
		}
		else if (playType == PlayType.FieldGoal) {
			value = random.nextInt(11);
			duration = Duration.ofSeconds(random.nextInt(10)+20);
			if(fieldGoalDistance > 60) {
				if(value >9) {
					isFieldGoal = true;
				} else {
					isFieldGoal = false;
				}
			} else if(fieldGoalDistance <= 60 &&fieldGoalDistance > 55) {
				if(value >8) {
					isFieldGoal = true;
				} else {
					isFieldGoal = false;
				}
			} else if(fieldGoalDistance <= 55 &&fieldGoalDistance > 50) {
				if(value >6) {
					isFieldGoal = true;
				} else {
					isFieldGoal = false;
				}
			} else if(fieldGoalDistance <= 50 && fieldGoalDistance > 35){
				if(value >4) {
					isFieldGoal = true;
				} else {
					isFieldGoal = false;
				}
			} else {
				if(value >2) {
					isFieldGoal = true;
				} else {
					isFieldGoal = false;
				}
			}
		}

		return new Play(gain, playType, isIncomplete, duration, turnoverType, isFieldGoal);
	}

	public static boolean extraPoint() {
		int value = random.nextInt(50);
		if(value <42) {
			return true;
		} else {
			return false;
		}
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
