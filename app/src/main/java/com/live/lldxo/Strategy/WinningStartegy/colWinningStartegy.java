package com.live.lldxo.Strategy.WinningStartegy;

import com.live.lldxo.Models.Board;
import com.live.lldxo.Models.Move;
import com.live.lldxo.Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class colWinningStartegy implements PlayerWinning{
    private Map<Integer , Map<Symbol,Integer>> colMaps = new HashMap<>();
    @Override
    public boolean checkWinner(Move move, Board board) {
        int col  = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if(!colMaps.containsKey(col)) {
            colMaps.put(col,new HashMap<>());
        }
        Map<Symbol,Integer> currcolMap = colMaps.get(col);
        currcolMap.put(symbol,currcolMap.getOrDefault(symbol,0)+1);

        return currcolMap.get(symbol) == board.getSize();
    }
}
