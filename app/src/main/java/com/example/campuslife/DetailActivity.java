package com.example.campuslife;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * DetailActivity displays the information for the topic that was selected on
 * the main screen (Facilities, Events, Clubs or Support).
 *
 * <p>The activity is started with an explicit Intent that carries the index of
 * the topic. That index is used to look up the title, the content text and the
 * icon name in the three arrays defined in res/values/arrays.xml, so the
 * activity needs no conditional logic to decide which content to show
 * (Part H).</p>
 */
public class DetailActivity extends AppCompatActivity {

    /** Key of the int extra that identifies the selected topic. */
    public static final String EXTRA_TOPIC_INDEX =
            "com.example.campuslife.extra.TOPIC_INDEX";

    /** Fallback used when the activity is somehow started without an index. */
    private static final int DEFAULT_TOPIC_INDEX = 0;

    /** Resource type of the topic icons. */
    private static final String DRAWABLE_RESOURCE_TYPE = "drawable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // The unique identifier that was passed through the Intent.
        int topicIndex = getIntent().getIntExtra(EXTRA_TOPIC_INDEX, DEFAULT_TOPIC_INDEX);

        populateUi(topicIndex);
    }

    /**
     * Fills the header and the body of the screen with the data of one topic.
     *
     * @param topicIndex index of the topic in the arrays.xml arrays
     */
    private void populateUi(int topicIndex) {
        String[] labels = getResources().getStringArray(R.array.topic_labels);
        String[] titles = getResources().getStringArray(R.array.string_array_titles);
        String[] contents = getResources().getStringArray(R.array.string_array_content);
        String[] imageNames = getResources().getStringArray(R.array.string_array_images);

        // Header: short name + matching white icon.
        TextView headerView = findViewById(R.id.textViewDetailHeader);
        headerView.setText(labels[topicIndex]);

        ImageView iconView = findViewById(R.id.imageViewDetailIcon);
        iconView.setImageResource(getDrawableId(imageNames[topicIndex].trim()));

        // Body: full title and the dummy content stored in arrays.xml.
        TextView titleView = findViewById(R.id.textViewDetailTitle);
        titleView.setText(titles[topicIndex].trim());

        TextView contentView = findViewById(R.id.textViewDetailContent);
        contentView.setText(formatContent(contents[topicIndex]));
    }

    /**
     * Resolves a drawable resource name (for example "img_btn_events") to its
     * integer resource id. The name is read from arrays.xml, which is why the
     * lookup has to be done by name instead of with R.drawable.*.
     *
     * @param drawableName name of the drawable resource
     * @return the resource id, or 0 when no such drawable exists
     */
    private int getDrawableId(String drawableName) {
        return getResources().getIdentifier(drawableName,
                DRAWABLE_RESOURCE_TYPE, getPackageName());
    }

    /**
     * Tidies the content text that is stored in arrays.xml.
     *
     * <p>The array items are written with "\n" escape sequences and with the
     * indentation of the XML file itself, and Android keeps that indentation,
     * which would otherwise show up as an uneven left margin. The text is
     * therefore normalised with a few regular expressions: escapes become real
     * line breaks, the indentation and trailing spaces of every line are
     * removed, and empty lines are collapsed (see Figures 6 to 9).</p>
     *
     * @param rawContent the string exactly as it is stored in arrays.xml
     * @return the text ready to be displayed in the TextView
     */
    private String formatContent(String rawContent) {
        return rawContent
                // Turn any literal "\n" escape that survived the resource build
                // into a real line break.
                .replace("\\n", "\n")
                // Drop the indentation that the XML file adds at the start of each line.
                .replaceAll("(?m)^[\\t ]+", "")
                // Drop trailing spaces so the text does not wrap early.
                .replaceAll("(?m)[\\t ]+$", "")
                // Collapse the empty lines created by the blank lines in arrays.xml.
                .replaceAll("\\n+", "\n")
                .trim();
    }
}
