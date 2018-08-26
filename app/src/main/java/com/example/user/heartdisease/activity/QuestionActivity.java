package com.example.user.heartdisease.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.user.heartdisease.R;
import com.example.user.heartdisease.ultil.CheckConnect;

public class QuestionActivity extends AppCompatActivity {
    private EditText editAge, editTrestbps, editChol;
    private RadioGroup radioGroupSex, radioGroupChest, radioGroupBloodSugar;
    private Boolean choiceSex, choiceFbs, choiceCp;
//    private RadioButton radioButtonMale, radioButtonFemale,
//            radioButtonTypical, radioButtonAtypical, radioButtonNon,
//            radioButtonAsym, radioButtonTrue, radioButtonFalse;
    private Button btnCanncel, btnContinue;
    String sex, cp, fbs, chol, trestbps, age ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        AnhXa();
        ActionClickButton();
    }


    private void ActionClickButton() {
        btnCanncel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = editAge.getText().toString().trim();
                trestbps = editTrestbps.getText().toString().trim();
                chol = editChol.getText().toString().trim();
                if (choiceSex!=false && choiceCp != false && choiceFbs != false && !TextUtils.isEmpty(age) && !TextUtils.isEmpty(trestbps) && !TextUtils.isEmpty(chol)){

                CheckConnect.ShowToast(getApplicationContext(),age+" "+trestbps+" "+chol+" "+sex +" "+cp+ " "+fbs);
//                radioGroupSex.clearCheck();
//                radioGroupChest.clearCheck();
//                radioGroupBloodSugar.clearCheck();
                Intent intent = new Intent(getApplicationContext(),QuestionPart2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("age", age);
                bundle.putString("sex",sex);
                bundle.putString("cp",cp);
                bundle.putString("trestps", trestbps);
                bundle.putString("chol", chol);
                bundle.putString("fbs", fbs);
                intent.putExtra("dulieu",bundle);
                startActivity(intent);
                }else{
                    CheckConnect.ShowToast(getApplicationContext(),"Vui lòng điền đầy đủ thông tin");
                }
            }
        });
    }
    public void onRadioButtonSexClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_question_male:
                if (checked){// Pirates are the best
                    choiceSex = true;
                    sex = "1";
                } else {
                    choiceSex = false;
                }
                break;
            case R.id.radio_question_female:
                if (checked){
                    choiceSex = true;
                    // Ninjas rule
                    sex = "0";
                }else{
                    choiceSex = false;
                }
                break;
        }
    }
    public void onRadioButtonClickBloodSugar(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_question_true:
                if (checked){// Pirates are the best
                    choiceFbs = true;
                    fbs = "1";
                }else{
                    choiceFbs = false;
                }
                break;
            case R.id.radio_question_false:
                if (checked){// Pirates are the best
                    choiceFbs = true;
                    fbs = "0";
                }else{
                    choiceFbs = false;
                }
                break;
        }
    }
    public void onRadioButtonChestClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_question_typical:
                if (checked){// Pirates are the best
                    choiceCp = true;
                    cp = "1";
                }else{
                    choiceCp = false;
                }
                break;
            case R.id.radio_question_atypical:
                if (checked){// Pirates are the best
                    choiceCp = true;
                    cp = "2";
                }else{
                    choiceCp = false;
                }
                break;
            case R.id.radio_question_non:
                if (checked){// Pirates are the best
                    choiceCp = true;
                    cp = "3";
                }else{
                    choiceCp = false;
                }
                break;
            case R.id.radio_question_asym:
                if (checked){// Pirates are the best
                    choiceCp = true;
                    cp = "4";
                }else{
                    choiceCp = false;
                }
                break;
        }
    }

    private void AnhXa() {
        editAge = (EditText) findViewById(R.id.edit_question_old);
        editTrestbps = (EditText) findViewById(R.id.edit_question_blood_pressure);
        editChol = (EditText) findViewById(R.id.edit_question_serum);
        radioGroupSex = (RadioGroup) findViewById(R.id.radio_question_group_sex);
        radioGroupChest = (RadioGroup) findViewById(R.id.radio_question_group_chest);
        radioGroupBloodSugar = (RadioGroup) findViewById(R.id.radio_question_group_blood_sugar);
//        radioButtonMale = (RadioButton) findViewById(R.id.radio_question_male);
//        radioButtonFemale = (RadioButton) findViewById(R.id.radio_question_female);
//        radioButtonTypical = (RadioButton) findViewById(R.id.radio_question_typical);
//        radioButtonAtypical = (RadioButton) findViewById(R.id.radio_question_atypical);
//        radioButtonNon = (RadioButton) findViewById(R.id.radio_question_non);
//        radioButtonAsym = (RadioButton) findViewById(R.id.radio_question_asym);
//        radioButtonTrue = (RadioButton) findViewById(R.id.radio_question_true);
//        radioButtonFalse = (RadioButton) findViewById(R.id.radio_question_false);
        btnCanncel = (Button) findViewById(R.id.btn_question_canncel);
        btnContinue = (Button) findViewById(R.id.btn_question_continue);
        choiceSex = false;
        choiceFbs = false;
        choiceCp = false;

    }
}
