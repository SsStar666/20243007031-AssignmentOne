package com.example.campuslife;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView headerView = findViewById(R.id.textViewDetailHeader);
        headerView.setText(R.string.facilities);

        ImageView iconView = findViewById(R.id.imageViewDetailIcon);
        iconView.setImageResource(R.drawable.img_btn_facilities);

        TextView titleView = findViewById(R.id.textViewDetailTitle);
        titleView.setText(R.string.detail_placeholder_title);

        TextView contentView = findViewById(R.id.textViewDetailContent);
        contentView.setText(R.string.detail_placeholder_content);
    }
}
