package cs102a.aeroplane.model;

import cs102a.aeroplane.presets.BoardCoordinate;

public class PlayerAgent {
    private final int color;
    private final boolean isComputer;
    private int moveCount;

    private ChessBoard chessBoard;
    public Aeroplane[] planes = new Aeroplane[4];


    public PlayerAgent(int color, boolean isComputer,ChessBoard chessBoard) {
        this.color = color;
        this.isComputer = isComputer;
        this.chessBoard=chessBoard;
        for(int i=0;i<4;i++){
            planes[i]=new Aeroplane(che, color, BoardCoordinate., 0, xOffSet, yOffSet),
        }
    }

    public void move() {
        moveCount++;

    }

    public int getMoveCount() {
        return moveCount;
    }
}
