package com.edu.baogia.introducefood.view.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.adapter.ReviewAdapter;
import com.edu.baogia.introducefood.interfaces.ItemClickListenerReview;
import com.edu.baogia.introducefood.interfaces.ReviewMVP;
import com.edu.baogia.introducefood.model.mySQL.ReviewModel;
import com.edu.baogia.introducefood.model.object.Review;
import com.edu.baogia.introducefood.presenter.ReviewPresenter;

import java.util.List;

public class ReviewActivity extends AppCompatActivity implements ReviewMVP.View{
    RecyclerView recyclerView;
    Toolbar toolbar;
    ReviewMVP.Presenter presenter;
    ProgressDialog progressDialog;
    TextView tvNoReview;
    LinearLayout layoutAdd;
    AlertDialog alertDialog;
    EditText editText,edtUpdate;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        recyclerView=findViewById(R.id.listReview);
        tvNoReview=findViewById(R.id.tvNoReview);
        layoutAdd=findViewById(R.id.layoutAdd);
        toolbar=findViewById(R.id.toolBar);

        id = getIntent().getIntExtra("idfood", 0);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Review");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        layoutAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.getAccount()!=null)
                {
                    popUp();
                }
                else
                {
                    AlertDialog.Builder dialog=new AlertDialog.Builder(ReviewActivity.this);
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
            }
        });
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Đang tải");
        progressDialog.setMessage("Loading");
        progressDialog.show();
        presenter=new ReviewPresenter(this,new ReviewModel(this));
        presenter.getListReview(id);

    }

    @Override
    public void setList(List<Review> list) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ReviewAdapter reviewAdapter=new ReviewAdapter(this, list, R.layout.line_list_review, new ItemClickListenerReview() {
            @Override
            public void itemClickListener(Review userReview, View view) {
                PopupMenu pm = new PopupMenu(getApplicationContext(),view);
                pm.getMenuInflater().inflate(R.menu.review_popup, pm.getMenu());
                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId()==R.id.update)
                        {
                            popUpUpdate(userReview);
                        }
                        else if(menuItem.getItemId()==R.id.delete)
                        {
                            delete(userReview);
                        }
                        return true;
                    }
                });
                pm.show();
            }
        },presenter.getAccount());
        recyclerView.setAdapter(reviewAdapter);
        recyclerView.setAdapter(reviewAdapter);
        progressDialog.dismiss();
        tvNoReview.setVisibility(View.GONE);
    }

    @Override
    public void onFail() {
        progressDialog.dismiss();
        recyclerView.setAdapter(null);
        tvNoReview.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAddFail() {
        Toast.makeText(getApplicationContext(), "Đăng tải thất bại", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }

    @Override
    public void onAddSuccess() {
        Toast.makeText(getApplicationContext(), "Đăng tải thành công", Toast.LENGTH_SHORT).show();
        presenter.getListReview(-1);
    }

    @Override
    public void onUpdateFail() {
        Toast.makeText(getApplicationContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }

    @Override
    public void onUpdateSuccess() {
        Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        presenter.getListReview(-1);
    }

    @Override
    public void onDeleteFail() {
        Toast.makeText(getApplicationContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }

    @Override
    public void onDeleteSuccess() {
        Toast.makeText(getApplicationContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
        presenter.getListReview(-1);
    }

    private void popUp() {
        LayoutInflater layoutInflater=getLayoutInflater();
        View view= layoutInflater.inflate(R.layout.popup_review,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(ReviewActivity.this);
        builder.setView(view);
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        builder.setPositiveButton("Đăng", null);
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
                            Toast.makeText(getApplicationContext(), "Hãy viết gì đó ...", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            alertDialog.dismiss();
                            progressDialog.show();
                            presenter.addReview(presenter.getAccount(),-1,editText.getText().toString());
                        }
                    }
                });
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        editText=view.findViewById(R.id.editTextTextMultiLine3);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void popUpUpdate(Review userReview) {
            LayoutInflater layoutInflater=getLayoutInflater();
            View view= layoutInflater.inflate(R.layout.popup_update_review,null);
            AlertDialog.Builder builder=new AlertDialog.Builder(ReviewActivity.this);
            builder.setView(view);
            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.dismiss();
                }
            });
            builder.setPositiveButton("Cập nhật", null);
            alertDialog=builder.create();
            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    Button button = ((AlertDialog) alertDialog).getButton(AlertDialog.BUTTON_POSITIVE);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(edtUpdate.length()==0)
                            {
                                Toast.makeText(getApplicationContext(), "Hãy điền nội dung", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                userReview.setText(edtUpdate.getText().toString());
                                presenter.updateReview(userReview);
                                progressDialog.show();
                                alertDialog.dismiss();
                            }

                        }
                    });
                }
            });
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
            edtUpdate=view.findViewById(R.id.editTextTextMultiLine3);
            edtUpdate.setText(userReview.getText());
    }
    public void delete(Review userReview)
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(ReviewActivity.this);
        dialog.setNegativeButton("Từ chối", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        dialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.deleteReview(userReview);
                progressDialog.show();
            }
        });
        dialog.setTitle("Xóa review");
        dialog.setMessage("Bạn có muốn xóa review ?");
        dialog.show();
    }


}