package com.live.lldxo.Strategy.WinningStartegy;

import com.live.lldxo.Models.Board;
import com.live.lldxo.Models.Move;

public interface PlayerWinning {

    boolean checkWinner(Move move , Board board);
}
