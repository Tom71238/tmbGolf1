package com.tbrey1gmail.tmbgolf.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tbrey1gmail.tmbgolf.Data.fake_gcName;
import com.tbrey1gmail.tmbgolf.R;

import java.util.ArrayList;
import java.util.List;

public class SelectGolfCourse extends AppCompatActivity {
    private static final String TAG = SelectGolfCourse.class.getSimpleName();

    ArrayList<String> gcNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_gc);

        //Create N instances of golf course name
        ArrayList<fake_gcName> gcNames = fake_gcName.createFakeGcNamesList(20);

        // Lookup the recyclerview in activity layout
        RecyclerView rv_gcNames = (RecyclerView) findViewById(R.id.rcvSelectGolfCourse_activity);

        // Create adapter passing in the sample user data
        gcNameAdapter adapter = new gcNameAdapter(this, gcNames);

        // Attach the adapter to the recyclerview to populate items
        rv_gcNames.setAdapter(adapter);
        // Set layout manager to position the items
        rv_gcNames.setLayoutManager(new LinearLayoutManager(this));
    }

    // Create the basic adapter extending from RecyclerView.Adapter
    // Note that we specify the custom ViewHolder which gives us access to our views
    public class gcNameAdapter extends RecyclerView.Adapter<gcNameAdapter.ViewHolder> {


        // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        public class ViewHolder extends RecyclerView.ViewHolder {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row
            public TextView tv_gcName;


            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public ViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);

                tv_gcName = (TextView) itemView.findViewById(R.id.tv_gcName);
                //messageButton = (Button) itemView.findViewById(R.id.message_button);
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
            fake_gcName gcName = m_gcNames.get(position);

            // Set item views based on your views and data model
            TextView textView = viewHolder.tv_gcName;
            textView.setText(gcName.getName());
//            Button button = viewHolder.messageButton;
//            button.setText(contact.isOnline() ? "Message" : "Offline");
//            button.setEnabled(contact.isOnline());
        }

        @Override
        public int getItemCount() {
            return m_gcNames.size();
        }

        // Store a member variable for the contacts
        private List<fake_gcName> m_gcNames;
        // Store the context for easy access
        private Context mContext;
        // Pass in the contact array into the constructor
        public gcNameAdapter(Context context, List<fake_gcName> contacts) {
            m_gcNames = contacts;
            mContext = context;
        }

        // Easy access to the context object in the recyclerview
        private Context getContext() {
            return mContext;
        }


    }
}