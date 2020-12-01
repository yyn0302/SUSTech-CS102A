package aeroplanechess.listener;

import aeroplanechess.model.*;

public interface ChessBoardListener extends Listener {
    void onChessPiecePlace(ChessBoardLocation location, ChessPiece piece);

    void onChessPieceRemove(ChessBoardLocation location);

    void onChessBoardReload(ChessBoard board);
}
