package com.example.win10.brain_traina;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
     int correct_position, score=0, question=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void repeat(){
        Random rand = new Random();
        int value1 = rand.nextInt(50)+1;
        int value2 = rand.nextInt(200)+1;
        problem.setText(String.valueOf(value1)+"+"+String.valueOf(value2));
        int correct = value1+value2;
        correct_position = rand.nextInt(4)+1;
        int i, incorrect_answer;
        for(i=1;i<=4;i++)
        {
            if(i==correct_position)
                array.add(correct);
            else{
                incorrect_answer = rand.nextInt(250);
                while(incorrect_answer==correct){
                    incorrect_answer = rand.nextInt(250);
                }
                array.add(incorrect_answer);
            }
        }
        TextView option1 = (TextView)findViewById(R.id.option1);
        TextView option2 = (TextView)findViewById(R.id.option2);
        TextView option3 = (TextView)findViewById(R.id.option3);
        TextView option4 = (TextView)findViewById(R.id.option4);
        option1.setText(String.valueOf(array.get(0)));
        option2.setText(String.valueOf(array.get(1)));
        option3.setText(String.valueOf(array.get(2)));
        option4.setText(String.valueOf(array.get(3)));

    }
    final TextView timer  = (TextView)findViewById(R.id.timer);
    final TextView problem = (TextView)findViewById(R.id.problem);
    final ArrayList<Integer> array = new ArrayList<Integer>();
    CountDownTimer countdownTimer = new CountDownTimer(30*1000,1000) {
        @Override
        public void onTick(long l) {


            timer.setText(String.valueOf(l/1000)+"s");

        }

        @Override
        public void onFinish() {
            TextView displayMsg = (TextView)findViewById(R.id.displayMsg);
            displayMsg.setText("Score: "+String.valueOf(score)+"/"+String.valueOf(question));
        }
    };

    public void go(View view){
        Button button = (Button)findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);

        timer.setVisibility(View.VISIBLE);
        TextView points = (TextView)findViewById(R.id.points);
        points.setVisibility(View.VISIBLE);
        TextView displayMsg = (TextView)findViewById(R.id.displayMsg);
        displayMsg.setVisibility(View.VISIBLE);

        problem.setVisibility(View.VISIBLE);
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        gridLayout.setVisibility(View.VISIBLE);
        repeat();
        countdownTimer.start();
    }
    public void clickOption(View view){
        int x = (int)view.getTag();
        TextView displayMsg = (TextView)findViewById(R.id.displayMsg);
        TextView points = (TextView)findViewById(R.id.points);
        if(x==correct_position)
        {
            displayMsg.setText("CORRECT");
            score+=1;
            question+=1;
            points.setText(String.valueOf(score)+"/"+String.valueOf(question));
        }
        else
        {
            displayMsg.setText("INCORRECT");
            question+=1;
            points.setText(String.valueOf(score)+"/"+String.valueOf(question));
        }
        repeat();
    }
}
