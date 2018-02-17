package com.tbrey1gmail.tmbgolf.View;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tbrey1gmail.tmbgolf.Data.aGolfCourse;
import com.tbrey1gmail.tmbgolf.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class gcDetailsWithPlayers extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,AdapterView.OnItemSelectedListener {
    private static final String TAG = gcDetailsWithPlayers.class.getSimpleName();
    private View btnPickDate;
    private View btnPickTime;
    private TextView gcName;
    private TextView dateOfRound;
    private Spinner teesPlayedSpinner;
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
    private Integer gcPos;
    private aGolfCourse selectedGolfCourse = new aGolfCourse();
    //ArrayList<String> gcTeeList;


    //private ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gc_details_with_players);
        //Defaults
        ArrayList<String> tmp = new ArrayList<>();
        tmp.add("Black");
        tmp.add("White");
        tmp.add("Red");
        tmp.add("Gold");
        selectedGolfCourse.setTeeList(tmp);

        gcName = (TextView) findViewById(R.id.tvCourseNameID);
        dateOfRound = (TextView) findViewById(R.id.etDateID);


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


        //gcName.setText(s,TextView.BufferType.EDITABLE);
        gcName.setText(getIntent().getStringExtra("gcSelected"));
        final String gcPos = getIntent().getStringExtra("gcPos");
        final Integer gcPos_int = Integer.parseInt(gcPos);
        DatabaseReference mFBref_golfCourseInfoList;
        DatabaseReference mFBref_golfCoursesList;

        final ArrayAdapter<String> spinDataAdapter = new ArrayAdapter<String> (this,android.R.layout.simple_spinner_item,selectedGolfCourse.getTeeList());


        //Read Golf course names
        mFBref_golfCourseInfoList = FirebaseDatabase.getInstance().getReference("golfCourseInfoList");
        mFBref_golfCoursesList = mFBref_golfCourseInfoList.child("golfCoursesList");


        mFBref_golfCoursesList.addValueEventListener(new ValueEventListener()  {
            Integer key;
            @Override
            //Note: The db was manually manufactured. When the value did not have a decimal point vs when it did changed the type in firebase
            //This caused me to convert them all to strings
            public void onDataChange(DataSnapshot dataSnapshot) {
                selectedGolfCourse.setName(dataSnapshot.child(gcPos).child("Name").getValue().toString());

                selectedGolfCourse.setPhoneNum(dataSnapshot.child(gcPos).child("PhoneNum").getValue().toString());

                ArrayList<String> TeeList = (ArrayList<String>) dataSnapshot.child(gcPos).child("TeeList").getValue();
                selectedGolfCourse.setTeeList(TeeList);

                ArrayList<Long> ParList = (ArrayList<Long>) dataSnapshot.child(gcPos).child("ParList").getValue();
                selectedGolfCourse.setParList(IntLongDouble_toString(ParList));

                ArrayList<Long> HdcList = (ArrayList<Long>) dataSnapshot.child(gcPos).child("HdcList").getValue();
                selectedGolfCourse.setHdcList(IntLongDouble_toString(HdcList));

                ArrayList<Double> TeeRatingList = (ArrayList<Double>) dataSnapshot.child(gcPos).child("TeeRatingList").getValue();
                selectedGolfCourse.setTeeRatingList(IntLongDouble_toString(TeeRatingList));

                ArrayList<Long> TeeSlopeList = (ArrayList<Long>) dataSnapshot.child(gcPos).child("TeeSlopeList").getValue();
                selectedGolfCourse.setTeeSlopeList(IntLongDouble_toString(TeeSlopeList));

                Log.d(TAG, "sFB returning data: electedGolfCourse=" + selectedGolfCourse.getTeeList());
                spinDataAdapter.clear();
                spinDataAdapter.addAll(selectedGolfCourse.getTeeList());

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
            }
        });



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

        //Spinner logic to select tees on this golf course
        Log.d(TAG, "Setting up spinner in OnCreate" );

        //Spinner element
        teesPlayedSpinner = (Spinner) findViewById(R.id.spinTeesPlayedID);

        //Set click listener
       teesPlayedSpinner.setOnItemSelectedListener(this);

        //Spinner drop down elements
        ArrayList<String > gcTeeList = selectedGolfCourse.getTeeList();

        // Create an ArrayAdapter using the string array and a default spinner layout (with gcTeeList array data)
//        ArrayAdapter<String> spinDataAdapter = new ArrayAdapter<String> (this,android.R.layout.simple_spinner_item,gcTeeList);

        // Specify the layout to use when the list of choices appears
        spinDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        // Attach data adapter to spinner
        teesPlayedSpinner.setAdapter(spinDataAdapter);
        Log.d(TAG, "Completed set up spinner in OnCreate" );

    }//end OnCreate

    public ArrayList<String> IntLongDouble_toString(ArrayList<?> aryList){
        ArrayList<String> strAryList = new ArrayList<>();
        for (int i=0; i<aryList.size();i++ ) {
            if (aryList.get(i) instanceof Double) {
                strAryList.add(String.valueOf(aryList.get(i)));
            }
            if (aryList.get(i) instanceof Long) {
                strAryList.add(String.valueOf(aryList.get(i)));
            }
            if (aryList.get(i) instanceof Integer) {
                strAryList.add(String.valueOf(aryList.get(i)));
            }
            if (aryList.get(i) instanceof String) {
                strAryList.add(String.valueOf(aryList.get(i)));
            }
        }
        return strAryList;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int yyyy, int mm, int dd) {
        dateOfRound.setText(mm+"/"+dd+"/"+yyyy);
    }
    

    //Set up the spinner Listener
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        // On selecting a spinner item
        String item = parent.getItemAtPosition(pos).toString();
        Log.d(TAG, "Tee selected is "+ item );
        ArrayList<String> tmp = new ArrayList<String>();
        tmp = selectedGolfCourse.getTeeSlopeList();
        courseSlope.setText(tmp.get(pos));

        tmp = selectedGolfCourse.getTeeRatingList();
        courseRating.setText(tmp.get(pos));

        Toast.makeText(parent.getContext(), "OnItemSelectedListener : " + item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(parent.getContext(),"NothingSelected : ",Toast.LENGTH_SHORT).show();
    }
}
