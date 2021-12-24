package com.example.merchantinstallments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.merchantinstallments.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int twoYears;
    int num;
    int oneYear;
    int oneAndHalfYear;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //binding
        activityMainBinding =ActivityMainBinding.inflate(getLayoutInflater());
        View view =activityMainBinding.getRoot();

        //edit language
        String languageToLoad  = "english"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(view);

        // hide keyboard
        (activityMainBinding.editTextTakePrice).setInputType(InputType.TYPE_NULL);
        //full program

        activityMainBinding.clear.setOnClickListener(this);
        activityMainBinding.constraintLayout.setOnClickListener(this);
        activityMainBinding.bu0.setOnClickListener(this);
        activityMainBinding.bu1.setOnClickListener(this);
        activityMainBinding.bu2.setOnClickListener(this);
        activityMainBinding.bu3.setOnClickListener(this);
        activityMainBinding.bu4.setOnClickListener(this);
        activityMainBinding.bu5.setOnClickListener(this);
        activityMainBinding.bu6.setOnClickListener(this);
        activityMainBinding.bu7.setOnClickListener(this);
        activityMainBinding.bu8.setOnClickListener(this);
        activityMainBinding.bu9.setOnClickListener(this);
        activityMainBinding.budelete.setOnClickListener(this);
        try {
            activityMainBinding.editTextTakePrice.addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    if(s.length()!=0) {
                        activityMainBinding.editTextTakePrice.setGravity(Gravity.CENTER);
                        calculator();
                    }
                    if(s.length()==0){
                        empty_field();
                    }
                }
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void calculator() {
        try
        {       num =Integer.parseInt(activityMainBinding.editTextTakePrice.getText().toString());
                twoYears = (int) ((num * 2.45 ) / 24);
                oneYear = (int) (num * 1.73 ) / 12 ;
                oneAndHalfYear = (int) ((num * 2.1) /18) ;
                setData();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    int roundUpTens(int n){
       if(n>0){
           if (n % 10 == 0){
               return (n+10);
           }return (n+10 - n%10);
       }else return 0;
    }
    public void setEmpty(){
        activityMainBinding.editTextTakePrice.setText("");
        empty_field();
    }
    @Override
    public void onClick(View v) {
        try {
            String text="";
            switch(v.getId()){
                case R.id.clear:
                    setEmpty();
                    break;
                case R.id.constraintLayout:
                    break;
                case R.id.bu0:
                    setText("0");
                    break;
                case R.id.bu1:
                    setText("1");
                    break;
                case R.id.bu2:
                    setText("2");
                    break;
                case R.id.bu3:
                    setText("3");
                    break;
                case R.id.bu4:
                    setText("4");
                    break;
                case R.id.bu5:
                    setText("5");
                    break;
                case R.id.bu6:
                    setText("6");
                    break;
                case R.id.bu7:
                    setText("7");
                    break;
                case R.id.bu8:
                    setText("8");
                    break;
                case R.id.bu9:
                    setText("9");
                    break;
                case R.id.budelete:
                    text = activityMainBinding.editTextTakePrice.getText().toString();
                   text = text.substring(0,text.length() - 1);
                    activityMainBinding.editTextTakePrice.setText(text);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        finish();
        System.exit(1);
        super.onBackPressed();
    }
    public void empty_field(){
        twoYears=0;
        num=0;
        oneYear=0;
        oneAndHalfYear=0;
        activityMainBinding.oneYearNum.setText("0");
        activityMainBinding.oneAndHalfYearNum.setText("0");
        activityMainBinding.twoYearsNum.setText("0");
    }
    public void setData(){
        activityMainBinding.oneYearNum.setText(String.valueOf(roundUpTens(oneYear)));
        activityMainBinding.oneAndHalfYearNum.setText(String.valueOf(roundUpTens(oneAndHalfYear)));
        activityMainBinding.twoYearsNum.setText(String.valueOf(roundUpTens(twoYears)));
    }
    public void setText(String s){

       if(activityMainBinding.editTextTakePrice.getText().length()<7){
           String text = activityMainBinding.editTextTakePrice.getText().toString();
        activityMainBinding.editTextTakePrice.setText(text+s);
       }
    }
}