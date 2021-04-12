package com.example.multipage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.cert.TrustAnchor;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1 , b2;
    EditText username,password;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.login);
        b2 = (Button)findViewById(R.id.register);
        username =(EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        DB = new DBHelper(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                String name = username.getText().toString();
                String pwd = password.getText().toString();

                if(!name.equals("")) {
                    if (!pwd.equals("")) {
                        Boolean checkuser = DB.checkUSernamePasword(name, pwd);
                        if (checkuser == true) {
                            Intent i = new Intent(getApplicationContext(), MainActivity3.class);
                            startActivity(i);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Please Enter password", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Please Enter Usrname", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.register:
                Intent i = new Intent(getApplicationContext() , MainActivity2.class);
                startActivity(i);
                break;
        }
    }
}