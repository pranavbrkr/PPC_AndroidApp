package com.example.ppcompliance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GradeActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        Intent gradeIntent = getIntent();
        String response = gradeIntent.getStringExtra("response");
        TextView tv1 = findViewById(R.id.tv1);
        tv1.setMovementMethod(new ScrollingMovementMethod());
        tv1.setText(response);

//        linearLayout = new LinearLayout(this);
//        linearLayout.setOrientation(LinearLayout.VERTICAL);
//        linearLayout.setGravity(1);
//        for(int ii = 0 ; ii < 6 ; ii++) {
//            ImageView i = new ImageView(this);
//            Drawable circ = getResources().getDrawable(R.drawable.circle);
//            circ.setTint(Color.RED);
//            i.setImageDrawable(circ);
//            i.setAdjustViewBounds(true);
//            i.setLayoutParams(new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT));
//
//
//            linearLayout.addView(i);
//        }
//        setContentView(linearLayout);
    }
}
