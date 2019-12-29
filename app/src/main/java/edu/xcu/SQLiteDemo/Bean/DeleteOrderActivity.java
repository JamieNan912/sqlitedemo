package edu.xcu.SQLiteDemo.Bean;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteOrderActivity extends AppCompatActivity  implements View.OnClickListener{

    private EditText etOrderid;
    private Button btnSearch;
    private EditText etUsername;
    private EditText etName;
    private EditText etPrice;
    private EditText etAmount;
    private Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_order);
        initView();
    }

    private void initView() {
        etOrderid=(EditText) findViewById(R.id.et_orderid);
        btnSearch=(Button) findViewById(R.id.btn_search);
        etUsername=(EditText)findViewById(R.id.et_username);
        etName=(EditText)findViewById(R.id.et_name);
        etPrice=(EditText)findViewById(R.id.et_price);
        etAmount=(EditText)findViewById(R.id.et_amount);
        btnDelete= (Button) findViewById(R.id.btn_delete);
        //设置按钮的点击事件
        btnSearch.setOnClickListener((View.OnClickListener) this);
        btnDelete.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_search:   //查询操作
                searchOrder();
                break;
            case R.id.btn_delete:    //删除操作
                deleteOrder();
                break;
        }
    }
    //查找订单
    private void searchOrder() {
        String orderid=etOrderid.getText().toString().trim();  //获取用户输入
        //建立数据库访问对象
        OrdersDAO dao=new OrdersDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //调用数据库访问方法
        Order o=dao.getOrders(orderid);
        //将数据填入控件
        etUsername.setText(o.username);
        etName.setText(o.name);
        etPrice.setText(o.price);
        etAmount.setText(o.amount);
        //关闭数据库
        dao.close();
    }
    //删除订单
    private void deleteOrder() {
        Order o=new Order();
        o.orderid=etOrderid.getText().toString().trim();
        //创建数据库访问对象
        OrdersDAO dao=new OrdersDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //执行数据库访问方法
        int result= dao.deletOrders(o);
        if(result>0) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
        }
        //关闭数据库
        dao.close();
    }
}
