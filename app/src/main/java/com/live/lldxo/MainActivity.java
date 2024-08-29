package com.live.lldxo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText name ;
    EditText symbol;
    EditText name1 ;
    EditText symbol1;
    Button playbtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        name1 = findViewById(R.id.name1);
        symbol = findViewById(R.id.symbol);
        symbol1 = findViewById(R.id.symbol1);
        playbtn = findViewById(R.id.btnPlay);

       playbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(!name.getText().toString().isEmpty() && !symbol.getText().toString().isEmpty() && !name1.getText().toString().isEmpty() && !symbol1.getText().toString().isEmpty()){
                   Intent intent = new Intent(getApplicationContext(),PlayActivity.class);
                   intent.putExtra("Name" , name.getText().toString());
                   intent.putExtra("Name1" , name1.getText().toString());
                   intent.putExtra("Symbol" , symbol.getText().toString());
                   intent.putExtra("Symbol1" , symbol1.getText().toString());
                   startActivity(intent);
               }
           }
       });
    }
}