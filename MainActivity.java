package com.example.myapplication15;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView seekBarValueTextView;
    private TextView textView;
    private static final String PREF_NAME = "MyPrefs";
    private static final String SEEKBAR_PROGRESS = "seekbar_progress";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        seekBarValueTextView = findViewById(R.id.seekBarValueTextView);
        textView = findViewById(R.id.textView);

        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        int savedProgress = prefs.getInt(SEEKBAR_PROGRESS, 0);
        seekBar.setProgress(savedProgress);
        seekBarValueTextView.setText(String.valueOf(savedProgress));
        textView.setText(String.valueOf(savedProgress));


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueTextView.setText("Progress: " + String.valueOf(progress));
                textView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                SharedPreferences.Editor editor = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();
                editor.putInt(SEEKBAR_PROGRESS, progress);
                editor.apply();
            }
        });
    }
}
