package com.example.messenger;

import android.content.Intent;
import android.os.Bundle;

import com.example.messenger.chatmessengerapi.Chat;
import com.example.messenger.chatmessengerapi.ChatMessengerSession;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class chats extends AppCompatActivity {
    ListView chatlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        final String token = getIntent().getStringExtra("token");
        final String endpoint = getIntent().getStringExtra("endpoint");
        chatlist = findViewById(R.id.listView);
        final ArrayList<String> chatsName = new ArrayList<>();
        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, chatsName);
        chatlist.setAdapter(adapter);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                ChatMessengerSession chatMessengerSession = new ChatMessengerSession( endpoint, token);
                List<Chat> lc = chatMessengerSession.getChats();
                Integer i = 0;
               for (Chat c : lc){
                    chatsName.add(i, c.name);
                    i++;
                }
            }
        });
        t.start();
        adapter.notifyDataSetChanged();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void getChatList(){

    }
}
