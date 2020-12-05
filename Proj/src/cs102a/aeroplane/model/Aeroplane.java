package cs102a.aeroplane.model;

import cs102a.aeroplane.presets.PlaneState;
import cs102a.aeroplane.util.Dice;
import cs102a.aeroplane.util.Player;

public class Aeroplane implements AeroplaneInterface {

    private int STATE;


    @Override
    public void rollAndConfirmStep() {
        int[] rollResult = {Dice.roll(), Dice.roll()};
        if (STATE == PlaneState.IN_HANGAR) {
            if (rollResult[0] == 6 || rollResult[1] == 6) {
                STATE = PlaneState.WAITING;
                rollAndConfirmStep();
            } else return;
        } else if (STATE == PlaneState.WAITING || STATE == PlaneState.ON_BOARD) {
            setPath(Player.askPlayerStep(rollResult));
        }
    }

    @Override
    public void setPath(int steps) {

    }

    @Override
    public int isSameColorGrid(int index) {
        return 0;
    }

    @Override
    public int isJetGrid(int index) {
        return 0;
    }

    @Override
    public void move() {

    }

    @Override
    public boolean isInAirport() {
        return false;
    }

    @Override
    public int getStepFromIndex(int index) {
        return 0;
    }

    @Override
    public float getXFromIndex(int index) {
        return 0;
    }

    @Override
    public float getYFromIndex(int index) {
        return 0;
    }

    @Override
    public void getReadyToFly() {

    }

    @Override
    public void crackByPlane() {

    }

    @Override
    public void resetGame() {

    }

    @Override
    public void finishTask() {

    }
}
