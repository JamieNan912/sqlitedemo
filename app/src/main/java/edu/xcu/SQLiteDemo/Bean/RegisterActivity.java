package edu.xcu.SQLiteDemo.Bean;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private MyDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //获取控件
        final ImageView image=findViewById(R.id.image);
        final EditText etUsername = findViewById(R.id.et_username);
        final EditText etUserpwd = findViewById(R.id.et_userpwd);
        final Button register=findViewById(R.id.et_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String userpwd = etUserpwd.getText().toString().trim();
                if (TextUtils.isEmpty(username)){
                    Toast.makeText(getApplicationContext(), "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(userpwd)){
                    Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                    //注册用户
                    User u =new User();
                    u.username = username;
                    u.userpwd = userpwd;
                    //创建数据库访问对象
                    OrdersDAO dao = new OrdersDAO(getApplicationContext());
                    //打开数据库
                    dao.open();
                    //执行数据库访问方法
                    long result = dao.registerUsers(u);

                    if (result > 0) {
                        Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_LONG).show();
                    }
                    //关闭数据库
                    dao.close();
                    //关闭活动
                    finish();
            }
        });
    }

}
