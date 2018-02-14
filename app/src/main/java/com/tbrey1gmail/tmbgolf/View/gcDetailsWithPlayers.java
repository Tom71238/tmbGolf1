package com.tbrey1gmail.tmbgolf.View;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tbrey1gmail.tmbgolf.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class gcDetailsWithPlayers extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

        private View btnPickDate;
        private View btnPickTime;
        private TextView gcName;
        private TextView dateOfRound;
        private TextView teesPlayed;
        private TextView courseSlope;
        private TextView courseRating;
        private TextView Player1_Name;
        private TextView Player1_HdcIdx;
        private TextView Player1_HdcDiff;
        private TextView Player2_Name;
        private TextView Player2_HdcIdx;
        private TextView Player2_HdcDiff;
        private TextView Player3_Name;
        private TextView Player3_HdcIdx;
        private TextView Player3_HdcDiff;
        private TextView Player4_Name;
        private TextView Player4_HdcIdx;
        private TextView Player4_HdcDiff;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_gc_details_with_players);
            gcName = (TextView) findViewById(R.id.tvCourseNameID);
            dateOfRound = (TextView) findViewById(R.id.etDateID);
            teesPlayed = (TextView) findViewById(R.id.etTeesPlayedID);
            courseSlope = (TextView) findViewById(R.id.etSlopeID);
            courseRating = (TextView) findViewById(R.id.etRatingID);
            Player1_Name = (TextView) findViewById(R.id.etDateID);
            Player1_HdcIdx = (TextView) findViewById(R.id.etDateID);
            Player1_HdcDiff = (TextView) findViewById(R.id.etDateID);
            Player2_Name = (TextView) findViewById(R.id.etDateID);
            Player2_HdcIdx = (TextView) findViewById(R.id.etDateID);
            Player2_HdcDiff = (TextView) findViewById(R.id.etDateID);
            Player3_Name = (TextView) findViewById(R.id.etDateID);
            Player3_HdcIdx = (TextView) findViewById(R.id.etDateID);
            Player3_HdcDiff = (TextView) findViewById(R.id.etDateID);
            Player4_Name = (TextView) findViewById(R.id.etDateID);
            Player4_HdcIdx = (TextView) findViewById(R.id.etDateID);
            Player4_HdcDiff = (TextView) findViewById(R.id.etDateID);


            String s = getIntent().getStringExtra("gcSelected");
            //gcName.setText(s,TextView.BufferType.EDITABLE);
            gcName.setText(s);
            //Toast.makeText(this, "Hes back" + s, Toast.LENGTH_SHORT).show();

            Calendar now = Calendar.getInstance();
            Integer yyyy = now.get(Calendar.YEAR);
            Integer mm = now.get(Calendar.MONTH)+1;
            Integer dd = now.get(Calendar.DAY_OF_MONTH);
            dateOfRound.setText(mm+"/"+dd+"/"+yyyy);
            Toast.makeText(this, "Round Date:" + mm+"/"+dd+"/"+yyyy, Toast.LENGTH_LONG).show();


            // btnPickDate.setOnClickListener(new View.OnClickListener() {
            dateOfRound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar now = Calendar.getInstance();
                    DatePickerDialog datepickerdialog = DatePickerDialog.newInstance(
                            gcDetailsWithPlayers.this,
                            now.get(Calendar.YEAR),
                            now.get(Calendar.MONTH) +1,
                            now.get(Calendar.DAY_OF_MONTH)
                    );
                    datepickerdialog.setThemeDark(true); //set dark them for dialog?
                    datepickerdialog.vibrate(true); //vibrate on choosing date?
                    datepickerdialog.dismissOnPause(true); //dismiss dialog when onPause() called?
                    datepickerdialog.showYearPickerFirst(false); //choose year first?
                    datepickerdialog.setAccentColor(Color.parseColor("#9C27A0")); // custom accent color
                    datepickerdialog.setTitle("Select date round was played"); //dialog title
                    datepickerdialog.show(getFragmentManager(), "Datepickerdialog"); //show dialog
                }
            });

        }



    @Override
    public void onDateSet(DatePickerDialog view, int yyyy, int mm, int dd) {
        dateOfRound.setText(mm+"/"+dd+"/"+yyyy);
    }

}
