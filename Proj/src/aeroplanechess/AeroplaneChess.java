package aeroplanechess;

import aeroplanechess.controller.GameController;
import aeroplanechess.model.ChessBoard;
import aeroplanechess.view.ChessBoardComponent;
import aeroplanechess.view.GameFrame;

import javax.swing.*;

public class AeroplaneChess {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.win.uiScaleX", "96dpi");
        System.setProperty("sun.java2d.win.uiScaleY", "96dpi");
        SwingUtilities.invokeLater(() -> {
//            ChessBoardComponent chessBoardComponent = new ChessBoardComponent(760, 40, 2);
//            ChessBoardComponent chessBoardComponent = new ChessBoardComponent(760, 13, 6);
            ChessBoard chessBoard = new ChessBoard(13, 6);
            GameController controller = new GameController(chessBoardComponent, chessBoard);

            GameFrame mainFrame = new GameFrame(controller);
            mainFrame.add(chessBoardComponent);
            mainFrame.setVisible(true);
            controller.initializeGame();
        });
    }
}
