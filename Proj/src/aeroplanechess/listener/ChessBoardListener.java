package aeroplanechess.listener;

import aeroplanechess.model.ChessBoard;
import aeroplanechess.model.ChessBoardLocation;
import aeroplanechess.model.ChessPiece;

public interface ChessBoardListener extends Listener {
    void onChessPiecePlace(ChessBoardLocation location, ChessPiece piece);

    void onChessPieceRemove(ChessBoardLocation location);

    void onChessBoardReload(ChessBoard board);
}
