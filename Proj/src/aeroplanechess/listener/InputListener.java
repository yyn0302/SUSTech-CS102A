package aeroplanechess.listener;

import aeroplanechess.model.ChessBoardLocation;
import aeroplanechess.view.ChessComponent;
import aeroplanechess.view.SquareComponent;

public interface InputListener extends Listener {
    void onPlayerClickSquare(ChessBoardLocation location, SquareComponent component);

    void onPlayerClickChessPiece(ChessBoardLocation location, ChessComponent component);
}
