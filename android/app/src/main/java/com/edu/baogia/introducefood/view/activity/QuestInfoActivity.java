package com.edu.baogia.introducefood.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.model.object.Quest;


public class QuestInfoActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView tvAnswer,tvQuest,tvTime,tvCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_info);
        tvAnswer=findViewById(R.id.tvAnswer);
        tvQuest=findViewById(R.id.tvQuest);
        tvTime=findViewById(R.id.tvTime);
        tvCheck=findViewById(R.id.tvCheck);
        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Nội dung câu hỏi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent=getIntent();
        Quest quest= (Quest) intent.getSerializableExtra("Quest");
        if(quest!=null)
        {
            tvQuest.setText("Câu hỏi : "+quest.getQuest());
            if(quest.getAnswer()!=null&&!quest.getAnswer().trim().equals("null"))
            {
                tvAnswer.setText("Câu trả lời : "+quest.getAnswer());
            }
            else
            {
                tvAnswer.setText("Chưa có câu trả lời");
            }

            tvTime.setText("Thời gian : "+quest.getTime());
            Log.d("AAA", String.valueOf(quest.isCheck()));
            if(quest.isCheck())
            {
                tvCheck.setText("Trạng thái : Đã trả lời");
            }
            else
            {
                tvCheck.setText("Trạng thái : Chưa trả lời");
            }

        }
        else
        {
            onBackPressed();
            Toast.makeText(this, "Không có nội dung", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}