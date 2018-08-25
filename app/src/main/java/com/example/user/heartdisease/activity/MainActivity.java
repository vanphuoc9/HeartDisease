package com.example.user.heartdisease.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.heartdisease.R;
import com.example.user.heartdisease.ultil.CheckConnect;
import com.example.user.heartdisease.ultil.Server;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Button btnSend;
    private TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
       // ActionClickButton();
    }

    private void ActionClickButton() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SenData();
            }
        });
    }

    private void SenData() {
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://172.20.10.2:5000/getResult", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (response.equals("ba")){
//                    CheckConnect.ShowToast(getApplicationContext(),"Thành công");
//                }else{
//                    CheckConnect.ShowToast(getApplicationContext(),"that bai");
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                CheckConnect.ShowToast(getApplicationContext(),"lỗi");
//            }
//        });
//        requestQueue.add(stringRequest);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("age","65");
            jsonObject.put("sex","1");
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Server.getResult, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response!=null){
                        CheckConnect.ShowToast(getApplicationContext(),"Thanh cong");
                        try {
                            String ketqua = response.getString("key");
                            CheckConnect.ShowToast(getApplicationContext(),ketqua);
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

    private void AnhXa() {
        btnSend = (Button) findViewById(R.id.btnSenData);
        txtResult = (TextView) findViewById(R.id.txtResult);
    }
}
