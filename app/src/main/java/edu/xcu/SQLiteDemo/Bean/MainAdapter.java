package edu.xcu.SQLiteDemo.Bean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {
    int[] imageId = {R.drawable.add, R.drawable.edit, R.drawable.delete, R.drawable.search};
    String[] names = {"添加订单", "修改订单", "删除订单", "查找订单"};
    private Context context;

    public MainAdapter(Context content) {
        this.context = content;
    }

    //设置GridView一共有多少条目
    @Override
    public int getCount() {
        return imageId.length;
    }

    //设置每个条目的界面
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.item_main, null);
        ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        iv_icon.setImageResource(imageId[position]);
        tv_name.setText(names[position]);

        return view;
    }

    @Override
    public Object getItem(int possition) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}