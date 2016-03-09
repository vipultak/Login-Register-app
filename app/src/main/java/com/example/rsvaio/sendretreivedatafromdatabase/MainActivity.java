package com.example.rsvaio.sendretreivedatafromdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bSend,bLogin;
    EditText tvName,tvAddress;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = (EditText)findViewById(R.id.tvName);
        tvAddress = (EditText)findViewById(R.id.tvAddress);
        bSend = (Button)findViewById(R.id.bSend);
        bLogin = (Button)findViewById(R.id.bLogin);
        tv1 = (TextView)findViewById(R.id.tv1);
        bSend.setOnClickListener(this);
        bLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = tvName.getText().toString();
        String address = tvAddress.getText().toString();
        switch(v.getId()){
            case R.id.bSend:
                Post post = new Post(this);
                post.execute(name,address);
                break;
            case R.id.bLogin:
                Login login = new Login(this);

                try {
                    String data = login.execute(name,address).get();
                    tv1.setText(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
        }

    }
}
