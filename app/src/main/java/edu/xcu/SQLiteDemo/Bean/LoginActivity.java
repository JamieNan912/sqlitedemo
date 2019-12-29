package edu.xcu.SQLiteDemo.Bean;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername;
    EditText etUserpwd;
    Button login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //获取各个控件
        etUsername = findViewById(R.id.et_username);
        etUserpwd = findViewById(R.id.et_userpwd);
        login=findViewById(R.id.et_login);
        register=findViewById(R.id.et_register);
        //注册按钮
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,1);
            }
        });
        //登录按钮
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String userpwd = etUserpwd.getText().toString();

                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivityForResult(intent,1);

            }
        });

    }
}
