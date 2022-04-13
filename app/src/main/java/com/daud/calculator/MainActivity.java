package com.daud.calculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*; // Library //
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText mainDisplay;
    private TextView secDisplay;
    private String userExpression;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialize View
        initialize();
    }

    // Set Display Method

    private void setMainDisplay(String currentKey) {
        if (mainDisplay.getText().toString().equals("0")) {
            mainDisplay.setText("");
        }
        String oldString = mainDisplay.getText().toString();
        int cursorPosition = mainDisplay.getSelectionStart();
        String startString = oldString.substring(0, cursorPosition);
        String endString = oldString.substring(cursorPosition);
        mainDisplay.setText(String.format("%S%S%S", startString, currentKey, endString).toLowerCase(Locale.ROOT));
        mainDisplay.setSelection(cursorPosition + currentKey.length());
    }

    // All OnClick Methods

    public void secDisplayClick(View view) {
        secDisplay.setText("");
        mainDisplay.setText(userExpression);
        mainDisplay.setSelection(userExpression.length());
    }

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
            }else if (textLength==0){
                secDisplay.setText("");
                mainDisplay.setText("0");
                mainDisplay.setSelection(1);
            }
        }else{
            secDisplay.setText("");
            mainDisplay.setText("0");
            mainDisplay.setSelection(1);
        }
    }

    public void acBtnClick(View view) {
        mainDisplay.setText("0");
        mainDisplay.setSelection(1);
        secDisplay.setText("");
    }

    public void equalBtnClick(View view) {
        userExpression = mainDisplay.getText().toString();
        if (!userExpression.isEmpty()){
            userExpression = userExpression.replace('÷', '/').replace('×', '*');
            Expression expression = new Expression(userExpression);
            String result = String.valueOf(expression.calculate());   // Library Link: https://mathparser.org/mxparser-downloads/
            result = result.replace('/','÷').replace('*','×');
            mainDisplay.setText(result);
            mainDisplay.setSelection(result.length());
            userExpression = userExpression.replace('*','×').replace('/','÷');
            secDisplay.setText(String.valueOf(userExpression));
        }
    }

    public void zeroBtnClick(View view) {
        setMainDisplay("0");
    }

    public void oneBtnClick(View view) {
        setMainDisplay("1");
    }

    public void twoBtnClick(View view) {
        setMainDisplay("2");
    }

    public void threeBtnClick(View view) {
        setMainDisplay("3");
    }

    public void fourBtnClick(View view) {
        setMainDisplay("4");
    }

    public void fiveBtnClick(View view) {
        setMainDisplay("5");
    }

    public void sixBtnClick(View view) {
        setMainDisplay("6");
    }

    public void sevenBtnClick(View view) {
        setMainDisplay("7");
    }

    public void eightBtnClick(View view) {
        setMainDisplay("8");
    }

    public void nineBtnClick(View view) {
        setMainDisplay("9");
    }

    public void eBtnClick(View view) {
        setMainDisplay("e");
    }

    public void pieBtnClick(View view) {
        setMainDisplay("pi");
    }

    public void sinBtnClick(View view) {
        setMainDisplay("sin(");
    }

    public void degBtnClick(View view) {
        setMainDisplay("deg(");
    }

    public void rootBtnClick(View view) {
        setMainDisplay("sqrt(");
    }

    public void pointBtnClick(View view) {
        setMainDisplay(".");
    }

    public void bStartBtnClick(View view) {
        setMainDisplay("(");
    }

    public void bEndBtnClick(View view) {
        setMainDisplay(")");
    }

    public void plusBtnClick(View view) {
        setMainDisplay("+");
    }

    public void minusBtnClick(View view) {
        setMainDisplay("-");
    }

    public void intoBtnClick(View view) {
        setMainDisplay("×");
    }

    public void divideBtnClick(View view) {
        setMainDisplay("÷");
    }

    // initialize Method
    private void initialize() {
        mainDisplay = findViewById(R.id.mainDisplay);
        mainDisplay.setShowSoftInputOnFocus(false);
        secDisplay = findViewById(R.id.secDisplay);
    }

}