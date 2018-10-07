package com.example.shriyachhabra.trackyourmood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashSet;

public class Measures extends AppCompatActivity {


    String Sadness="1.Take one deep breath.\n" +
            " \n" +
            "2. Slap a label on your negative feelings. Attaching a label shifts activity from the emotional part of your brain to the thinking part, making you hurt less and feel more in control. \n" +
            " \n" +
            "3.Sit up straight. Poor posture can actually cause negative emotional states. Studies show that people who slouch experience more bad moods, lower self-esteem, and poorer confidence than those with upright posture. Sitting up straight can cause positive emotional states—a sense of confidence, assertiveness, and a happier mood.\n" +
            " \n" +
            "4. Appreciate yourself. Think of one thing you’ve already accomplished or handled well today or focus on a good quality you’ve demonstrated—a start to a project, a phone call, or even an honest talk with a colleague that ended well. Drawing your attention to your strengths will lift your spirits.\n" +
            " \n" +
            "5.Practice inner calming techniques like yoga or martial arts.\n" +
            " ";

    String Tentative="1.Turn your negative thoughts to positive thoughts.\n" +
            " \n" +
            "2. Maintain a positive support network. Connect with those close to you, family or friends and stay away from people or things that make you feel bad.\n" +
            " \n" +
            "3. Accept compliments gracefully. Take pride in yourself.\n" +
            " \n" +
            "4.Don’t accept failure and get rid of the negative voices in your head.\n";

    String Joy="It's good that you are happy :D.To continue being in this state,\n"+"1.Exercise everyday\n"+
            "2.Drink lots of water\n"+"3.Don’t self-ruminate.\n"+"4.Don’t compare.\n"+"5. Be of service and know how to take care of yourself.";

    String Analytical="1.Consume content that’s way outside your comfort zone. Read blogs outside of your industry.\n" +
            " \n" +
            "2.Take a phone call with someone you don’t know. Hearing someone's story that’s completely new to you can be an eye-opening and mind expanding experience.\n" +
            " \n" +
            "3.Get at least one other person to join you for a 45-60 minute brainstorming session. No technology and no criticism whatsoever. Bring a topic or idea you want to brainstorm to the table, and just start riffing on it back and forth. Write down all your ideas (on actual paper) and don't critique a single idea. It's important to do this in person and to make absolutely sure you don't have any negative energy or feedback throughout the process. You may end up with 100 horrible ideas, but I bet you'll have one or two good ones. Plus, you'll get better at this the more you do it.\n" +
            " \n" +
            "4.Relax. good ideas and creativity usually do not appear under stress.\n" +
            " \n" +
            "5.Stop criticizing yourself and stop being so harsh for yourself. Accept yourself the way you are and do things to the best of your ability.";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measures);
        TextView measures=findViewById(R.id.measures);

       String senti=getIntent().getStringExtra("sentiment");

        if(senti.equals("Joy")){
            measures.setText(Joy);
        }else if(senti.equals("Analytical")){
            measures.setText(Analytical);
        }else if(senti.equals("Sadness")){
            measures.setText(Sadness);
        }else if(senti.equals("Tentative")){
            measures.setText(Tentative);
        }else{
            measures.setText(Joy);
        }
    }
}
