package e.piepi.shelterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FrontPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        createBtnLogIn();
    }

    private void createBtnLogIn() {
        Button btnLogIn = (Button) findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) {
               startActivity(new Intent(FrontPage.this, LogIn.class));
           }
        });
    }
}
