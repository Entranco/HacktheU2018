package e.piepi.shelterapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

public class PetSearch extends AppCompatActivity {
    // Information previously determined by a different scene
    private String[] filters = {"Brown", "German_Shepherd"};
    private int queryCount = 0;
    // all TextViews and Buttons from the App
/*
    private TextView txtNameAge = (TextView) findViewById(R.id.txtNameAge);
    private TextView txtAnimal = (TextView) findViewById(R.id.txtAnimal);
    private TextView txtBreed = (TextView) findViewById(R.id.txtBreed);
    private TextView txtShelter = (TextView) findViewById(R.id.txtShelter);
    private TextView txtDescription = (TextView) findViewById(R.id.txtDescription);
    private Button btnContactShelter = (Button) findViewById(R.id.btnContactShelter);
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_search);

        // creates all of the texts and buttons to be altered
        Button btnNextPet = (Button) findViewById(R.id.btnNextPet);

        btnNextPet.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) {
               String output = "nextPet ";
               for(String el: filters) {
                   output += el + " ";
               }
               output += queryCount;
               new ConnectToServer().execute(output, null, null);
               /*Scanner parser = new Scanner(petInfo);
               txtNameAge.setText(parser.next().replaceAll("_", " "));
               */
           }
        });
    }

    protected class ConnectToServer extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... args){
            try{
                System.out.println();
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
                System.out.println("Caught IOException when connecting to a server!");
            }
            return null;
        }
    }
}
