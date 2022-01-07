package com.karleen.karleenfinalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.karleen.karleenfinalproject.model.Result;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvTitle, tvQuestion;
    EditText etAnswer;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button btnDash, btnDot, btnGen, btnVal, btnClear, btnScore, btnFinish;

    int num1, num2, operator, realAnswer, userAnswer, score, rightCount;
    final int ADD = 0, SUBTRACT = 1, MULTIPLY = 2, DIVIDE = 3;
    String userResult;

    ArrayList<Result> listResults;
    String[] operators = {"+", "-", "*", "/"};

    Random r = new Random();

    boolean periodIsPressedOnce = false;
    final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize(){

        tvTitle = findViewById(R.id.tvTitle);
        tvQuestion = findViewById(R.id.tvQuestion);
        etAnswer = findViewById(R.id.etAnswer);

        btn0 = findViewById(R.id.btn0);
        btn0.setOnClickListener(this);

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);

        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(this);

        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(this);

        btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(this);

        btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(this);

        btn8 = findViewById(R.id.btn8);
        btn8.setOnClickListener(this);

        btn9 = findViewById(R.id.btn9);
        btn9.setOnClickListener(this);

        btnDot = findViewById(R.id.btnDot);
        btnDot.setOnClickListener(this);

        btnDash = findViewById(R.id.btnDash);
        btnDash.setOnClickListener(this);

        btnGen = findViewById(R.id.btnGen);
        btnGen.setOnClickListener(this);

        btnVal = findViewById(R.id.btnVal);
        btnVal.setOnClickListener(this);

        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        btnScore = findViewById(R.id.btnScore);
        btnScore.setOnClickListener(this);

        btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(this);

        listResults = new ArrayList<>();
    }

    private void generateQuestion() {
        num1 = r.nextInt(9);
        num2 = r.nextInt(9);
        operator = r.nextInt(operators.length);

        if (operator == SUBTRACT) {
            while (num2 > num1) {
                num1 = r.nextInt(9);
                num2 = r.nextInt(9);
            }
        } else if (operator == DIVIDE) {
            while (((double) num1 / (double) num2) % 1 > 0 || num1 == 0 || num2 == 0) {
                num1 = r.nextInt(9);
                num2 = r.nextInt(9);
            }
        } else if (operator == MULTIPLY) {
            while (num1 == 0 || num2 == 0) {
                num1 = r.nextInt(9);
                num2 = r.nextInt(9);
            }
        } tvQuestion.setText(num1 + " " + operators[operator] + " " + num2);
    }

    private void updateEditText(String update){
        etAnswer.setText(etAnswer.getText() + update);
    }

    private void clearEditText(){
        etAnswer.setText("");
        periodIsPressedOnce = false;
    }

    private void calculate() {
        switch (operator) {
            case ADD:
                realAnswer = num1 + num2;
                break;
            case SUBTRACT:
                realAnswer = num1 - num2;
                break;
            case MULTIPLY:
                realAnswer = num1 * num2;
                break;
            case DIVIDE:
                realAnswer = num1 / num2;
                break;
            default:
                break;
        }
    }

    private void validateAns(){

        if (etAnswer.getText().toString().matches("")) {

            Toast.makeText(this, "No answer given", Toast.LENGTH_LONG).show();
        }
        else{
            userAnswer = (int) Math.floor(Double.parseDouble(etAnswer.getText().toString()));
            calculate();


            if (userAnswer == realAnswer) {
                userResult = "Correct";
                rightCount += 1;
            }
            else userResult = "Wrong";

            Toast.makeText(this, userResult, Toast.LENGTH_LONG).show();
            addToArrayList();
        }
    }

    private void addToArrayList() {

        String question = (num1 + " " + operators[operator] + " " + num2);

        Result res = new Result( realAnswer, userAnswer, question, userResult);
        listResults.add(res);
    }

    private void showScores() {

        if (listResults.size() > 0) {
            score = rightCount * 100 / (listResults.size());
        } else score = 0;

        Bundle bundle = new Bundle();
        bundle.putSerializable("bundleExtra", listResults);
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("intentExtra", bundle);
        intent.putExtra("scoreExtra", score);
        startActivityForResult(intent, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        tvTitle.setText(data.getStringExtra("title"));
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn0:
                updateEditText("0");
                break;
            case R.id.btn1:
                updateEditText("1");
                break;
            case R.id.btn2:
                updateEditText("2");
                break;
            case R.id.btn3:
                updateEditText("3");
                break;
            case R.id.btn4:
                updateEditText("4");
                break;
            case R.id.btn5:
                updateEditText("5");
                break;
            case R.id.btn6:
                updateEditText("6");
                break;
            case R.id.btn7:
                updateEditText("7");
                break;
            case R.id.btn8:
                updateEditText("8");
                break;
            case R.id.btn9:
                updateEditText("9");
                break;
            case R.id.btnDot:
                if (!periodIsPressedOnce) {
                    updateEditText(".");
                    periodIsPressedOnce = true;
                }                ;
                break;
            case R.id.btnDash:
                if(etAnswer.getText().toString().matches("")) {
                    updateEditText("-");
                }
                break;


            case R.id.btnClear:
                clearEditText();
                break;
            case R.id.btnGen:
                generateQuestion();
                clearEditText();
                break;
            case R.id.btnVal:
                validateAns();
                break;
            case R.id.btnScore:
                showScores();
                clearEditText();
                break;
            case R.id.btnFinish:
                finish();
                break;
        }
    }
}