package com.live.lldxo.Controller;

import com.live.lldxo.Enum.GameState;
import com.live.lldxo.Models.Game;
import com.live.lldxo.Models.Player;
import com.live.lldxo.Strategy.WinningStartegy.PlayerWinning;


import java.util.List;

public class GameController {

    public Game startGame(int boardDimension, List<Player> playerList, List<PlayerWinning> winningStrategy) {
        Game game = Game.getGameBuilder(boardDimension, playerList, winningStrategy).build();
        return game;
    }

    public GameState getGame(Game game) {
        return game.getGameState();
    }

    public boolean makeMove(Game game,int row,int col) {
        return game.makeMove(game.getBoard(),row,col);
    }

    public void displayBoard(Game game) {
        game.printBoard();
    }

    public Player checkWinner(Game game) {
        return game.getWinner();
    }

    public void undo(Game game) {
        game.undo();
    }
}
