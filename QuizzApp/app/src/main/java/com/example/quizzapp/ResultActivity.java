package com.example.quizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// ResultActivity.java
public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);
        String userName = getIntent().getStringExtra("USER_NAME");

        TextView congratsTextView = findViewById(R.id.congratsText);
        TextView resultTextView = findViewById(R.id.resultTextView);
        congratsTextView.setText(String.format("Congratulations %s", userName));
        resultTextView.setText(String.format("%d/%d", score, totalQuestions));

        Button newQuizButton = findViewById(R.id.newQuizButton);
        Button finishButton = findViewById(R.id.finishButton);

        newQuizButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("USER_NAME", userName);
            startActivity(intent);
            finish();
        });

        finishButton.setOnClickListener(v -> {
            finishAffinity();
        });
    }
}
