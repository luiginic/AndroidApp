package client.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class TCPClient {

    private static String TAG = "CLIENT"; //Tag used for debugging with Log
    private String serverIP = "172.20.10.2";
    private long startTime = 01;
    private int serverPort = 9000;
    private Socket connectionSocket;

    public class ConnectRunnable implements Runnable{
        InetAddress serverAddr;

        @Override
        public void run() {
            try {
                Log.d(TAG,"Client is trying to connect!");

                serverAddr = InetAddress.getByName(serverIP);
                startTime=System.currentTimeMillis();

                connectionSocket = new Socket();
                connectionSocket.connect(new InetSocketAddress(serverAddr,serverPort),5000);
                long time=System.currentTimeMillis() - startTime;
                Log.d(TAG,"Connected! Current duration: "+time+"ms");

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d(TAG,"Connection thread stopped");

        }
    }

    public void connect(){
        new Thread(new ConnectRunnable()).start();
    }

    public class SendRunnable implements Runnable{

        byte[] data;
        private OutputStream out;
        private boolean hasMessage = false;
        int dataType = 1;

        public SendRunnable(Socket server){
            try {
                this.out = server.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void send(byte[] bytes){
            this.data=bytes;
            this.hasMessage = true;
        }

        @Override
        public void run() {
            Log.d(TAG,"Sending alert");
            while(!Thread.currentThread().isInterrupted() && isConnected()){
                if(this.hasMessage){
                    startTime = System.currentTimeMillis();
//                    try {
//                        this.out.write(ByteBuffer.allocate(4).putInt(data.length).array());
//                        this.out.write(ByteBuffer.allocate(4).putInt(dataType).array());
//                        this.out.write(data,0,data.length);
//                        this.out.flush();
                        String alert  = "alert";
                        PrintWriter writer = new PrintWriter(out);
                        writer.println(alert);
                        writer.flush();

//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    this.hasMessage=false;
                    this.data=null;
                    long time = System.currentTimeMillis() - startTime;
                    Log.d(TAG, "Alert has been sent! Current duration: "+time+"ms");
                }
            }

            Log.d(TAG,"Sending stopped");
        }
    }

    private SendRunnable sendRunnable;
    private Thread sendThread;
    byte[] dataToSend;

    private void startSending(){
        sendRunnable = new SendRunnable(connectionSocket);
        sendThread = new Thread(sendRunnable);
        sendThread.start();
    }

    public boolean isConnected(){
        return connectionSocket != null && connectionSocket.isConnected() && !connectionSocket.isClosed();
    }

    public void writeData(byte[] data){
        if(isConnected()){
            startSending();
            sendRunnable.send(data);
        }
    }


}
