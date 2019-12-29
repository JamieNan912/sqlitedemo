package edu.xcu.SQLiteDemo.Bean;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddgoodsActivity extends AppCompatActivity implements View.OnClickListener {
    //组件定义
    private EditText etOrderid;
    private EditText etUsername;
    private EditText etName;
    private EditText etPrice;
    private EditText etAmount;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        //初始化界面
        initView();
    }

    //初始化界面
    private void initView() {
        etOrderid=(EditText)findViewById(R.id.et_orderid);
        etUsername = (EditText) findViewById(R.id.et_username);
        etName = (EditText) findViewById(R.id.et_name);
        etPrice = (EditText) findViewById(R.id.et_price);
        etAmount = (EditText) findViewById(R.id.et_amount);
        btnAdd = (Button) findViewById(R.id.btn_add);
        //设置按钮的点击事件
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //当单击“添加”按钮时，获取订单信息
        String orderid=etOrderid.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String price = etPrice.getText().toString();
        String amount = etAmount.getText().toString();

        //检验信息是否正确
        if (TextUtils.isEmpty(orderid)) {
            Toast.makeText(this, "请输入订单号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入商品名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(price)) {
            Toast.makeText(this, "请输入单价", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(this, "请输入商品数量", Toast.LENGTH_SHORT).show();
            return;
        }
            //添加订单
            Order o =new Order();
            o.orderid=orderid;
            o.username = username;
            o.name = name;
            o.price = price;
            o.amount = amount;
            //创建数据库访问对象
            OrdersDAO dao = new OrdersDAO(getApplicationContext());
            //打开数据库
            dao.open();
            //执行数据库访问方法
            long result = dao.addOrders(o);

            if (result > 0) {
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
            }
            //关闭数据库
            dao.close();
            //关闭活动
            finish();

        }
    }