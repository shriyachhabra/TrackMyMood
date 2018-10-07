package com.example.shriyachhabra.trackyourmood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FriendsList extends AppCompatActivity {


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
    ListView lvCourses;
    TextView listofFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

        lvCourses = findViewById(R.id.lvCourses);
        listofFriends=findViewById(R.id.lof);



        ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                FriendsName
        );
        lvCourses.setAdapter(courseAdapter);
        lvCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent friendsPosts=new Intent(FriendsList.this,AnalyseIBM.class);
                friendsPosts.putExtra("friendNO", position);
                startActivity(friendsPosts);
            }
        });
    }
}
