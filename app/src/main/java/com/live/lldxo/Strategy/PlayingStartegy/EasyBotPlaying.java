package com.live.lldxo.Strategy.PlayingStartegy;

import com.live.lldxo.Enum.CellState;
import com.live.lldxo.Models.Board;
import com.live.lldxo.Models.Cell;
import com.live.lldxo.Models.Move;

import java.util.List;

public class EasyBotPlaying implements BotPlaying{
    @Override
    public Move makeMove(Board board) {
        List<List<Cell>> listMove = board.getBoard();
        for(int i=0; i<board.getSize(); i++){
            for(int j=0; j<board.getSize(); j++){
                Cell cell = listMove.get(i).get(j);
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(cell,null);
                }
            }
        }
        return null;
    }
}
