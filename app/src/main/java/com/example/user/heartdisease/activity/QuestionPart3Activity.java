package com.example.user.heartdisease.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.heartdisease.R;
import com.example.user.heartdisease.model.HeartDisease;
import com.example.user.heartdisease.ultil.CheckConnect;
import com.example.user.heartdisease.ultil.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class QuestionPart3Activity extends AppCompatActivity {
    private RadioGroup radioGroupSlope, radioGroupCa, radioGroupThal;
    private Button btnCancel, btnDisease;
    private String slope,ca, thal;
    private Boolean choiceSlope, choiceCa, choiceThal;
    String sex, cp, fbs, chol, trestbps, age, restecg, exang, thalach, oldpeak ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_part3);
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
        btnDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choiceCa != false && choiceSlope != false && choiceThal != false){

                    //CheckConnect.ShowToast(getApplicationContext(), slope+" "+ca+" "+thal);
                    Bundle bundle = getIntent().getBundleExtra("dulieu2");
                    if(bundle != null){
                        age = bundle.getString("age").toString().trim();
                        sex = bundle.getString("sex").toString().trim();
                        cp = bundle.getString("cp").toString().trim();
                        trestbps = bundle.getString("trestbps").toString().trim();
                        chol = bundle.getString("chol").toString().trim();
                        fbs = bundle.getString("fbs").toString().trim();
                        restecg = bundle.getString("restecg").toString().trim();
                        exang = bundle.getString("exang").toString().trim();
                        thalach = bundle.getString("thalach").toString().trim();
                        oldpeak = bundle.getString("oldpeak").toString().trim();

                        ActionHeartDisease(age,sex,cp,trestbps,chol,fbs,restecg,thalach,exang,oldpeak,slope,ca,thal);
                        //CheckConnect.ShowToast(getApplicationContext(), age +" "+ sex+" "+cp+" "+trestbps+" "+chol+" "+fbs+" "+restecg+" "+exang+" "+thalach+" "+oldpeak);


                    }
                }else{
                    CheckConnect.ShowToast(getApplicationContext(),"Vui lòng điền đầy đủ thông tin");
                }
            }
        });

    }

    private void ActionHeartDisease(final String age, final String sex, final String cp, final String trestbps, final String chol, final String fbs, final String restecg, final String thalach, final String exang, final String oldpeak, final String slope, final String ca, final String thal) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("age",Integer.parseInt(age));
            jsonObject.put("sex",Integer.parseInt(sex));
            jsonObject.put("cp",Integer.parseInt(cp));
            jsonObject.put("trestbps",Integer.parseInt(trestbps));
            jsonObject.put("chol",Integer.parseInt(chol));
            jsonObject.put("fbs",Integer.parseInt(fbs));
            jsonObject.put("restecg",Integer.parseInt(restecg));
            jsonObject.put("thalach",Integer.parseInt(thalach));
            jsonObject.put("exang",Integer.parseInt(exang));
            jsonObject.put("oldpeak",Float.parseFloat(oldpeak));
            jsonObject.put("slop",Integer.parseInt(slope));
            jsonObject.put("ca",Integer.parseInt(ca));
            jsonObject.put("thal",Integer.parseInt(thal));
            //{"age": 63, "sex": 1, "cp": 1, "trestbps": 145, "chol": 233, "fbs": 1, "restecg": 2, "thalach": 150, "exang": 0, "oldpeak": 2.3, "slop": 3, "ca": 0, "thal": 6}
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Server.getResult, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response!=null){
                      //  CheckConnect.ShowToast(getApplicationContext(),"Thanh cong");
                        try {
                            String result = response.getString("result");
                            String accuracy = response.getString("accuracy");
                            Calendar time = Calendar.getInstance();
                            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                            String currenttime = df.format(time.getTime());
                            HeartDisease heartDisease = new HeartDisease(age,sex,cp,trestbps,chol,fbs,restecg,thalach,exang,oldpeak,slope,ca,thal);
                            ResultDiagnosis(result,accuracy,currenttime, heartDisease);
                            //CheckConnect.ShowToast(getApplicationContext(),result +" "+ accuracy+" "+currenttime);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        CheckConnect.ShowToast(getApplicationContext(),"khong");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    CheckConnect.ShowToast(getApplicationContext(),"loi");
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void ResultDiagnosis(final String result, final String accuracy, final String currenttime, final HeartDisease heartDisease) {
        final Dialog dialog = new Dialog(QuestionPart3Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // dùng để goi layout dialog
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_detail_answer);
        TextView resultDiagnosis = dialog.findViewById(R.id.txtResultDiagnosisReslut);
        TextView resultTime = dialog.findViewById(R.id.txtDiagnosisTimeResult);
        TextView resultAcc = dialog.findViewById(R.id.txtAccuracyReslut);
        Button btnCancel = dialog.findViewById(R.id.btnCancelResult);
        Button btnConfirm = dialog.findViewById(R.id.btnConfirmResult);
        TextView feedback = dialog.findViewById(R.id.txtFeedbackResult);
//        CheckConnect.ShowToast(getApplicationContext(),result);

        if (result.equals("1.0"))
            resultDiagnosis.setText("> 50% diameter narrowing");
        else
            resultDiagnosis.setText("< 50% diameter narrowing");
        resultAcc.setText(accuracy+"%");
        resultTime.setText(currenttime);
        dialog.getWindow().setLayout((int)(getResources().getDisplayMetrics().widthPixels*1.0),
                (int)(getResources().getDisplayMetrics().heightPixels*0.90));
        dialog.show();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionPart3Activity.this, QuestionActivity.class);
                startActivity(intent);
                finish();
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                FeedBack(result,accuracy,currenttime,heartDisease);
            }
        });

    }

    private void FeedBack(String result, String accuracy, String currenttime, HeartDisease heartDisease) {
//                        Log.e("EEE","Age:  " +heartDisease.getAge()+"Sex: "+heartDisease.getSex()+"Cp: "
//                        +heartDisease.getCp()+"Trestbps: "+heartDisease.getTrestbps()+"Chol: "+heartDisease.getChol()
//                        +"Fbs: "+heartDisease.getFbs()
//
//                +"Restecg: "+heartDisease.getRestecg()+"Thalach: "+heartDisease.getThalach()+"Exang: "+heartDisease.getExang()
//
//                +"Oldpeak: "+heartDisease.getOldpeak()+"Slope: "+heartDisease.getSlope()+"Ca: "+heartDisease.getCa()
//                        +"Thal: "+heartDisease.getThal());

        final Dialog dialog = new Dialog(QuestionPart3Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // dùng để goi layout dialog
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_detail_feedback);
        TextView resultDiagnosis = dialog.findViewById(R.id.txtResultDiagnosisFeedback);
        TextView resultTime = dialog.findViewById(R.id.txtDiagnosisTimeFeedback);
        TextView resultAcc = dialog.findViewById(R.id.txtAccuracyFeedback);
        TextView txtAge,txtSex,txtCp,txtTrestbps, txtChol, txtFbs, txtRestecg, txtThalach,txtExang, txtOldpeak, txtSlope, txtCa, txtThal;
        txtAge = dialog.findViewById(R.id.txtAgeFeedback);
        txtSex = dialog.findViewById(R.id.txtSexFeedback);
        txtCp = dialog.findViewById(R.id.txtCpFeedback);
        txtTrestbps = dialog.findViewById(R.id.txtTrestbpsFeedback);
        txtChol = dialog.findViewById(R.id.txtCholFeedback);
        txtFbs = dialog.findViewById(R.id.txtFbsFeedback);
        txtRestecg = dialog.findViewById(R.id.txtRestecgFeedback);
        txtThalach = dialog.findViewById(R.id.txtThalachFeedback);
        txtExang = dialog.findViewById(R.id.txtExangFeedBack);
        txtOldpeak = dialog.findViewById(R.id.txtOldpeakFeedback);
        txtSlope = dialog.findViewById(R.id.txtSlopFeedback);
        txtCa = dialog.findViewById(R.id.txtCaFeedback);
        txtThal = dialog.findViewById(R.id.txtThalFeedback);
        Button btnYes = dialog.findViewById(R.id.btnYesFeedBack);
        Button btnNo = dialog.findViewById(R.id.btnNoFeedback);


        if (result.equals("1.0"))
            resultDiagnosis.setText("> 50% diameter narrowing");
        else
            resultDiagnosis.setText("< 50% diameter narrowing");
        resultAcc.setText(accuracy+"%");
        resultTime.setText(currenttime);



        txtAge.setText(heartDisease.getAge());
        if (heartDisease.getSex().equals("1"))
            txtSex.setText("male");
        else
            txtSex.setText("female");
        txtCp.setText(parseCP(heartDisease.getCp()));
        txtTrestbps.setText(heartDisease.getTrestbps());
        txtChol.setText(heartDisease.getChol());
        txtFbs.setText(parseFBS(heartDisease.getFbs()));
        txtRestecg.setText(parseRestecg(heartDisease.getRestecg()));
        txtThalach.setText(heartDisease.getThalach());
        txtExang.setText(parseExang(heartDisease.getExang()));
        txtOldpeak.setText(heartDisease.getOldpeak());
        txtSlope.setText(parseSlope(heartDisease.getSlope()));
        txtCa.setText(heartDisease.getCa());
        txtThal.setText(parseThal(heartDisease.getThal()));

        dialog.getWindow().setLayout((int)(getResources().getDisplayMetrics().widthPixels*1.0),
                (int)(getResources().getDisplayMetrics().heightPixels*0.90));
        dialog.show();


    }
    // từ số sang chữ
    private String parseCP(String number){
        int num = Integer.parseInt(number);
        String anw = "";
        switch (num){
            case 1:
                anw = "typical angina";
                break;
            case 2:
                anw = "atypical angina";
                break;
            case 3:
                anw = "non-anginal pain";
                break;
            case 4:
                anw = "asymptomatic ";
                break;
        }
        return anw;

    }
    // đổi quyền: từ số sang chữ
    private String parseFBS(String number){
        int num = Integer.parseInt(number);
        String anw = "";
        switch (num){
            case 0:
                anw = "false";
                break;
            case 1:
                anw = "true";
                break;
        }
        return anw;

    }
    private String parseRestecg(String number){
        int num = Integer.parseInt(number);
        String anw = "";
        switch (num){
            case 0:
                anw = "normal";
                break;
            case 1:
                anw = "having ST-T wave abnormality";
                break;
            case 2:
                anw = "showing probable or definite left ventricular hypertrophy";
                break;
        }
        return anw;

    }
    private String parseExang(String number){
        int num = Integer.parseInt(number);
        String anw = "";
        switch (num){
            case 0:
                anw = "true";
                break;
            case 1:
                anw = "false";
                break;
        }
        return anw;

    }
    private String parseSlope(String number){
        int num = Integer.parseInt(number);
        String anw = "";
        switch (num){
            case 1:
                anw = "upsloping ";
                break;
            case 2:
                anw = "flat ";
                break;
            case 3:
                anw = "downsloping";
                break;
        }
        return anw;

    }
    private String parseThal(String number){
        int num = Integer.parseInt(number);
        String anw = "";
        switch (num){
            case 3:
                anw = "normal";
                break;
            case 6:
                anw = "fixed defect";
                break;
            case 7:
                anw = "reversable defect";
                break;
        }
        return anw;

    }

    public void onRadioButtonClickSlope(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_ques3_upsloping:
                if (checked){// Pirates are the best
                    choiceSlope = true;
                    slope = "1";
                }else{
                    choiceSlope = false;
                }
                break;
            case R.id.radio_ques3_flat:
                if (checked){// Pirates are the best
                    choiceSlope = true;
                    slope = "2";
                }else{
                    choiceSlope = false;
                }
            case R.id.radio_ques3_downsloping:
                if (checked){// Pirates are the best
                    choiceSlope = true;
                    slope = "3";
                }else{
                    choiceSlope = false;
                }
                break;
        }
    }
    public void onRadioButtonClickCa(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_ques3_0:
                if (checked) {// Pirates are the best
                    choiceCa = true;
                    ca = "0";
                }else{
                    choiceCa = false;
                }
                break;
            case R.id.radio_ques3_1:
                if (checked) {// Pirates are the best
                    choiceCa = true;
                    ca = "1";
                }else{
                    choiceCa = false;
                }
                break;
            case R.id.radio_ques3_2:
                if (checked) {// Pirates are the best
                    choiceCa = true;
                    ca = "2";
                }else{
                    choiceCa = false;
                }
                break;
            case R.id.radio_ques3_3:
                if (checked) {// Pirates are the best
                    choiceCa = true;
                    ca = "3";
                }else{
                    choiceCa = false;
                }
                break;
        }
    }
    public void onRadioButtonClickThal(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_ques3_normal:
                if (checked) {// Pirates are the best
                    choiceThal = true;
                    thal = "3";
                }else{
                    choiceThal = false;
                }
                break;
            case R.id.radio_ques3_fixed:
                if (checked) {// Pirates are the best
                    choiceThal = true;
                    thal = "6";
                }else{
                    choiceThal = false;
                }
                break;
            case R.id.radio_ques3_defect:
                if (checked) {// Pirates are the best
                    choiceThal = true;
                    thal = "7";
                }else{
                    choiceThal = false;
                }
                break;
        }
    }

    private void AnhXa() {
        radioGroupSlope = (RadioGroup) findViewById(R.id.radiogr_ques3_slope);
        radioGroupCa = (RadioGroup) findViewById(R.id.radiogr_ques3_ca);
        radioGroupThal = (RadioGroup) findViewById(R.id.radiogr_ques3_thal);
        btnCancel = (Button) findViewById(R.id.btn_ques3_cancel);
        btnDisease = (Button) findViewById(R.id.btn_ques3_disease);

        choiceSlope = false;
        choiceCa = false;
        choiceThal = false;
    }
}
