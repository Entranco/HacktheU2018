package e.piepi.shelterapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

public class PetSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_search);


        Button btnNextPet = (Button) findViewById(R.id.btnNextPet);
        btnNextPet.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) {
               new ConnectToServer().execute(null, null, null);
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

                DataInputStream in = new DataInputStream(client.getInputStream());
                System.out.println(in.readUTF());
            }catch(IOException ex) {
                System.out.println("Caught IOException when connecting to a server!");
            }
            return null;
        }
    }
}
