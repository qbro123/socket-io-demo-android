package com.example.socketio_client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtNote;
    ListView rc_listnote;
    Button btnLogin, btnChat;
    List<String> mListMess = new ArrayList<>();
    ChatAdapter chatAdapter;
    String URL = "http://192.168.1.6:3000/";
    Socket socket;

    @Override
    protected void onStart() {
        super.onStart();
        try {
            socket = IO.socket(URL);
        } catch (Exception e) {
            e.printStackTrace();
        }    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        config();
    }

    private void config() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socket.emit("user_login", edtNote.getText().toString());
            }
        });
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socket.emit("send_mess", edtNote.getText().toString());

            }
        });

    }

    private void initView() {
        edtNote = findViewById(R.id.edtNote);
        rc_listnote = findViewById(R.id.rc_listchat);
        btnChat = findViewById(R.id.btnChat);
        btnLogin = findViewById(R.id.btnLogin);
        chatAdapter = new ChatAdapter(mListMess, getApplicationContext());
        rc_listnote.setAdapter(chatAdapter);
    }

    private Emitter.Listener listener = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject jsonObject = (JSONObject) args[0];
                    String mess = jsonObject.optString("data");
                    mListMess.add(mess);
                }
            });
        }
    };
}
