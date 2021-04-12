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

     //seekbar for budget
     private SeekBar seekBar;
     //declaring textView
     private TextView textView;
     //variables of filters
     private RadioGroup gender;
     private RadioGroup age;
     private RadioGroup closeness;
     private Spinner catSpinner;
     //String[] categories = {"Beauty", "Books", "Clothing", "Electronics", "Handmade", "Kitchen", "Music", "Sports", "Toys", "Videogames"};
     //category filter
     private List<String> categories;
     private ArrayAdapter categoryAdapter;
     private String categoryString;
     //finish button
     Button finishButton;
 
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_filter);

         //spinner with categories
         catSpinner = (Spinner) findViewById(R.id.category_spinner);
         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                 R.array.spinner_array, android.R.layout.simple_spinner_item);
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         catSpinner.setAdapter(adapter);

         //removing the header
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

         //seekbar with budget
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

 
      //method for starting GiftListPageActivity to open GiftList layout
     public void openGiftListPageActivity() {
         gender = findViewById(R.id.gender_group);
         age = findViewById(R.id.age_group);
         closeness = findViewById(R.id.closeness_group);
 
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
             startActivity(intent);
         }
     }
 }