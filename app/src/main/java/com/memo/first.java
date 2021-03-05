package com.memo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;
import static java.lang.Thread.sleep;

public class first extends AppCompatActivity {
    int flag = 1;
    String which1;
    int id = 1;
    SimpleDateFormat fo = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    private List<Text> mTextList;
    private EditText editText;
    private EditText editText1;
    public Text d;
    public Text A;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        final Button save = findViewById(R.id.save);

        d = (Text) getIntent().getSerializableExtra("title");
        editText = (EditText) findViewById(R.id.title);
        editText1 = (EditText) findViewById(R.id.text);
        if (d != null)//如果d不为null说明是点击列表进来的，需要传入数据
        {
            //改变控件的数据
            Log.i(d.getTitle(), d.getText());
            editText.setText(d.getTitle());
            editText1.setText(d.getText());
            Log.i(d.getTitle(), d.getText());
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
                String title = editText.getText().toString();
                String text = editText1.getText().toString();

                if (title.isEmpty()) {
                    Toast.makeText(first.this, "请输入标题", LENGTH_SHORT).show();
                    flag = 0;
                }
                if (text.isEmpty()) {
                    Toast.makeText(first.this, "请输入正文", LENGTH_SHORT).show();
                    flag = 0;
                }//小提醒

                if (flag == 1) {
                    String date1 = fo.format(new Date(System.currentTimeMillis()));
                    if (d != null)//如果d不为null说明是点击列表进来的，更新数据库
                    {
                        //Date date = new Date(System.currentTimeMillis());
                        int id = d.getId();
                        d.setTime(date1);
                        d.setKind(which1);
                        d.setText(editText1.getText().toString());
                        d.setTitle(editText.getText().toString());
                        d.update(id);
                        A = new Text(id, title, date1, text, which1);

                    } else {
                        //增加
                        Text text1 = new Text();
                        text1.setId(id);
                        text1.setTitle(title);
                        text1.setText(text);
                        text1.setKind(which1);
                        //Date date = new Date(System.currentTimeMillis());
                        text1.setTime(date1);
                        text1.save();
                        A = new Text(id, title, date1, text, which1);

                    }

                    Toast.makeText(first.this, "保存成功", LENGTH_SHORT).show();
                    ++id;
                    Intent intent = new Intent(first.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


    public Text pass() {
        return A;  //将数据传递到MainActivity，使其能够在recyclerview中展示。
    }

    public void kind(View view) {
        //设置备忘录的分类
        new AlertDialog.Builder(first.this).setTitle("请选择分类")
                //.setIcon(R.mipmap.ic_launcher)  //图标
                .setPositiveButton("yes", null)
                .setSingleChoiceItems(new String[]{"生活", "工作", "学习", "临时"}, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) which1 = "生活";
                        else if (which == 1) which1 = "工作";
                        else if (which == 2) which1 = "学习";
                        else which1 = "临时";
                    }
                })
                .create().show();
    }

    public void delete(View view) {
        //删除时的确认
        AlertDialog.Builder dialog = new AlertDialog.Builder(this).setTitle("Dialog对话框");
        dialog
                .setMessage("是否确定删除")
                //.setIcon(R.mipmap.ic_launcher)  //图标
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    //启动一个监听事件
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        godelete();
                    }
                })
                .setNegativeButton("no", null)
                .create().show();
    }


    public void godelete() {
        //实现删除
        if (d != null)//如果d不为null说明是点击列表进来的，删除数据库中数据。
        {
            int id = d.getId();
            LitePal.delete(Text.class, id);
            Intent intent = new Intent(first.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else //如果d为空的话，说明是点添加进来的，只需要返回即可
        {
            Intent intent = new Intent(first.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        //防止误退 导致数据丢失。
        Toast.makeText(this, "请您选择保存或者删除", Toast.LENGTH_SHORT).show();
    }
}





