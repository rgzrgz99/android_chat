package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.*;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.messenger.chatmessengerapi.ChatMessengerSession;
import com.example.messenger.chats;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    Button Blogin;
    EditText login;
    EditText password;

    ChatMessengerSession chatMessengerSession = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Blogin = (Button) findViewById(R.id.button);
        login = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);

        Blogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String Login = login.getText().toString();
                        String Password = password.getText().toString();
                        String endpoint = "http://192.168.1.5:8081/messenger";
                        chatMessengerSession = new ChatMessengerSession(endpoint, Login, Password);
                        Intent SecAct;
                        SecAct = new Intent(getApplicationContext(), chats.class);
                        String token = chatMessengerSession.getToken();
                        SecAct.putExtra("token", token);
                        SecAct.putExtra("endpoint", endpoint);
                        startActivity(SecAct);
                    }
                });

                t.start();
            }
        }
        );
    }


}
