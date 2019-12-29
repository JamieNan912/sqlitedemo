package edu.xcu.SQLiteDemo.Bean;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //控件声明
    private GridView gv_main;
    //退出时的时间
    private long mExitTime;
    //开启新的Activity
    public void startActivity(Class<?> cls)
    {
        Intent intent=new Intent(MainActivity.this,cls);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化布局
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //初始化控件
        gv_main=(GridView)findViewById(R.id.gv_home);
        gv_main.setAdapter(new MainAdapter(MainActivity.this));
        //设置条目单击事件
        gv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        //添加订单
                        startActivity(AddgoodsActivity.class);
                        break;
                    case 1:
                        //修改订单
                        startActivity(UpdateOrderActivity.class);
                        break;
                    case 2:
                        //删除订单
                        startActivity(DeleteOrderActivity.class);
                        break;
                    case 3:
                        //查询订单
                        startActivity(QueryOrderActivity.class);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(System.currentTimeMillis()-mExitTime<2000){
                System.exit(0);
            }else{
                Toast.makeText(MainActivity.this,"再按一次退出程序",Toast.LENGTH_LONG);
                mExitTime=System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}
