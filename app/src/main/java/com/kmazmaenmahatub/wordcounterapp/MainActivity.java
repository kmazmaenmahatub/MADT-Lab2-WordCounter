package com.kmazmaenmahatub.wordcounterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare variables with EXACT names that match your layout IDs
    private EditText editTextInput;
    private Spinner spinnerMetrics;
    private Button buttonCount;
    private TextView textViewResult;
    private String selectedMetric;

    private final String[] metricsOptions = {"Sentences", "Words", "Characters", "Numbers"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components - MAKE SURE THESE MATCH YOUR LAYOUT FILE IDs
        editTextInput = findViewById(R.id.editTextInput);
        spinnerMetrics = findViewById(R.id.spinnerMetrics);
        buttonCount = findViewById(R.id.buttonCount);
        textViewResult = findViewById(R.id.textViewResult);

        setupSpinner();
        setupButtonClickListener();
    }

    private void setupSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, metricsOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMetrics.setAdapter(adapter);

        spinnerMetrics.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMetric = metricsOptions[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedMetric = metricsOptions[0];
            }
        });
    }

    private void setupButtonClickListener() {
        buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndDisplayResult();
            }
        });
    }

    private void calculateAndDisplayResult() {
        String inputText = editTextInput.getText().toString().trim();

        if (inputText.isEmpty()) {
            Toast.makeText(MainActivity.this, R.string.warning_empty_text, Toast.LENGTH_LONG).show();
            textViewResult.setText(R.string.result_initial_text);
            return;
        }

        int count = 0;

        switch (selectedMetric) {
            case "Sentences":
                count = TextCounter.countSentences(inputText);
                break;
            case "Words":
                count = TextCounter.countWords(inputText);
                break;
            case "Characters":
                count = TextCounter.countChars(inputText);
                break;
            case "Numbers":
                count = TextCounter.countNumbers(inputText);
                break;
            default:
                count = 0;
        }

        String resultMessage = "Number of " + selectedMetric.toLowerCase() + ": " + count;
        textViewResult.setText(resultMessage);
    }
}