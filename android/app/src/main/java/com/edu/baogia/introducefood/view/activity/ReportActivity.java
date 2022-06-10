package com.edu.baogia.introducefood.view.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.interfaces.ReportMVP;
import com.edu.baogia.introducefood.model.mySQL.ReportModel;
import com.edu.baogia.introducefood.presenter.ReportPresenter;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;

public class ReportActivity extends AppCompatActivity implements ReportMVP.View {
    EditText edtTitle,edtDesc;
    Button btnPush,btnPick;
    ImageView imageView;
    Bitmap bitmap=null;
    Toolbar toolbar;
    ProgressDialog progressDialog;
    ReportMVP.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        edtTitle=findViewById(R.id.edtTitle);
        edtDesc=findViewById(R.id.edtDesc);
        btnPush=findViewById(R.id.btnPush);
        btnPick=findViewById(R.id.btnPick);
        imageView=findViewById(R.id.imgReport);
        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Báo lỗi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        progressDialog=new ProgressDialog(ReportActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Đang tải");
        progressDialog.setMessage("Loading...");
        presenter=new ReportPresenter(this,new ReportModel(this));
        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        pickImg();
                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                        Toast.makeText(getApplicationContext(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                    }

                };
                TedPermission.create()
                        .setPermissionListener(permissionlistener)
                        .setDeniedMessage("Nếu bạn từ chối quyền, bạn không thể sử dụng dịch vụ này\n\nVui lòng bật quyền tại [Setting]> [Permission]")
                        .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();
            }
        });
        btnPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtTitle.length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Title không được để trống ", Toast.LENGTH_SHORT).show();
                }
                else if(edtDesc.length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Nội dung không được để trống ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressDialog.show();
                    presenter.addReport(edtTitle.getText().toString(),edtDesc.getText().toString(),bitmap);
                }
            }
        });
    }

    @Override
    public void onSuccess() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(ReportActivity.this);
        dialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                edtTitle.setText(null);
                edtDesc.setText(null);
                bitmap=null;
                progressDialog.dismiss();
            }
        });
        dialog.setTitle("Thành công");
        dialog.setMessage("Cảm ơn bạn đã gửi báo cáo cho chúng tôi, chúng tôi sẽ cố gắng khắc phục trong thời gian sớm nhất !");
        dialog.show();
    }

    @Override
    public void onFail() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(ReportActivity.this);
        dialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                progressDialog.dismiss();
            }
        });
        dialog.setTitle("Thất bại");
        dialog.setMessage("Cảm ơn bạn đã gửi báo cáo cho chúng tôi, nhưng đã có lỗi trong lúc bạn gửi báo cáo, vui lòng thử lại !");
        dialog.show();
    }

    private void pickImg() {
        TedBottomPicker.with(ReportActivity.this)
                .show(new TedBottomSheetDialogFragment.OnImageSelectedListener() {
                    @Override
                    public void onImageSelected(Uri uri) {
                        try {
                            InputStream inputStream=getContentResolver().openInputStream(uri);
                            Bitmap b = BitmapFactory.decodeStream(inputStream);
                            imageView.setImageBitmap(bitmap);
                            bitmap=b;

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}