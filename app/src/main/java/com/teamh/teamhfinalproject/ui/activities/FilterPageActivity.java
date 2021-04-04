 package com.teamh.teamhfinalproject.ui.activities;
 
 import androidx.appcompat.app.AppCompatActivity;
 
 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.ArrayAdapter;
 import android.widget.Button;
 import android.widget.RadioGroup;
 import android.widget.SeekBar;
 import android.widget.Spinner;
 import android.widget.TextView;
 
 import com.teamh.teamhfinalproject.R;

 public class FilterPageActivity extends AppCompatActivity {
 
     SeekBar seekBar;
     TextView textView;
     RadioGroup gender;
     RadioGroup age;
     RadioGroup closeness;
     Spinner spinner;
     Button finishButton;
 
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_filter);
 
         spinner = (Spinner) findViewById(R.id.category_spinner);
         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                 R.array.spinner_array, android.R.layout.simple_spinner_item);
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         spinner.setAdapter(adapter);
 
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
 
         int price = seekBar.getProgress();
         String gender_male = getString(R.string.male);
         String gender_female = getString(R.string.female);
         String age_kid = getString(R.string.kid);
         String age_young = getString(R.string.young);
         String age_adult = getString(R.string.adult);
         String age_senior = getString(R.string.senior);
         String closeness_friend = getString(R.string.friend_button);
         String closeness_family = getString(R.string.family_button);
         String closeness_work = getString(R.string.work_button);
         String closeness_couple = getString(R.string.couple_button);

         //getting value for spinner
         String text = spinner.getSelectedItem().toString();
 
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
             MessageFilterNotChecked.message(getApplicationContext(), "Please apply all filters");
         }

         else
             {
             Intent intent = new Intent(this, GiftListPageActivity.class);
             startActivity(intent);
         }
     }
 }