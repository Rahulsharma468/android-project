package com.example.multipage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    Button b1 , b2;
    EditText user , eml , pwd , cnfpwd;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b1 = (Button)findViewById(R.id.login);
        b2 = (Button)findViewById(R.id.register);

        user = (EditText)findViewById(R.id.username);
        eml = (EditText)findViewById(R.id.email);
        pwd = (EditText)findViewById(R.id.password);
        cnfpwd = (EditText)findViewById(R.id.cnfPassword);
        DB = new DBHelper(this);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                String name = user.getText().toString();
                String email = eml.getText().toString();
                String pwdd = pwd. getText().toString();
                String cnfPwd = cnfpwd.getText().toString();

                if(pwdd.equals(cnfPwd)) {
                    Boolean ckeckUser = DB.checkUSernamePasword(name, pwdd);
                    if (ckeckUser == false) {
                        Boolean insert = DB.insertData(name, email, pwdd, cnfPwd);
                        if (insert == true) {
                            Toast.makeText(getApplicationContext(), "Successfully done", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), MainActivity3.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "insert failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "User exists", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Match error pwd and cnfpwd", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login:
                Intent i = new Intent(getApplicationContext() , MainActivity.class);
                startActivity(i);
                break;
        }
    }
}