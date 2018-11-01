package ca.mcgill.ecse321.rideshare9.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;

import ca.mcgill.ecse321.rideshare9.HttpUtils;
import ca.mcgill.ecse321.rideshare9.R;
import cz.msebera.android.httpclient.Header;

public class JourneyViewerActivity extends AppCompatActivity {
    // journey sent in a bundle to the activity when it starts
    private Journey journey;
    // stops recyclerView
    private RecyclerView rvStops;
    private StopsAdapter adapter;
    private LinearLayoutManager layoutManager;
    // Views
    private TextView adTitle;
    private TextView adStartLocation;
    private TextView adStartDateAndTime;
    private Button adJoinButton;

    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_viewer);

        journey = getIntent().getParcelableExtra("advertisement_data");

        rvStops = findViewById(R.id.rvStopsForJourney);
        layoutManager = new LinearLayoutManager(this);
        rvStops.setLayoutManager(layoutManager);
        adapter = new StopsAdapter(journey.getStops());
        rvStops.setAdapter(adapter);

        // get the TextViews for advertisement_viewer
        adTitle = findViewById(R.id.advertisement_viewer_title);
        adStartLocation = findViewById(R.id.advertisement_viewer_startsAtLocation);
        adStartDateAndTime = findViewById(R.id.advertisement_viewer_startsAtDate);

        // set the view text for advertisement_viewer
        adTitle.setText(journey.getTitle());
        adStartLocation.setText(journey.getStartLocation());
        adStartDateAndTime.setText(journey.getStartTime());

        // get the Join Trip button
        adJoinButton = findViewById(R.id.advertisement_viewer_join_button);

        // set onClickListener
        adJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "works", Toast.LENGTH_SHORT).show();
                HttpUtils.post("/map/add-map?adv_id=" + journey.getId(), null, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(context, "Error joining trip", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        if (responseString.equals("Success")) {
                            Toast.makeText(context, "Enjoy your journey!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Last seat gone! :(", Toast.LENGTH_LONG).show();
                        }
                        finish();
                    }
                });
            }
        });
    }
}