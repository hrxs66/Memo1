package com.memo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.usage.UsageEvents;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Text> textList = new ArrayList<>();
    private RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //创建主页面和recyclerview的展示。

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCourse();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        TextAdapter adapter = new TextAdapter(LitePal.findAll(Text.class), this);
        recyclerView.setAdapter(adapter);

    }

    public void newone(View view) {
        //创建新备忘录
        Intent intent = new Intent(this, first.class);
        startActivity(intent);
        finish();
    }

    private void initCourse() {
        //初始化
        Text text = new Text();
        first first = new first();
        text = first.pass();
        textList.add(text);
    }


}


