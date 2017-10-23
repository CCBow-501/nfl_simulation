package testo.roed.simulation;

import testo.roed.simulation.game.Field;
import testo.roed.simulation.game.Gameclock;
import testo.roed.simulation.game.Play;
import testo.roed.simulation.game.PlayGenerator;
import testo.roed.simulation.model.Result;
import testo.roed.simulation.model.Team;
import testo.roed.simulation.model.type.PlayType;
import testo.roed.simulation.model.type.TurnoverType;

public class Game {

	private static Team hometeam;
	private static Team roadteam;
	private static int homescore;
	private static int roadscore;
	private static int homePassingYards;
	private static int homeRushingYards;
	private static int homePassAttemps;
	private static int homePassCompletions;
	private static int roadPassingYards;
	private static int roadRushingYards;
	private static int roadPassAttemps;
	private static int roadPassCompletions;
	private static Result result;
	private static Field field;
	private static Gameclock gameclock;
	private static Team teamInPosession;
	private static int quarter;
	private static int down;
	private static int yardsToGo;

	public static Result getResult() {
		return result;
	}

	public static void simulate(Team hometeam, Team roadteam) {
		//pre game setup
		setHometeam(hometeam);
		setRoadteam(roadteam);
		homescore = 0;
		roadscore = 0;
		homePassingYards = 0;
		homeRushingYards = 0;
		roadPassingYards = 0;
		roadRushingYards = 0;
		field = new Field();
		gameclock = new Gameclock();
		gameclock.setDuration(15);
		quarter = 1;

		teamInPosession = hometeam;
		field.setKickoffPosition(teamInPosession);



		down = 1;
		yardsToGo = 10;

		while(quarter <=4) {
			simulateQuarter();
			gameclock.setDuration(15);
		}
		result = new Result();
		result.setHomescore(homescore);
		result.setRoadscore(roadscore);
		System.out.println(hometeam + " "+homePassCompletions+"/"+homePassAttemps + " " + homePassingYards + " Yards Passing " + homeRushingYards + " Yards Rushing" );
		System.out.println(roadteam + " "+roadPassCompletions+"/"+roadPassAttemps + " " + roadPassingYards + " Yards Passing " + roadRushingYards + " Yards Rushing" );
	}

	private static void simulateQuarter() {
		System.out.println("Start of Quarter: " + quarter);
		while((!gameclock.getDuration().isZero() && !gameclock.getDuration().isNegative())) {
			simulateBallPossession();
			System.out.println(hometeam + " " + homescore + " : " + roadscore + " " + roadteam);
		}
		quarter++;
	}

