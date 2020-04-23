package com.example.ppcompliance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    EditText linkInput;
    Button scrapeButton;
    RequestQueue mQueue;
    ArrayList<String> sentences = new ArrayList<>(6);
    JsonObjectRequest request1, request2;
    int[] grades = new int[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkInput = findViewById(R.id.edittext1);
        scrapeButton = findViewById(R.id.button1);

        mQueue = Volley.newRequestQueue(this);


        scrapeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrapeButton.setEnabled(false);
                Toast.makeText(MainActivity.this, "Link Received", Toast.LENGTH_LONG).show();
                System.out.println("Scrape Button Clicked");
                try {
                    jsonGet();
                } catch (JSONException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void jsonGet() throws JSONException, UnsupportedEncodingException, IndexOutOfBoundsException {
        String url = "https://rest-api-privacy.herokuapp.com/privacy/";
          JSONObject jsonBody = new JSONObject();
          jsonBody.put("weblink", linkInput.getText().toString());
          final String requestBody= jsonBody.toString().replace("\\","");
          System.out.println("Body : " + requestBody);
        System.out.println("BodyBytes : " + requestBody.getBytes("utf-8"));
        request1 = new JsonObjectRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            for(int i = 0 ; i < 6 ; i++)
                            {
                                JSONObject ooo = new JSONObject();
                                ooo.put("Type", ("Type" + Integer.toString((i + 1))));
                                ooo.put("Data", response.getJSONArray(("Type" + Integer.toString((i + 1)))));
                                sentences.add(ooo.toString());
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println("\n" + "************************************" + "\n");
                        Intent loadIntent = new Intent(getApplicationContext(), LoadActivity.class);
                        loadIntent.putStringArrayListExtra("sentences", sentences);
                        startActivity(loadIntent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Cannot be Scraped", Toast.LENGTH_LONG).show();
                        scrapeButton.setEnabled(true);
                        System.out.println(error.toString());
                    }
                }){
                @Override
                public byte[] getBody(){
                    try {
                        return requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    return headers;
                }
        };

        request1.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        mQueue.add(request1);

    }

}
