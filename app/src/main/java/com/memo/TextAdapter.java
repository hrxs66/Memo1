package com.memo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder> {
    Context mContext;
    private List<Text> mTextList;

    //将要展示的数据源传进来
    public TextAdapter(List<Text> textList, Context mContext) {
        this.mContext = mContext;
        this.mTextList = textList;
    }

    //定义内部类
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView texttitle;
        TextView texttime;

        //获取实例
        public ViewHolder(View view) {
            super(view);
            texttitle = (TextView) view.findViewById(R.id.title);
            texttime = (TextView) view.findViewById(R.id.time);
        }
    }

    //重写三个方法
    //1.创建实例
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(mContext, R.layout.text, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    //对recyclerView子项进行赋值
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Text text = mTextList.get(position);
        //Log.i(String.valueOf(position), String.valueOf(text));
        holder.texttitle.setText(text.getTitle());
        holder.texttime.setText(String.valueOf(text.getTime()));

        holder.texttitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, first.class);
                intent.putExtra("title", text);
                mContext.startActivity(intent);
                Activity main = (Activity) mContext;
                main.finish();
            }
        });

    }

    //返回数据源长度
    @Override
    public int getItemCount() {
        return mTextList.size();
    }
}
