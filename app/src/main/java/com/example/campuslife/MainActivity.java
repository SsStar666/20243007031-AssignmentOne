package com.example.campuslife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity is the home screen of CampusLife. It shows the banner and the
 * four ImageButtons (Facilities, Events, Clubs and Support).
 *
 * <p>Each button has its own {@code android:onClick} handler. The handler
 * shows a Toast so the click can be verified (Part E) and then starts
 * {@link DetailActivity} with the index of the selected topic (Part G).
 * The index is the "unique identifier" that DetailActivity uses to decide
 * which content to display.</p>
 */
public class MainActivity extends AppCompatActivity {

    // Positions of the topics inside the arrays stored in res/values/arrays.xml.
    // The order matches the order of the buttons on the screen.
    private static final int TOPIC_FACILITIES = 0;
    private static final int TOPIC_EVENTS = 1;
    private static final int TOPIC_CLUBS = 2;
    private static final int TOPIC_SUPPORT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The activity_main layout is automatically replaced by the version in
        // res/layout-land when the device is rotated to landscape.
        setContentView(R.layout.activity_main);
    }

    /** Called when the Facilities image button is tapped. */
    public void onFacilitiesClicked(View view) {
        openTopic(TOPIC_FACILITIES, getString(R.string.facilities));
    }

    /** Called when the Events image button is tapped. */
    public void onEventsClicked(View view) {
        openTopic(TOPIC_EVENTS, getString(R.string.events));
    }

    /** Called when the Clubs image button is tapped. */
    public void onClubsClicked(View view) {
        openTopic(TOPIC_CLUBS, getString(R.string.clubs));
    }

    /** Called when the Support image button is tapped. */
    public void onSupportClicked(View view) {
        openTopic(TOPIC_SUPPORT, getString(R.string.support));
    }

    /**
     * Shows a confirmation Toast and opens the detail screen for the topic.
     *
     * @param topicIndex index of the topic in the arrays.xml arrays
     * @param topicLabel label of the topic, used by the Toast message
     */
    private void openTopic(int topicIndex, String topicLabel) {
        Toast.makeText(this, getString(R.string.toast_topic_clicked, topicLabel),
                Toast.LENGTH_SHORT).show();

        // Explicit Intent: the target activity is named directly, so no
        // intent filter is needed in the manifest.
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_TOPIC_INDEX, topicIndex);
        startActivity(intent);
    }
}
