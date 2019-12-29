package edu.xcu.SQLiteDemo.Bean;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.List;
import java.util.Map;

public class QueryOrderActivity extends AppCompatActivity {
    //定义组件
    ListView listView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_order);
        setTitle("查询订单");
        //初始化界面
        initView();
    }

    private void initView() {
        //建立数据库访问对象
        OrdersDAO dao=new OrdersDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //调用数据库访问方法
        List<Map<String,Object>> mOrderData=dao.getAllOrders();
        //获取组件
        listView=(ListView)findViewById(R.id.lst_orders);
        //定义数据源
        String[] from={"orderid","username","name","price","amount"};
        //定义布局控件ID
        int[] to={R.id.tv_lst_orderid,R.id.tv_lst_username,R.id.tv_lst_name,R.id.tv_lst_price,R.id.tv_lst_amount};
        SimpleAdapter listItemAdapter=new SimpleAdapter(QueryOrderActivity.this,mOrderData,R.layout.item_list,from,to);
        //添加并显示
        listView.setAdapter(listItemAdapter);
        //关闭数据库
        dao.close();
    }
}
