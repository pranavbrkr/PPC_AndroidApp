package com.example.ppcompliance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class LoadActivity extends AppCompatActivity {

    int[] grades = new int[6];
    int count = 0;
    Boolean flag = false;
    RequestQueue mQueue;
    JsonObjectRequest request;
    ArrayList<String> sentences;
    final AtomicInteger requestsCounter = new AtomicInteger(0);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        mQueue = Volley.newRequestQueue(this);
        Intent loadIntent = getIntent();
        sentences = loadIntent.getStringArrayListExtra("sentences");

        gradeFunction();

        mQueue.addRequestFinishedListener(request -> {
            requestsCounter.decrementAndGet();
            if (requestsCounter.get() == 0) {
                System.out.println("END");
                Intent gradeIntent = new Intent(getApplicationContext(), GradeActivity.class);
                gradeIntent.putExtra("grades", grades);
                startActivity(gradeIntent);
            }
        });

    }

    public void gradeFunction()
    {
        getGrades(0);
        getGrades(1);
        getGrades(2);
        getGrades(3);
        getGrades(4);
        getGrades(5);
    }

    private void getGrades(final int i)
    {
        requestsCounter.incrementAndGet();
        String url = "https://ppc-grader-server.herokuapp.com/grade/";

        request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("Type" + i + " : " + response.getInt("Percentage"));
                            grades[i] = response.getInt("Percentage");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                })
        {
            @Override
            public byte[] getBody() {
                return sentences.get(i).getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        mQueue.add(request);

    }
}
