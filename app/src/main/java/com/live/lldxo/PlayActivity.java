package com.live.lldxo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.live.lldxo.Controller.GameController;
import com.live.lldxo.Enum.BotDifficultyLevel;
import com.live.lldxo.Enum.GameState;
import com.live.lldxo.Enum.PlayerType;
import com.live.lldxo.Models.Bot;
import com.live.lldxo.Models.Game;
import com.live.lldxo.Models.Player;
import com.live.lldxo.Models.Symbol;
import com.live.lldxo.Strategy.PlayingStartegy.EasyBotPlaying;
import com.live.lldxo.Strategy.WinningStartegy.PlayerWinning;
import com.live.lldxo.Strategy.WinningStartegy.colWinningStartegy;
import com.live.lldxo.Strategy.WinningStartegy.diagonalWinningStartegy;
import com.live.lldxo.Strategy.WinningStartegy.rowWinningStartegy;

import java.util.ArrayList;
import java.util.List;

public class PlayActivity extends AppCompatActivity {

    TextView statusTextView;
    GridLayout boardGridLayout;
    Button resetButton;
    Button[][] buttons;
    Game game;
    GameController gameController = new GameController();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        statusTextView = findViewById(R.id.statusTextView);
        boardGridLayout = findViewById(R.id.boardGridLayout);
        resetButton = findViewById(R.id.resetButton);

        String player1Name = getIntent().getStringExtra("Name");
        String player2Name = getIntent().getStringExtra("Name1");
        String player1Symbol = getIntent().getStringExtra("Symbol");
        String player2Symbol = getIntent().getStringExtra("Symbol1");

        List<Player> players = new ArrayList<>();

        players.add(new Player(player1Name,new Symbol(player1Symbol.charAt(0)), PlayerType.HUMAN,1L));
        players.add(new Player(player2Name,new Symbol(player2Symbol.charAt(0)), PlayerType.HUMAN,2L));

        List<PlayerWinning> winningsrategy = new ArrayList<>();
        winningsrategy.add(new colWinningStartegy());
        winningsrategy.add(new diagonalWinningStartegy());
        winningsrategy.add(new rowWinningStartegy());

            game = gameController.startGame(6,players,winningsrategy);
            updateStatus();

       createBoard(boardGridLayout,6);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game = gameController.startGame(6,players,winningsrategy);
                updateStatus();

                createBoard(boardGridLayout,6);
            }
        });

    }
    private void disableButtons() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
    private void createBoard(GridLayout boardGridLayout,int boardSize) {
        boardGridLayout.setRowCount(boardSize);
        boardGridLayout.setColumnCount(boardSize);

        buttons = new Button[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                buttons[i][j] = new Button(this);
                buttons[i][j].setTextSize(24);
                final int row = i;
                final int col = j;
                buttons[i][j].setOnClickListener(v -> onCellClicked(row, col));

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.rowSpec = GridLayout.spec(i, 1f);
                params.columnSpec = GridLayout.spec(j, 1f);
                params.width = 0;
                params.height = 0;

                boardGridLayout.addView(buttons[i][j], params);
            }
        }
    }

    private void onCellClicked(int row, int col) {
        System.out.println("Current State Board");

        gameController.displayBoard(game);
        int currentPlayerIndex =  game.getNextMovePlayer();
        if (gameController.makeMove(game,row,col)) {
            buttons[row][col].setText(String.valueOf(game.getPlayers().get(currentPlayerIndex).getSymbol().getSymbol()+""));
            if(game.getGameState().equals(GameState.DRAW)){
                System.out.println("Game has Drawn");
                statusTextView.setText("It's a draw!");
                disableButtons();
            }else if(game.getGameState().equals(GameState.ENDED)){
                statusTextView.setText(game.getPlayers().get(currentPlayerIndex).getName() + " wins!");
                disableButtons();
                System.out.println("Winner is " + gameController.checkWinner(game).getName());
            }else {
                updateStatus();
            }
        }
    }
    private void updateStatus() {
        statusTextView.setText(game.getPlayers().get(game.getNextMovePlayer()).getName() + "'s turn");
    }
}