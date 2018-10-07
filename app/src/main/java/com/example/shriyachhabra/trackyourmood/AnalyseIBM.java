package com.example.shriyachhabra.trackyourmood;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneCategory;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneScore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AnalyseIBM extends AppCompatActivity {


    Button button,mes;
    TextView sentiType,disc;
    String sentiment;
    ToneAnalyzer service;
    ListView posts;

            String text = "I know the times are difficult! Our sales have been "
                + "disappointing for the past three quarters for our data analytics "
                + "product suite. We have a competitive data analytics product "
                + "suite in the industry. But we need to do our job selling it! "
                + "We need to acknowledge and fix our sales challenges. "
                + "We can’t blame the economy for our lack of execution! " + "We are missing critical sales opportunities. "
                + "Our product is in no way inferior to the competitor products. "
                + "Our clients are hungry for analytical tools to improve their "
                + "business outcomes. Economy has nothing to do with it.";


    String[] one={"Life is like a roller coaster, live it, be happy, enjoy life.#ReallyHappy","YAY,GOT PLACED!!!"};
    String[] two={"People will hurt you sometimes.Be Stong and Move on.","#MOVEON"};
    String[] three={"I am someone who can't hold on to negativity or hold on to grudges. I might feel something at a certain point, but I get tired after that. I don't carry it with me. I forgive and forget very easily, and that's the only way to be happy and peaceful"
          ,"Moving on from this sad phase of life because something magical awaits"};
    String[] four={"I dont trust people.I dont have any friends because people hurt you always.","I think there is no reason to live left now.#sad #nojob"};
    String[] five={"The important thing is to never stop questioning.Be curious always"};
    String[] six={"Life is awesome.#having fun"};
    String[] seven={text};
    String[] eight={"Loving can hurt but it is the only thing that I know"};
    String[] pos;

    String[] FriendsName = {
            "Aarna Gupta",
            "Abhi Sheik",
            "Anjali Sharma",
            "Brihul Sharma",
            "Muskan Jindal",
            "Sheena Mittal",
            "Shuchita Dobal",
            "Yukti Gupta"
    };


    HashMap<Integer,String[]> map;

    int FrndNo;



    public class analyseInBG extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... text2Analyse) {
            Log.d("SHRIYA",text2Analyse[0]);

            runOnUiThread(new Runnable(){

                @Override
                public void run() {
                      sentiType.setText("analysing....");
                }
            });

            //using watson
            HashMap<String, Integer> mp = new HashMap<>();
            String str = "";

            for(int i=0;i<text2Analyse.length;i++) {
                ToneOptions toneOptions = new ToneOptions.Builder()
                        .text(text2Analyse[i])
                        .build();

                ToneAnalysis tone = service.tone(toneOptions).execute();
                System.out.println(tone);


                // sentiment=tone.getDocumentTone().toString();
                List<ToneScore> list = tone.getDocumentTone().getTones();




                for (ToneScore ts : list) {
                    String st = ts.getToneName();
                    if (mp.containsKey(st) == true) {
                        mp.put(st, mp.get(st) + 1);
                    } else {
                        mp.put(st, 1);
                    }


                }
            }
             int max=-1;
             for(String key:mp.keySet()){
                  int b=mp.get(key);
                 if(b>=max){
                     max=b;
                     str=key;
                 }
             }




          sentiment=str;
            return sentiment;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            sentiType.setText("Name: "+FriendsName[FrndNo]+"\n"+"Prominent Sentiment: "+s);
            mes.setVisibility(View.VISIBLE);
            mes.setEnabled(true);
            disc.setVisibility(View.VISIBLE);
        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button=findViewById(R.id.analyser);
        disc=findViewById(R.id.disc);
        posts=findViewById(R.id.Posts);
        sentiType=findViewById(R.id.kindOfSenti);
        service = new ToneAnalyzer("2017-09-21");
        service.setUsernameAndPassword("aec6623a-b774-42b2-a525-003e1efd31c3", "NnNj58eYOudz");
        mes=findViewById(R.id.mes);
        mes.setEnabled(false);
        //fill the map
        map=new HashMap<>();
        map.put(0,one); map.put(1,two); map.put(2,three); map.put(3,four);
        map.put(4,five); map.put(5,six); map.put(6,seven); map.put(7,eight);


        //receive the intent
        FrndNo = getIntent().getIntExtra("friendNO", 0);

        pos=map.get(FrndNo);
        //adapter
        ArrayAdapter<String> FriendAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                pos

        );


        posts.setAdapter(FriendAdapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                analyseInBG task= new analyseInBG();
                task.execute(pos);

            }
        });


        mes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(AnalyseIBM.this,Measures.class);
                i.putExtra("sentiment",sentiment);
                startActivity(i);
               finish();

            }
        });
    }




}
