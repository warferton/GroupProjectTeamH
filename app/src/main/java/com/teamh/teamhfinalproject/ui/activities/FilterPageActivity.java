package com.teamh.teamhfinalproject.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.teamh.teamhfinalproject.R;
import com.teamh.teamhfinalproject.ui.activities.GiftListPageActivity;

public class FilterPageActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        try { this.getSupportActionBar().hide(); }
            catch (NullPointerException e){ e.printStackTrace();}

        //finish button which redirects user to GifltList layout
        Button finishButton = (Button)findViewById(R.id.finish_button);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGiftListPageActivity();
            }
        });

        seekBar = findViewById(R.id.seekBar_budget);
        seekBar.setProgress(5);
        seekBar.incrementProgressBy(5);
        seekBar.setMax(1995);
        textView = findViewById(R.id.user_budget);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress + 5;
                textView.setText("$ " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //class for starting GiftListPageActivity to open GiftList layout
    public void openGiftListPageActivity(){
        Intent intent = new Intent(this, GiftListPageActivity.class);
        startActivity(intent);
    }

}