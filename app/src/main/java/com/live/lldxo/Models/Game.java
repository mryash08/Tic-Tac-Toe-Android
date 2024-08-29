package com.live.lldxo.Models;



import com.live.lldxo.Builder.GameBuilder;
import com.live.lldxo.Enum.CellState;
import com.live.lldxo.Enum.GameState;
import com.live.lldxo.Strategy.WinningStartegy.PlayerWinning;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Board board;
    private List<Player> players;

    private GameState gameState;
    private Player Winner;
    private int nextMovePlayer;
    private List<Move> moves;

    private List<PlayerWinning> winningsStrategies;
    public Game(GameBuilder gameBuilder){
           this.board = gameBuilder.getBoard();
           this.gameState = gameBuilder.getGameState();
           this.nextMovePlayer = gameBuilder.getNextMovePlayer();
           this.moves = gameBuilder.getMoves();
           this.winningsStrategies = gameBuilder.getWinningsStrategies();
           this.players = gameBuilder.getPlayers();
    }

    public static GameBuilder getGameBuilder(int dimension, List<Player> players, List<PlayerWinning> winningsStrategies){
        return new GameBuilder(dimension,players,winningsStrategies);
    }
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return Winner;
    }

    public void setWinner(Player winner) {
        Winner = winner;
    }

    public int getNextMovePlayer() {
        return nextMovePlayer;
    }

    public void setNextMovePlayer(int nextMovePlayer) {
        this.nextMovePlayer = nextMovePlayer;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public void printBoard(){
           board.displayBoard();
    }

    public void undo() {
    }

    private boolean checkWinner(Board board , Move move){
        for(PlayerWinning winning : winningsStrategies){
            if(winning.checkWinner(move,board)) return true;
        }
        return false;
    }

    public boolean makeMove(Board board,int row,int col) {
         Player currentPlayer = players.get(nextMovePlayer);
         Move move = currentPlayer.makeMove(board,row,col);
         if(!validateMove(move)) {
             System.out.println("Invalid mpve");
             return false;
         }
        int row1 = move.getCell().getRow();
        int col1 = move.getCell().getCol();
        Cell cell = board.getBoard().get(row1).get(col1);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);
        Move finalMove = new Move(cell,currentPlayer);
        moves.add(finalMove);

        nextMovePlayer += 1;
        nextMovePlayer %= players.size();

        if(checkWinner(board,finalMove)){
            gameState = GameState.ENDED;
            Winner = currentPlayer;
        }else if(moves.size() == board.getSize()*board.getSize()){
            gameState = GameState.DRAW;
        }

        return  true;
    }

    private boolean validateMove(Move move){
           int row = move.getCell().getRow();
           int col = move.getCell().getCol();

        return row < board.getSize() && row >= 0 && col < board.getSize() && col >= 0 &&
                board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }
}
