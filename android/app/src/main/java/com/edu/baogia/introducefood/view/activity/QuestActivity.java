package com.edu.baogia.introducefood.view.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.adapter.QuestAdapter;
import com.edu.baogia.introducefood.interfaces.ItemClickListenerQuest;
import com.edu.baogia.introducefood.interfaces.QuestMVP;
import com.edu.baogia.introducefood.model.mySQL.QuestModel;
import com.edu.baogia.introducefood.model.object.Quest;
import com.edu.baogia.introducefood.presenter.QuestPresenter;


import java.util.List;

public class QuestActivity extends AppCompatActivity implements QuestMVP.View {
    RecyclerView recyclerView;
    Toolbar toolbar;
    QuestMVP.Presenter presenter;
    EditText editText;
    AlertDialog alertDialog;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quest);
        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Danh sách câu hỏi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView=findViewById(R.id.listQuest);
        progressDialog=new ProgressDialog(QuestActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Đang tải");
        progressDialog.setMessage("Loading...");
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        presenter=new QuestPresenter(this,new QuestModel(this));
        presenter.getListQuest();
    }

    @Override
    public void setList(List<Quest> list) {
        recyclerView.setVisibility(View.VISIBLE);
        QuestAdapter questAdapter=new QuestAdapter(this, list, R.layout.line_quest, new ItemClickListenerQuest() {
            @Override
            public void itemClickListener(Quest quest) {
                Intent intent = new Intent(QuestActivity.this, QuestInfoActivity.class);
                intent.putExtra("Quest", quest);
                startActivity(intent);
            }
        }, new ItemClickListenerQuest() {
            @Override
            public void itemClickListener(Quest quest) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(QuestActivity.this);
                dialog.setNegativeButton("Từ chối", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            presenter.deleteQuest(quest);
                            progressDialog.show();
                    }
                });
                dialog.setTitle("Xóa câu hỏi");
                dialog.setMessage("Bạn có muốn xóa câu hỏi "+quest.getQuest()+" ?");
                dialog.show();
            }
        });
        recyclerView.setAdapter(questAdapter);
        progressDialog.dismiss();
    }

    @Override
    public void onFail() {
        Toast.makeText(this, "Không có câu hỏi", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onAddSuccess() {
        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
        presenter.getListQuest();
    }

    @Override
    public void onAddFail() {
        Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }

    @Override
    public void onDeleteSuccess() {
        Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
        presenter.getListQuest();
    }

    @Override
    public void onDeleteFail() {
        Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_quest,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.addQuest:
            {
                if(presenter.getKeyUser()==null)
                {
                    AlertDialog.Builder dialog=new AlertDialog.Builder(QuestActivity.this);
                    dialog.setNegativeButton("Từ chối", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog.setTitle("Bạn chưa đăng nhập");
                    dialog.setMessage("Bạn có muốn đăng nhập ?");
                    dialog.show();
                }
                else
                {
                    showDialogAdd();
                }
                return true;
            }
            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    private void showDialogAdd() {
        LayoutInflater layoutInflater=getLayoutInflater();
        View view= layoutInflater.inflate(R.layout.popup_quest,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(QuestActivity.this);
        builder.setView(view);
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        builder.setPositiveButton("Gửi", null);
        alertDialog=builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button button = ((AlertDialog) alertDialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(editText.length()==0)
                        {
                            Toast.makeText(getApplicationContext(), "Hãy nhập câu hỏi", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            alertDialog.dismiss();
                            progressDialog.show();
                            presenter.addQuest(editText.getText().toString());
                        }
                    }
                });
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        editText=view.findViewById(R.id.edtTitle);
    }
}