	private static void simulateBallPossession() {
		boolean turnover = false;
		boolean leftToRight = false;
		boolean isFieldGoal = false;
		boolean timeLeft = true;
		int scoreDifference;
		Play lastPlay = null;
		String turnoverString = "";
		if(teamInPosession == hometeam) {
			leftToRight = true;
		}

		while(!field.isTouchdown() && !isFieldGoal && !turnover && down <= 4 && yardsToGo > 0 && timeLeft) {
			if(teamInPosession == hometeam) {
				scoreDifference = homescore -roadscore;
			}else {
				scoreDifference = roadscore -homescore;
			}
			System.out.println(teamInPosession + " has " + field.getFieldposition(leftToRight) + " on " + down + " and " + yardsToGo + " with " + gameclock.getDuration() + " left");
			Play p = teamInPosession.getPlaybook().selectPlay(down, yardsToGo, gameclock.getDuration(), scoreDifference, field.yardsToEndzone(leftToRight));
			if(p.getTurnoverType() != TurnoverType.None) {
				turnover = true;
				if(p.getTurnoverType() == TurnoverType.Fumble ) {
					turnoverString = "Fumbles Ball";
				}
			}
			if(p.getPlayType() == PlayType.Passing) {
				if(teamInPosession == hometeam) {
					homePassingYards += p.getGain();
					homePassAttemps++;
					if(p.getGain() != 0) {
						homePassCompletions++;
					}
				}else {
					roadPassingYards += p.getGain();
					roadPassAttemps++;
					if(p.getGain() != 0) {
						roadPassCompletions++;
					}
				}
			} else if(p.getPlayType() == PlayType.Rushing) {
				if(teamInPosession == hometeam) {
					homeRushingYards += p.getGain();
				}else {
					roadRushingYards += p.getGain();
				}
			}

			if(p.isFieldGoal()) {
				isFieldGoal = true;
			}
			if(p.getGain() >= field.yardsToEndzone(leftToRight) && p.getTurnoverType() != TurnoverType.Punt) {
				p.setGain(field.yardsToEndzone(leftToRight));
			}
			if(p.getPlayType() != PlayType.FieldGoal) {
				System.out.println(teamInPosession + " " + p.getPlayType().toString() + " for " + p.getGain() + " Yards " + turnoverString);
			} else {
				if(isFieldGoal) {
					System.out.println(teamInPosession + " " + p.getPlayType().toString() + " from " + field.fieldGoalDistance(leftToRight) + " Yards good");
				} else {
					System.out.println(teamInPosession + " " + p.getPlayType().toString() + " from " + field.fieldGoalDistance(leftToRight) + " Yards no good");
				}

			}
			field.adjustFieldPosition(p.getGain(), leftToRight);
			if(p.getGain() >= yardsToGo) {
				down = 1;
				yardsToGo = 10;
			} else {
				down++;
				yardsToGo -= p.getGain();
			}
			adjustGameclock(p.getDuration().getSeconds());
			lastPlay = p;
			if(gameclock.getDuration().isZero() || gameclock.getDuration().isNegative()) {
				timeLeft = false;
			}
		}



		if(teamInPosession == hometeam) {
			if(field.isTouchdown() && !turnover) {
				System.out.println(teamInPosession + " scored Touchdown");
				homescore +=6;
				System.out.println(hometeam + " " + homescore + " : " + roadscore + " " + roadteam);
				if(PlayGenerator.extraPoint()) {
					homescore++;
					System.out.println(teamInPosession + " made XP");
				} else {
					System.out.println(teamInPosession + " missed XP");
				}
				field.setKickoffPosition(roadteam);
			} else if(isFieldGoal) {
				homescore +=3;
				field.setKickoffPosition(roadteam);
			}
			try {
				if(lastPlay.getTurnoverType() == TurnoverType.Punt && field.getFieldposition() >= 110) {
					field.setFieldpostion(90);
				}
			}
			catch (NullPointerException e) {
				System.err.println(lastPlay);
				e.printStackTrace();
			}
			if(timeLeft || lastPlay.getPlayType() == PlayType.Punt) {
				down = 1;
				yardsToGo = 10;
				teamInPosession = roadteam;
			}
		} else {
			if(field.isTouchdown() && !turnover) {
				System.out.println(teamInPosession + " scored Touchdown");
				roadscore += 6;
				System.out.println(hometeam + " " + homescore + " : " + roadscore + " " + roadteam);
				if(PlayGenerator.extraPoint()) {
					roadscore++;
					System.out.println(teamInPosession + " made XP");
				} else {
					System.out.println(teamInPosession + " missed XP");
				}
				field.setKickoffPosition(hometeam);
			} else if(isFieldGoal) {
				roadscore +=3;
				field.setKickoffPosition(hometeam);
			}
			try {
				if(lastPlay.getTurnoverType() == TurnoverType.Punt && field.getFieldposition() <= 10) {
					field.setFieldpostion(30);
				}
			}
			catch (NullPointerException e) {
				System.err.println(lastPlay);
				e.printStackTrace();
			}
			if(timeLeft || lastPlay.getPlayType() == PlayType.Punt) {
				down = 1;
				yardsToGo = 10;
				teamInPosession = hometeam;
			}
		}
	}

	private static void setHometeam(Team team) {
		hometeam = team;

	}

	public static Field getField() {
		return field;
	}

	public static Team getHometeam() {
		return hometeam;
	}

	private static void setRoadteam(Team team) {
		roadteam = team;

	}

	public static Team getRoadteam() {
		return roadteam;
	}

	public static Gameclock getGameclock() {
		return gameclock;
	}

	public static void adjustGameclock(long seconds) {
		gameclock.subtractPlayLength(seconds);
	}

}
