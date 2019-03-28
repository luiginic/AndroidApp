package client.test;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class clientIterativ extends AsyncTask<Void, Void, Void> {



    private Socket connectionSocket;

    public clientIterativ() throws Exception {

        InetAddress serverAddress = InetAddress.getByName("172.20.10.2");
        int serverPort = Integer.parseInt("7000");
        this.connectionSocket = new Socket(serverAddress, serverPort);

    }
    public void start() throws IOException {
        String input;
        while (true) {
            String msg = "alert";
            PrintWriter out = new PrintWriter(this.connectionSocket.getOutputStream(), true);
            out.println(msg);
            out.flush();
        }
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return null ;
    }
}
