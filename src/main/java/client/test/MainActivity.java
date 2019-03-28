package client.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "MainActivity";
    TCPClient client;
    Button sendBtn;
    String alert = "Alerta";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = findViewById(R.id.buttonSend);
        client = new TCPClient();
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if(id == sendBtn.getId()){
                    client.connect();
                    client.writeData(alert.getBytes());
                }
            }
        });
    }

    }


