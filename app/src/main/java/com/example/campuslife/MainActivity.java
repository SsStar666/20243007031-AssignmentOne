package com.example.campuslife;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onFacilitiesClicked(View view) {
        showToast(getString(R.string.facilities));
    }

    public void onEventsClicked(View view) {
        showToast(getString(R.string.events));
    }

    public void onClubsClicked(View view) {
        showToast(getString(R.string.clubs));
    }

    public void onSupportClicked(View view) {
        showToast(getString(R.string.support));
    }

    private void showToast(String topicLabel) {
        Toast.makeText(this, getString(R.string.toast_topic_clicked, topicLabel),
                Toast.LENGTH_SHORT).show();
    }
}
