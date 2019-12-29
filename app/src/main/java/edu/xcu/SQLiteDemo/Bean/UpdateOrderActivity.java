package edu.xcu.SQLiteDemo.Bean;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class UpdateOrderActivity extends AppCompatActivity implements View.OnClickListener{
    //组件定义
    private EditText etOrderid;
    private Button btnSearch;
    private EditText etUsername;
    private EditText etName;
    private EditText etPrice;
    private EditText etAmount;
    private Button btnEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_order);
        initView();
    }

    private void initView() {
        etOrderid=(EditText) findViewById(R.id.et_orderid);
        btnSearch=(Button) findViewById(R.id.btn_search);
        etUsername=(EditText)findViewById(R.id.et_username);
        etName=(EditText)findViewById(R.id.et_name);
        etPrice=(EditText)findViewById(R.id.et_price);
        etAmount=(EditText)findViewById(R.id.et_amount);
        btnEdit= (Button) findViewById(R.id.btn_edit);
        //设置按钮的点击事件
        btnSearch.setOnClickListener((View.OnClickListener) this);
        btnEdit.setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_search:   //查询操作
                searchOrder();
                break;
            case R.id.btn_edit:    //更新操作
                updateOrder();
                break;
        }
    }
    //查询操作
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
    //修改操作
    private void updateOrder() {
        Order o=new Order();
        o.orderid=etOrderid.getText().toString().trim();
        o.username=etUsername.getText().toString().trim();
        o.name=etName.getText().toString().trim();
        o.price=etPrice.getText().toString().trim();
        o.amount=etAmount.getText().toString().trim();
        //创建数据库访问对象
        OrdersDAO dao=new OrdersDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //执行数据库访问方法
        long result= dao.updateOrders(o);
        if(result>0) {
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
        }
        //关闭数据库
        dao.close();
    }
}
