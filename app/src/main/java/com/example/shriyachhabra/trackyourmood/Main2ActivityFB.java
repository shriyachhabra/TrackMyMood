package com.example.shriyachhabra.trackyourmood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;



public class Main2ActivityFB extends AppCompatActivity {

    Button loginButton;
    TextView loginStatus;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_fb);



        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);



        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
      loginStatus=findViewById(R.id.Login_Status);



        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        //loginResult.getAccessToken().getUserId()
                        loginStatus.setText(" successfully logged in :D");
                        Intent i=new Intent(Main2ActivityFB.this,FriendsList.class);
                        startActivity(i);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        loginStatus.setText("oops.cancelled");



                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        loginStatus.setText("oops.error"+exception);
                        Log.d("CHINUEXP", "onCancel:  "+exception);
                    }
                });





    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }




}
