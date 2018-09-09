package e.piepi.shelterapp;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectToServer extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... args){
        try{
            System.out.println("Ran Connect to Server");
            Socket client = new Socket("10.0.2.2", 8888);
            System.out.println("connected successfully to " + client.getRemoteSocketAddress());


            OutputStream outStream = client.getOutputStream();
            DataOutputStream dataOut = new DataOutputStream(outStream);
            dataOut.writeUTF(args[0]);
            System.out.println("Sent " + args[0] + " to server");

            DataInputStream in = new DataInputStream(client.getInputStream());
            String serverInfo = in.readUTF();
            System.out.println("Received " + serverInfo + " from server");
            return serverInfo;
        }catch(IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}