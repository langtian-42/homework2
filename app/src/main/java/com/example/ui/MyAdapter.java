package com.example.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui.R;

import java.util.List;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter <MyAdapter.MyViewHolder>{
    //定义存储数据和运行环境的变量
    private List<Map<String,Object>> mydata;
    private Context mycontext;

    //获取数据和运行环境
    public MyAdapter(List<Map<String,Object>> data, Context context){
        mydata=data;
        mycontext=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mycontext).inflate(R.layout.item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        String name=mydata.get(position).get("i_name").toString();
        int image=Integer.parseInt(mydata.get(position).get("i_image").toString());
        //获取详情页面中某个联系人的对应数据
        String phone=mydata.get(position).get("i_phone").toString();
        String region=mydata.get(position).get("i_region").toString();
        String tag=mydata.get(position).get("i_tag").toString();
        holder.textView.setText(name);
        holder.imageView.setImageResource(image);
        //添加点击事件
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击后跳转到联系人详情页
                Intent intent=new Intent(mycontext,LianxiDetails.class);
                //向intent传值
                intent.putExtra("details",name);
                intent.putExtra("image",image);
                intent.putExtra("phone",phone);
                intent.putExtra("region",region);
                intent.putExtra("tag",tag);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //开始跳转
                mycontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mydata.size();
    }
    public  class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //获取item中的控件id
            textView=itemView.findViewById(R.id.text_hhh);
            imageView=itemView.findViewById(R.id.image_hhh);
        }

    }
}
