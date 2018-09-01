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

public class QuestionPart2Activity extends AppCompatActivity {
    private RadioGroup radioGroupRestecg, radioGroupExang;
    private EditText editTextThalach, editTextOldpeak ;
    private Button btnCancel, btnContinue;
    private String restecg, exang, thalach, oldpeak;
    private Boolean choiceRestecg = false;
    private Boolean choiceExang = false;
    String sex, cp, fbs, chol, trestbps, age ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_part2);
        //
        AnhXa();
        ActionClick();
    }

    private void ActionClick() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thalach = editTextThalach.getText().toString().trim();
                oldpeak = editTextOldpeak.getText().toString().trim();
                if(choiceRestecg != false && choiceExang!=false && !TextUtils.isEmpty(thalach) && !TextUtils.isEmpty(oldpeak)){
                    Bundle bundle = getIntent().getBundleExtra("dulieu");
                    if(bundle != null){
                        age = bundle.getString("age").toString().trim();
                        sex = bundle.getString("sex").toString().trim();
                        cp = bundle.getString("cp").toString().trim();
                        trestbps = bundle.getString("trestps").toString().trim();
                        chol = bundle.getString("chol").toString().trim();
                        fbs = bundle.getString("fbs").toString().trim();
                        //CheckConnect.ShowToast(getApplicationContext(), restecg+" "+exang+" "+thalach+" "+oldpeak);
                        //                radioGroupRestecg.clearCheck();
                        //                radioGroupExang.clearCheck();
                        //CheckConnect.ShowToast(getApplicationContext(),age + " "+sex+ " "+cp+" "+trestbps+ " "+chol+" "+fbs);
                        Intent intent = new Intent(getApplicationContext(),QuestionPart3Activity.class);
                        Bundle bun = new Bundle();
                        bun.putString("age",age);
                        bun.putString("sex",sex);
                        bun.putString("cp",cp);
                        bun.putString("trestbps",trestbps);
                        bun.putString("chol",chol);
                        bun.putString("fbs",fbs);
                        bun.putString("restecg",restecg);
                        bun.putString("exang",exang);
                        bun.putString("thalach",thalach);
                        bun.putString("oldpeak",oldpeak);
                        intent.putExtra("dulieu2",bun);
                        startActivity(intent);
                    }


                }else{

                    CheckConnect.ShowToast(getApplicationContext(),"Vui lòng nhập đầy đủ thông tin");
                }
            }
        });
    }

    public void onRadioButtonClickRestecg(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_ques2_restecg_normal:
                if (checked){// Pirates are the best
                    choiceRestecg = true;
                    restecg = "0";
                }else{
                    choiceRestecg = false;
                }
                break;
            case R.id.radio_ques2_restecg_abnormality:
                if (checked){// Pirates are the best
                    choiceRestecg = true;
                    restecg = "1";
                }else{
                    choiceRestecg = false;
                }
                break;
            case R.id.radio_ques2_restecg_probable:
                if (checked){// Pirates are the best
                    choiceRestecg = true;
                    restecg = "2";
                }else{
                    choiceRestecg = false;
                }
                break;
        }
    }
    public void onRadioButtonClickExang(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_ques2_exercise_angina_true:
                if (checked){// Pirates are the best
                    choiceExang = true;
                    exang = "1";
                }else{
                    choiceExang = false;
                }
                break;
            case R.id.radio_ques2_exercise_angina_false:
                if (checked){// Pirates are the best
                    choiceExang = true;
                    exang = "0";
                }else{
                    choiceExang = false;
                }
                break;
        }
    }

    private void AnhXa() {
        radioGroupRestecg = (RadioGroup) findViewById(R.id.radiogr_restecg);
        radioGroupExang = (RadioGroup) findViewById(R.id.radiogr_exang);
        editTextThalach = (EditText) findViewById(R.id.edit_thalach_ques2);
        editTextOldpeak = (EditText) findViewById(R.id.edit_ques2_oldpeak);
        btnCancel = (Button) findViewById(R.id.btn_ques2_cancel);
        btnContinue = (Button) findViewById(R.id.btn_ques2_continue);

    }
}
