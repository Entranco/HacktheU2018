package e.piepi.shelterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        createBtnSubmit();
    }

    private void createBtnSubmit() {
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // creates the EditText objects to find their text fields
                EditText editUser = (EditText) findViewById(R.id.editUser);
                EditText editPass = (EditText) findViewById(R.id.editPass);

                String output = editUser.getText() + " " + editPass.getText();
                // sends the info to the server
                try {
                    String info = new ConnectToServer().execute(output, null, null).get();
                } catch (Exception ex) {
                }

                startActivity(new Intent(LogIn.this, PetSearch.class));
            }
        });
    }
}