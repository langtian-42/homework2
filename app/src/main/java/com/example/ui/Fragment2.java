package com.example.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment2 extends Fragment {
    //获取recyclerView对象并且实例化适配器
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fra_lx, container, false);
        View view;
        //存所有控件的视图
        view=inflater.inflate(R.layout.tab2, container, false);
        //调用recycleview控件
        recyclerView=view.findViewById(R.id.recyclerView);
        //创建数据
        String[] names={"爸爸","妈妈","姐姐","弟弟","二叔","张三","李四","王五","刘雯","刘星"};
        int[] images={R.drawable.baba,R.drawable.mama,R.drawable.jiejie,R.drawable.didi,R.drawable.ershu,
                R.drawable.zs,R.drawable.ls,R.drawable.ww,R.drawable.lw,R.drawable.lx};
        String[] phones={"13420827777","13423459999","18920203433","13712930000","13611119898",
        "17238389999","17326357489","13482930203","13619872034","18923458976"};
        String[] regions={"广东 佛山","广东 佛山","广东 佛山","广东 佛山","广东 佛山","湖北 武汉","湖北 武汉",
        "北京","上海","广东 深圳"};
        String[] tags={"家人","家人","家人","家人","家人",
        "同学","同学","同学","同学","同学"};
        List<Map<String,Object>> items=new ArrayList<Map<String,Object>>();
        for(int i=0;i<names.length;i++){
            Map<String,Object> item=new HashMap<String, Object>();
            item.put("i_name",names[i]);
            item.put("i_image",images[i]);
            item.put("i_phone",phones[i]);
            item.put("i_region",regions[i]);
            item.put("i_tag",tags[i]);
            items.add(item);
        }
        //创建RecycleView实例和设置Adapter
        Context context=getContext();
        myAdapter=new MyAdapter(items,context);
        LinearLayoutManager manager=new LinearLayoutManager(context);
        manager.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(myAdapter);

        //实现拖拽和左滑删除效果
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int swiped=ItemTouchHelper.LEFT;
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                return makeMovementFlags(dragFlags,swiped);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int oldPosition = viewHolder.getAdapterPosition();
                int newPosition = target.getAdapterPosition();
                if (oldPosition < newPosition) {
                    for (int i = oldPosition; i < newPosition; i++) {
                        // 改变数据集
                        Collections.swap(items, i, i +1);
                    }
                } else {
                    for (int i = oldPosition; i > newPosition; i--) {
                        // 改变数据集
                        Collections.swap(items, i, i - 1);
                    }
                }
                myAdapter.notifyItemMoved(oldPosition, newPosition);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                items.remove(position);
                myAdapter.notifyItemRemoved(position);
            }
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState!=ItemTouchHelper.ACTION_STATE_IDLE){
                    viewHolder.itemView.setBackgroundColor(Color.parseColor("#04BE02"));
                }
            }
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
            }
        });
        //关联recycleView
        itemTouchHelper.attachToRecyclerView(recyclerView);
        return view;
    }
}