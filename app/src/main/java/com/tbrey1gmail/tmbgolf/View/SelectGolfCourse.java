package com.tbrey1gmail.tmbgolf.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tbrey1gmail.tmbgolf.Data.GCnames;
import com.tbrey1gmail.tmbgolf.R;

public class SelectGolfCourse extends AppCompatActivity {
    private static final String TAG = SelectGolfCourse.class.getSimpleName();
    private DatabaseReference mFBref_root;
    private DatabaseReference mFBref_message;
    private DatabaseReference mFBref_goldCoursesList;
    private DatabaseReference mFBref_playersRoundList;
    public GCnames mGCnames = new GCnames();

    gcNameAdapter adapter = new gcNameAdapter(this, mGCnames);



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DatabaseReference mFBref_golfCourseInfoList;
        DatabaseReference mFBref_golfCourseNamesList;
        DatabaseReference mFBref_testme;
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        super.onCreate(savedInstanceState);

        //ToDo: Remove dummy data for GCnames
        //Create N instances of golf course name
        //final ArrayList<fake_gcName> gcNames = fake_gcName.createFakeGcNamesList(20);

        //Read Golf courses

        mFBref_golfCourseInfoList = FirebaseDatabase.getInstance().getReference("golfCourseInfoList");
        mFBref_golfCourseNamesList = mFBref_golfCourseInfoList.child("golfCourseNamesList");
        mFBref_testme = FirebaseDatabase.getInstance().getReference("testme");
        Log.d(TAG,"ref built:");


        mFBref_golfCourseNamesList.addValueEventListener(new ValueEventListener()  {
            //GCnames mGCnames = new GCnames();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child  : dataSnapshot.getChildren()) {
                    String what = child.getKey().toString();
                    String who = child.getValue().toString();
                    mGCnames.addGCname(who);
                    Log.d(TAG,"dataSnapshot what, who =:" + what + "," + who);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
        Log.d(TAG,"dataSnapshot got here");

        setContentView(R.layout.activity_list_gc);

        // Lookup the recyclerview in activity layout
        RecyclerView rv_gcNames = (RecyclerView) findViewById(R.id.rcvSelectGolfCourse_activity);

        // Create adapter passing in the sample user data
        //gcNameAdapter adapter = new gcNameAdapter(this, mGCnames);

        // Attach the adapter to the recyclerview to populate items
        rv_gcNames.setAdapter(adapter);
        // Set layout manager to position the items
        rv_gcNames.setLayoutManager(new LinearLayoutManager(this));
        // add the decoration to the recyclerView
        SeparatorDecoration decoration = new SeparatorDecoration(this, Color.GRAY, 1.5f);
        rv_gcNames.addItemDecoration(decoration);
    }
    // Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
    public class gcNameAdapter extends RecyclerView.Adapter<gcNameAdapter.ViewHolder> {

        // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row
            public TextView tv_gcName;

            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public ViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);
                itemView.setOnClickListener(this);

                tv_gcName = (TextView) itemView.findViewById(R.id.tv_gcName);
                //messageButton = (Button) itemView.findViewById(R.id.message_button);
            }

            @Override
            public void onClick(View v) {

                int pos = getAdapterPosition();
                String gcSelected = mGCnames.getGCname((pos));
                Toast.makeText(SelectGolfCourse.this,gcSelected, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SelectGolfCourse.this, gcDetailsWithPlayers.class);
                intent.putExtra("gcSelected", gcSelected);
                startActivity(intent);
            }
        }

        //        Every adapter has three primary methods:
//            onCreateViewHolder to inflate the item layout and create the holder,
//            onBindViewHolder to set the view attributes based on the data and
//            getItemCount to determine the number of items. We need to implement all three to finish the adapter:
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View contactView = inflater.inflate(R.layout.item_gc, parent, false);

            // Return a new holder instance
            ViewHolder viewHolder = new ViewHolder(contactView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            // Get the data model based on position
//            fake_gcName gcName = m_gcNames.get(position);
            String gcName = mGCnames.getGCname(position);

            // Set item views based on your views and data model
            TextView textView = viewHolder.tv_gcName;
            textView.setText(gcName);
//            Button button = viewHolder.messageButton;
//            button.setText(contact.isOnline() ? "Message" : "Offline");
//            button.setEnabled(contact.isOnline());
        }

        @Override
        public int getItemCount() {
            return mGCnames.lengthOfArray();
        }

        // Store a member variable for the contacts
        private GCnames mGCnames;
        // Store the context for easy access
        private Context mContext;
        // Pass in the contact array into the constructor
        public gcNameAdapter(Context context, GCnames gcNames) {
            mGCnames = gcNames;
            mContext = context;
        }

        // Easy access to the context object in the recyclerview
        private Context getContext() {
            return mContext;
        }

    }
}