package com.daud.calculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*; // Library //
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText mainDisplay;
    private TextView secDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainDisplay = findViewById(R.id.mainDisplay);
        secDisplay = findViewById(R.id.secDisplay);
        mainDisplay.setShowSoftInputOnFocus(false);
        secDisplay.setShowSoftInputOnFocus(false);
    }

    private void setMainDisplay(String value, int cursorSize){
        if (mainDisplay.getText().toString().equals("0")){
            mainDisplay.setText("");
        }
        String oldString = mainDisplay.getText().toString();
        int cursorPosition = mainDisplay.getSelectionStart();
        String startString = oldString.substring(0, cursorPosition);
        String endString = oldString.substring(cursorPosition);
        mainDisplay.setText(String.format("%S%S%S",startString, value, endString));
        mainDisplay.setSelection(cursorPosition + cursorSize);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void zeroBtnClick(View view) {
        setMainDisplay("0",1);
    }

    public void oneBtnClick(View view) {
        setMainDisplay("1",1);
    }

    public void twoBtnClick(View view) {
        setMainDisplay("2",1);
    }

    public void threeBtnClick(View view) {
        setMainDisplay("3",1);
    }

    public void fourBtnClick(View view) {
        setMainDisplay("4",1);
    }

    public void fiveBtnClick(View view) {
        setMainDisplay("5",1);
    }

    public void sixBtnClick(View view) {
        setMainDisplay("6",1);
    }

    public void sevenBtnClick(View view) {
        setMainDisplay("7",1);
    }

    public void eightBtnClick(View view) {
        setMainDisplay("8",1);
    }

    public void nineBtnClick(View view) {
        setMainDisplay("9",1);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void eBtnClick(View view) {
        setMainDisplay("e",1);
    }

    public void pieBtnClick(View view) {
        setMainDisplay("π",1);
    }

    public void sinBtnClick(View view) {
        setMainDisplay("sin",3);
    }

    public void degBtnClick(View view) {
        setMainDisplay("°",1);
    }

    public void pointBtnClick(View view) {
        setMainDisplay(".",1);
    }

    public void bStartBtnClick(View view) {
        setMainDisplay("(",1);
    }

    public void bEndBtnClick(View view) {
        setMainDisplay(")",1);
    }

    public void rootBtnClick(View view) {
        String userExpression = mainDisplay.getText().toString();
        if (!userExpression.isEmpty()){
            userExpression = userExpression.replace('÷', '/').replace('×', '*');
            Expression expression = new Expression(userExpression);
            String result = String.valueOf(expression.calculate());   // Library Link: https://mathparser.org/mxparser-downloads/
            double rootDouble = Math.sqrt(Double.parseDouble(result));
            float rootFloat = Float.parseFloat(String.valueOf(rootDouble));
            mainDisplay.setText(String.valueOf(rootFloat));
            mainDisplay.setSelection(String.valueOf(rootFloat).length());

        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void plusBtnClick(View view) {
        setMainDisplay("+",1);
    }

    public void minusBtnClick(View view) {
        setMainDisplay("-",1);
    }

    public void intoBtnClick(View view) {
        setMainDisplay("×",1);
    }

    public void divideBtnClick(View view) {
        setMainDisplay("÷",1);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void cBtnClick(View view) {
        String content = mainDisplay.getText().toString();
        if (!content.equals("0")){
            int cursorPosition = mainDisplay.getSelectionStart();
            int textLength = mainDisplay.getText().length();
            if (cursorPosition!=0 && textLength!=0){
                SpannableStringBuilder selection = (SpannableStringBuilder) mainDisplay.getText();
                selection.replace(cursorPosition-1,cursorPosition,"");
                mainDisplay.setText(selection);
                mainDisplay.setSelection(cursorPosition-1);
            }
        }
    }

    public void acBtnClick(View view) {
        mainDisplay.setText("0");
        secDisplay.setText("");
    }

    public void equalBtnClick(View view) {
        String userExpression = mainDisplay.getText().toString();
        if (!userExpression.isEmpty()){
            userExpression = userExpression.replace('÷', '/').replace('×', '*');
            Expression expression = new Expression(userExpression);
            String result = String.valueOf(expression.calculate());   // Library Link: https://mathparser.org/mxparser-downloads/
            mainDisplay.setText(result);
            mainDisplay.setSelection(result.length());
            secDisplay.setText(String.valueOf(userExpression));
        }
    }
}