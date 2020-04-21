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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText linkInput;
    Button scrapeButton;
    RequestQueue mQueue;
    JsonObjectRequest request;

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
                System.out.println("Scrape Button Clicked");
                try {
                    jsonGet();
                } catch (JSONException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
//                Intent gradeIntent = new Intent(getApplicationContext(), GradeActivity.class);
//                startActivity(gradeIntent);
            }
        });
    }

    private void jsonGet() throws JSONException, UnsupportedEncodingException {
        String url = "http://192.168.1.9:8000/privacy/";
          JSONObject jsonBody = new JSONObject();
          jsonBody.put("weblink", linkInput.getText().toString());
          final String requestBody= jsonBody.toString().replace("\\","");
          System.out.println("FFFFFFFFFFFFFFFFFF : " + requestBody);
        System.out.println("FFFFFFFFFFFFFFFFFF : " + requestBody.getBytes("utf-8"));
        request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int[] grades=  new int[6];
                        System.out.println(response.toString());
                        try {
                            grades[0] = response.getInt("Type1");
                            grades[1] = response.getInt("Type2");
                            grades[2] = response.getInt("Type3");
                            grades[3] = response.getInt("Type4");
                            grades[4] = response.getInt("Type5");
                            grades[5] = response.getInt("Type6");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent gradeIntent = new Intent(getApplicationContext(), GradeActivity.class);
                        gradeIntent.putExtra("grades", grades);
                        startActivity(gradeIntent);
                    }
                },      //Toast.makeText(getActivity(), "This is my Toast message!",
//                Toast.LENGTH_LONG).show();
                //
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Cannot be Scraped", Toast.LENGTH_LONG).show();
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

        request.setRetryPolicy(new DefaultRetryPolicy(
                200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

          mQueue.add(request);
    }
}
