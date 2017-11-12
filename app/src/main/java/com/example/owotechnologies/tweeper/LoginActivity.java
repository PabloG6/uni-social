package com.example.owotechnologies.tweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kenai.jffi.Main;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private static String MEDIA_TAG = "TWEEP_TAG";

    EditText walletTag;
    EditText walletPassword;
    private String walletTagData;
    private static String password;
    Button button;
    String url = "http://localhost:3000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        walletTag = findViewById(R.id.tweet_edit_tag);
        walletPassword = findViewById(R.id.password);
        final RequestQueue queue = Volley.newRequestQueue(this);
        final StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(LoginActivity.this, "Created your custom wallet!", Toast.LENGTH_LONG).show();
                //move to the messaging application
                Intent intent = new Intent(LoginActivity.this, MessageActivity.class);
                startActivity(intent);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error!=null && error.getMessage() != null)
                    Log.d("Volley", "Error: " + error.getMessage());
                else if(error!=null && error.networkResponse!=null) {
                    Log.d("Volley", "response code: "+error.networkResponse.statusCode);
                    byte[] data = error.networkResponse.data;
                    int length = data.length;
                    StringBuilder builder = new StringBuilder(length);
                    for(int x = 0; x < length; x++)
                    {
                        builder.append(((char) data[x]));

                    }
                    String errorMessage = builder.toString();
                    Log.d("Volley", "error message data: "+errorMessage);


                }
                Toast.makeText(LoginActivity.this, "Never worked! " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userName", walletTagData);
                params.put("password", password);
                params.put("create_wallet", "true");
                return params;
            }
        };
        StaticInfo.password = password;

        button = findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createWallet();
      //          queue.add(request);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
        walletTag.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                walletTagData = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                walletTagData = editable.toString();
            }
        });

        walletPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                password = editable.toString();
            }
        });


    }

    private void createWallet() {
        if (valid(walletTagData, password)) {

        }

    }

    private boolean valid(String walletTagData, String password) {
        if (password != null && !password.equals("") && password.length() > 6 && checkIfTagExists(walletTagData)) {
            // TODO: 11/11/2017 do something about this
        }
        return true;
    }

    private boolean checkIfTagExists(String walletTagData) {
        // TODO: 11/11/2017 check database to see if tag already exists. if not notify user
        return true;

    }


}
