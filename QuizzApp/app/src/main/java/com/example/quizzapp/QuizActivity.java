package com.example.quizzapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// QuizActivity.java
public class QuizActivity extends AppCompatActivity {
    private TextView questionTextView;
    private LinearLayout answersLayout;
    private Button submitButton;
    private ProgressBar progressBar;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String userName;
    private Button selectedAnswerButton = null;
    private int correctAnswerIndex;
    private boolean answerSubmitted = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        userName = getIntent().getStringExtra("USER_NAME");
        questionTextView = findViewById(R.id.questionTextView);
        answersLayout = findViewById(R.id.answersLayout);
        submitButton = findViewById(R.id.submitButton);
        progressBar = findViewById(R.id.progressBar);

        // Initialize questions
        questions = new ArrayList<>();
        questions.add(new Question("What is capital of Japan?", Arrays.asList("Seoul", "Beijing", "Tokyo"), 2));
        questions.add(new Question("What is the largest planet in our solar system?", Arrays.asList("Earth", "Jupiter", "Mars"), 1));
        questions.add(new Question("Which programming language is known for its 'Write once, run anywhere' philosophy?", Arrays.asList("Java", "Python", "C++"), 0));
        questions.add(new Question("Which element has the chemical symbol 'O'?", Arrays.asList("Oxygen", "Ozone", "Osmium"), 0));
        questions.add(new Question("Which animal is known as the King of the Jungle?", Arrays.asList("Elephant", "Lion", "Tiger"), 1));

        loadQuestion();

//        on clicking the submit button
        submitButton.setOnClickListener(v -> {
            if(!answerSubmitted){
                // First click - submit answer
                if (selectedAnswerButton != null) {
                    checkAnswer();
                    submitButton.setText("Next");
                    answerSubmitted = true;
                } else {
                    Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
                }
            }else {
                // Second click - go to next question
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    loadQuestion();
                    submitButton.setText("Submit");
                    answerSubmitted = false;
                } else {
                    showResult(); //navigates to result screen
                }
            }
        });
    }

    private void loadQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        questionTextView.setText(currentQuestion.getQuestionText());
        answersLayout.removeAllViews();

        correctAnswerIndex = currentQuestion.getCorrectAnswerIndex();
        selectedAnswerButton = null;

        for (int i = 0; i < currentQuestion.getAnswers().size(); i++) {
            Button answerButton = new Button(this);
            answerButton.setText(currentQuestion.getAnswers().get(i));

            // Adding margins to each button so that we can have clear view
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 0, 16); // left, top, right, bottom (in pixels)
            answerButton.setLayoutParams(params);

            answerButton.setBackgroundColor(Color.LTGRAY);
            answerButton.setEnabled(true);

            answerButton.setOnClickListener(v -> {
                selectedAnswerButton = answerButton;
                // Reset other buttons' backgrounds
                for (int j = 0; j < answersLayout.getChildCount(); j++) {
                    answersLayout.getChildAt(j).setBackgroundColor(Color.LTGRAY);
                }
                answerButton.setTextColor(Color.WHITE); //changes the text to white upon selecting the option
                answerButton.setBackgroundColor(Color.DKGRAY); //changes the background button to dark gray upon selecting the option
            });
            answersLayout.addView(answerButton); //adds above option button the linear layout
        }

        // Update progress bar
        int progress = (int) ((currentQuestionIndex / (float) questions.size()) * 100);
        progressBar.setProgress(progress);
    }

    private void checkAnswer() {
        int selectedIndex = answersLayout.indexOfChild(selectedAnswerButton);
        Button correctButton = (Button) answersLayout.getChildAt(correctAnswerIndex);

        if (selectedIndex == correctAnswerIndex) {
            selectedAnswerButton.setBackgroundColor(Color.GREEN); // Changes correct answer to green
            score++;
        } else {
            selectedAnswerButton.setBackgroundColor(Color.RED); // Changes incorrect button to red
            correctButton.setBackgroundColor(Color.GREEN);
        }

        // Disable all buttons
        for (int i = 0; i < answersLayout.getChildCount(); i++) {
            answersLayout.getChildAt(i).setEnabled(false);
        }
    }

    private void showResult() {
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class); //navigating to the result screen
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL_QUESTIONS", questions.size());
        intent.putExtra("USER_NAME", userName);
        startActivity(intent);
        finish();
    }
}