package cs102a.aeroplane.model;

import cs102a.aeroplane.presets.BoardCoordinate;

public class PlayerAgent {
    private final int color;
    private final boolean isComputer;
    private int moveCount;

    private ChessBoard chessBoard;
    public Aeroplane[] planes;


    public PlayerAgent(int color, boolean isComputer, int moveCount, ChessBoard chessBoard, Aeroplane... planes) {
        this.color = color;
        this.isComputer = isComputer;
        this.moveCount = moveCount;
        this.chessBoard = chessBoard;
        this.planes = planes;
    }

    public void move() {
        moveCount++;

    }

    public int getMoveCount() {
        return moveCount;
    }
}
