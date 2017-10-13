package testo.roed.simulation.game;

import testo.roed.simulation.Game;
import testo.roed.simulation.model.Team;

public class Field {

	private int position;
	private static final int fieldStart = 0;
	private static final int fieldHalf = 60;
	private static final int fieldEnd = 120;
	private static final int endzoneOne = 10;
	private static final int endzoneTwo = 110;
	private static final int kickoffTouchbackPositionOne = 35;
	private static final int kickoffTouchbackPositionTwo = 85;

    // 0  10  20  30  40  50  60  70  80  90 100 110 120
    // |   | 			      |      			  |   |
    // |   0  10  20  30  40  50  40  30  20  10  0   |
    // |   |                  |                   |   |

	public Field() {

	}

	public void setFieldpostion(int position) {
//		if(position < fieldStart || position > fieldEnd) {
//			throw new IllegalArgumentException("Fieldposition out of bound. It needs to be inbetween 0 and 120");
//		}
		this.position = position;
	}
	public void adjustFieldPosition(int gain, boolean leftToRight) {
		int tmpPos = this.position;
		if(leftToRight) {
			setFieldpostion(tmpPos+gain);
		} else {
			setFieldpostion(tmpPos-gain);
		}
	}

	public void setKickoffPosition(Team teamInPosession) {
		if(teamInPosession.equals(Game.getHometeam())) {
			setFieldpostion(kickoffTouchbackPositionOne);
		} else {
			setFieldpostion(kickoffTouchbackPositionTwo);
		}
	}

	public int getFieldposition() {
		return this.position;
	}
	public boolean isTouchdown() {
		if(position <= endzoneOne || position >= endzoneTwo) {
			return true;
		}
		return false;
	}

	public int yardsToEndzone(boolean leftToRight) {
		if(leftToRight) {
			return endzoneTwo - getFieldposition();
		}
		return getFieldposition() - endzoneOne;
	}
	public int fieldGoalDistance(boolean leftToRight) {
		return yardsToEndzone(leftToRight) + 17;
	}

	public String getFieldposition(boolean leftToRight) {
		if(leftToRight) {
			if(position < fieldHalf) {
				return "Ball on Own " + (position-10);
			} else if(position == fieldHalf) {
				return "Ball on 50";
			} else if (position > fieldHalf) {
				return "Ball on Opp " + (100-(position-10));
			} else {
				return "TD";
			}
		} else {
			if(position > fieldHalf) {
				return "Ball on Own " + (120-(position+10));
			} else if(position == fieldHalf) {
				return "Ball on 50";
			} else if (position < fieldHalf) {
				return "Ball on Opp " + (position-10);
			} else {
				return "TD";
			}
		}
	}

}
