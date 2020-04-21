package com.example.ppcompliance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GradeActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        Intent gradeIntent = getIntent();
        String response = gradeIntent.getStringExtra("response");
        int[] grades = gradeIntent.getIntArrayExtra("grades");
        System.out.println(grades[0]+ " "+ grades[1]+ " "+ grades[2]+ " "+ grades[3]+ " "+ grades[4]+ " "+grades[5]);

        Resources res = getResources();

        ProgressBar PB1 = findViewById(R.id.pb1);
        TextView TV1 = findViewById(R.id.tpb1);
        PB1.setProgress(grades[0]);
        TV1.setText(grades[0]+ "%");
        if(grades[0] < 34)
            PB1.setProgressDrawable(res.getDrawable( R.drawable.high_grade));
        else if(grades[0] < 67)
            PB1.setProgressDrawable(res.getDrawable( R.drawable.mid_grade));
        else
            PB1.setProgressDrawable(res.getDrawable( R.drawable.low_grade));



        ProgressBar PB2 = findViewById(R.id.pb2);
        TextView TV2 = findViewById(R.id.tpb2);
        PB2.setProgress(grades[0]);
        TV2.setText(grades[1] + "%");
        if(grades[1] < 34)
            PB2.setProgressDrawable(res.getDrawable( R.drawable.high_grade));
        else if(grades[1] < 67)
            PB2.setProgressDrawable(res.getDrawable( R.drawable.mid_grade));
        else
            PB2.setProgressDrawable(res.getDrawable( R.drawable.low_grade));


        ProgressBar PB3 = findViewById(R.id.pb3);
        TextView TV3 = findViewById(R.id.tpb3);
        PB3.setProgress(grades[2]);
        TV3.setText(grades[2] + "%");
        if(grades[2] < 34)
            PB3.setProgressDrawable(res.getDrawable( R.drawable.high_grade));
        else if(grades[2] < 67)
            PB3.setProgressDrawable(res.getDrawable( R.drawable.mid_grade));
        else
            PB3.setProgressDrawable(res.getDrawable( R.drawable.low_grade));


        ProgressBar PB4 = findViewById(R.id.pb4);
        TextView TV4 = findViewById(R.id.tpb4);
        PB4.setProgress(grades[3]);
        TV4.setText(grades[3] + "%");
        if(grades[3] < 34)
            PB4.setProgressDrawable(res.getDrawable( R.drawable.high_grade));
        else if(grades[3] < 67)
            PB4.setProgressDrawable(res.getDrawable( R.drawable.mid_grade));
        else
            PB4.setProgressDrawable(res.getDrawable( R.drawable.low_grade));

        ProgressBar PB5 = findViewById(R.id.pb5);
        TextView TV5 = findViewById(R.id.tpb5);
        PB5.setProgress(grades[4]);
        TV5.setText(grades[4] + "%");
        if(grades[4] < 34)
            PB5.setProgressDrawable(res.getDrawable( R.drawable.high_grade));
        else if(grades[4] < 67)
            PB5.setProgressDrawable(res.getDrawable( R.drawable.mid_grade));
        else
            PB5.setProgressDrawable(res.getDrawable( R.drawable.low_grade));


        ProgressBar PB6 = findViewById(R.id.pb6);
        TextView TV6 = findViewById(R.id.tpb6);
        PB6.setProgress(grades[5]);
        TV6.setText(grades[5] + "%");
        if(grades[5] < 34)
            PB6.setProgressDrawable(res.getDrawable( R.drawable.high_grade));
        else if(grades[5] < 67)
            PB6.setProgressDrawable(res.getDrawable( R.drawable.mid_grade));
        else
            PB6.setProgressDrawable(res.getDrawable( R.drawable.low_grade));




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