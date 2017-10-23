package testo.roed.simulation.game;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

import testo.roed.simulation.model.type.PlayType;

public class Playbook {

	private Set<Play> plays;

	public Playbook() {

	}

	public Play selectPlay(int down, int yardsToGo, Duration timeLeft, int scoreDifference, int yardsToEndzone) {
		Random r = new Random();
		int value = r.nextInt(2);
		PlayType playType;

//		switch(down) {
//		case 1:
//		case 2:
//		case 3:
//			if(yardsToGo > 6) {
//				playType = PlayType.Passing;
//			}
//		case 4:
//			if(yardsToEndzone > 40) {
//				if(time)
//				playType = PlayType.Punt;
//			}
//		}



		if(down == 4 ) {
			if(yardsToEndzone > 40) {
				playType = PlayType.Punt;
			} else {
				playType = PlayType.FieldGoal;
			}
			if(timeLeft.getSeconds() < Duration.ofSeconds(120).getSeconds() && scoreDifference <0) {
				if(yardsToGo>3) {
					playType = PlayType.Passing;
				} else {
					switch(value) {
						case 0 : playType = PlayType.Passing;break;
						case 1: playType = PlayType.Rushing;break;
						default : playType = PlayType.Passing;break;
					}
				}
			}
		} else {
			if(yardsToEndzone > 10 && yardsToEndzone <= 45 && timeLeft.getSeconds() < 20 && scoreDifference >= -3) {
				playType = PlayType.FieldGoal;
			} else if(scoreDifference >= -7 && scoreDifference < 0 && timeLeft.getSeconds() < 90 && yardsToEndzone < 50){
				playType = PlayType.Passing;
			} else if(scoreDifference > 0 && timeLeft.getSeconds() < 270) {
				value = r.nextInt(5);
				switch(value) {
					case 0 : playType = PlayType.Passing;break;
					case 1:case 2:case 3:case 4: playType = PlayType.Rushing;break;
					default : playType = PlayType.Rushing;break;
				}
			}
			else {
				switch(value) {
					case 0 : playType = PlayType.Passing;break;
					case 1: playType = PlayType.Rushing;break;
					default : playType = PlayType.Passing;break;
				}
			}
		}
		return PlayGenerator.generatePlay(playType, yardsToEndzone, timeLeft);
	}

	public static Playbook generateDefaultRunningPlaybook() {
		return new Playbook();
	}

	public static Playbook generateDefaultPassingPlaybook() {
		return new Playbook();
	}

	private Play getRandomPlay() {
		return null;
	}

}
