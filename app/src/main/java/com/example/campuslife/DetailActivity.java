package com.example.campuslife;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    /** Key of the int extra that identifies the selected topic. */
    public static final String EXTRA_TOPIC_INDEX =
            "com.example.campuslife.extra.TOPIC_INDEX";

    private static final int DEFAULT_TOPIC_INDEX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Read the identifier that MainActivity passed through the Intent.
        int topicIndex = getIntent().getIntExtra(EXTRA_TOPIC_INDEX, DEFAULT_TOPIC_INDEX);

        // The screen still shows placeholder text until Part H wires the arrays up.
        TextView headerView = findViewById(R.id.textViewDetailHeader);
        headerView.setText(R.string.facilities);

        ImageView iconView = findViewById(R.id.imageViewDetailIcon);
        iconView.setImageResource(R.drawable.img_btn_facilities);

        TextView titleView = findViewById(R.id.textViewDetailTitle);
        titleView.setText(R.string.detail_placeholder_title);

        TextView contentView = findViewById(R.id.textViewDetailContent);
        contentView.setText(R.string.detail_placeholder_content);

        // topicIndex is used in Part H to pick the matching entry from arrays.xml.
    }
}
