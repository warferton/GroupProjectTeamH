 package com.teamh.teamhfinalproject.ui.activities;
 
 import androidx.appcompat.app.AppCompatActivity;
 
 import android.content.Intent;
 import android.os.Bundle;
 import android.util.Log;
 import android.view.View;
 import android.widget.AdapterView;
 import android.widget.ArrayAdapter;
 import android.widget.Button;
 import android.widget.RadioGroup;
 import android.widget.SeekBar;
 import android.widget.Spinner;
 import android.widget.TextView;
 import android.widget.Toast;

 import com.teamh.teamhfinalproject.R;

 import java.util.ArrayList;
 import java.util.List;

 public class FilterPageActivity extends AppCompatActivity {

     //declaring textView
     private TextView textView;
     private Spinner catSpinner;
     private double max_price;
     //finish button
     Button finishButton;
 
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_filter);

         //spinner with categories
         catSpinner = findViewById(R.id.category_spinner);
         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                 R.array.spinner_array, android.R.layout.simple_spinner_item);
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         catSpinner.setAdapter(adapter);

         //removing the header
         try { this.getSupportActionBar().hide(); }
             catch (NullPointerException e){ e.printStackTrace();}
 
          //finish button which redirects user to GifltList layout
         Button finishButton = findViewById(R.id.finish_button);
         finishButton.setOnClickListener(v -> openGiftListPageActivity());

         //seekbar with budget
         //seekbar for budget
         SeekBar seekBar = findViewById(R.id.seekBar_budget);
         seekBar.setProgress(5);
         seekBar.incrementProgressBy(1);
         seekBar.setMax(500);
         textView = findViewById(R.id.user_budget);
 
         seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 progress = progress + 1;
                 max_price = progress;
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

 
      //method for starting GiftListPageActivity to open GiftList layout
     public void openGiftListPageActivity() {
         //variables of filters
         RadioGroup gender = findViewById(R.id.gender_group);
         RadioGroup age = findViewById(R.id.age_group);
         RadioGroup closeness = findViewById(R.id.closeness_group);
 
         int checkedId_1 = gender.getCheckedRadioButtonId();
         int checkedId_2 = age.getCheckedRadioButtonId();
         int checkedId_3 = closeness.getCheckedRadioButtonId();

         finishButton = findViewById(R.id.finish_button);
 
         if (checkedId_1 == -1 && checkedId_2 == -1 && checkedId_3 == -1) {
             // if gender, age and closeness were not checked
             Toast.makeText(getApplicationContext(), "Please apply all filters", Toast.LENGTH_SHORT).show();
         }

         else
             {
             Intent intent = new Intent(this, LoadingPage.class);
             intent.putExtra("category", catSpinner.getSelectedItem().toString());
             intent.putExtra("maxPrice", String.valueOf(max_price));
             startActivity(intent);
         }
     }
 }