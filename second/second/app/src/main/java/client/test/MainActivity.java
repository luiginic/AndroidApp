package client.test;

import android.content.Intent;
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

    /*this method will be called when enter you data button was pressed
      and will send the user to DataEntryACtivity
    */
    public void navigate2DataEntry(View view) {
        Intent intent = new Intent(this, DataEntryActivity.class);
        startActivity(intent);
    }
}

