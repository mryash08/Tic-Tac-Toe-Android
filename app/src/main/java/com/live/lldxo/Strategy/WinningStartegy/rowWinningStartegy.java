package com.live.lldxo.Strategy.WinningStartegy;

import com.live.lldxo.Models.Board;
import com.live.lldxo.Models.Move;
import com.live.lldxo.Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class rowWinningStartegy implements PlayerWinning{

    private Map<Integer , Map<Symbol,Integer>> rowMaps = new HashMap<>();
    @Override
    public boolean checkWinner(Move move, Board board) {
        int row  = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        if(!rowMaps.containsKey(row)) {
            rowMaps.put(row,new HashMap<>());
        }
        Map<Symbol,Integer> currRowMap = rowMaps.get(row);
            currRowMap.put(symbol,currRowMap.getOrDefault(row,0)+1);

        return currRowMap.get(symbol) == board.getSize();
    }
}
