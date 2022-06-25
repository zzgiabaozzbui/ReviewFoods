package com.edu.baogia.introducefood.view.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.interfaces.HoSoInterface;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.model.object.NguoiDung;
import com.edu.baogia.introducefood.presenter.HoSoPresenter;
import com.edu.baogia.introducefood.util.MySharedPreferences;
import com.edu.baogia.introducefood.util.UrlVolley;
import com.edu.baogia.introducefood.util.idwifi;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

public class HoSoView extends AppCompatActivity implements HoSoInterface {
    LinearLayout lnlIdHoSo,lnlTenDayDuHoSo,lnlNgaySinhHoSo,lnlEmailHoSo,lnlGioiTinhHoSo,lnlSdtHoSo;
    TextView txtIdHoSo,txtTenDayDuHoSo,txtNgaySinhHoSo,txtEmailHoSo,txtGioiTinhHoSo,txtSdtHoSo;
    ImageView imgAvatarHoSo;
    Button btnAcceptHoSo,btnReturnHomeHoSo;
    DatePickerDialog datePickerDialog;
    Bitmap bitmap;

    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;

    final int CODE_GALLERY_REQUEST=999;

    String tenTK="";

    HoSoPresenter hoSoPresenter=new HoSoPresenter(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTenTaiKhoan();
        setContentView(R.layout.activity_ho_so);
        mapping();
        seederTime();
        getDataHoSoCurrent();
        acceptChangeData();
        returnHome();
        setAvatar();

    }

    private void returnHome() {
        btnReturnHomeHoSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setTenTaiKhoan() {
        try {
            AccountRemember accountRemember=new  MySharedPreferences().getRememberAcc(HoSoView.this);
            tenTK=accountRemember.getUsername();
        }catch (Exception e){

        }
    }

    private void setAvatar() {
        imgAvatarHoSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(HoSoView.this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE},CODE_GALLERY_REQUEST);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode== CODE_GALLERY_REQUEST){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Chọn ảnh"),CODE_GALLERY_REQUEST);
            }else {
                Toast.makeText(HoSoView.this, "Bạn chưa cho phép quyền truy cập vào thư viện ảnh", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==CODE_GALLERY_REQUEST && resultCode== RESULT_OK && data!=null){
            Uri filePath=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(filePath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                imgAvatarHoSo.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    private void acceptChangeData() {
        btnAcceptHoSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hoSoPresenter.changeDataHoSo();
            }
        });
    }

    private void getDataHoSoCurrent() {
        if(tenTK.equalsIgnoreCase("")){
            Intent intent=new Intent(HoSoView.this,LoginActivity.class);
            Toast.makeText(HoSoView.this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        hoSoPresenter.getDataHoSo(new NguoiDung.CallBackHoSo() {
            @Override
            public void onSuccessHoSo(NguoiDung nguoiDung) {
                txtIdHoSo.setText(nguoiDung.getId()+"");
                txtTenDayDuHoSo.setText(nguoiDung.getTenDayDu());
//                ảnh đại diện
                txtNgaySinhHoSo.setText(nguoiDung.getNgaySinh().substring(0,10));
                txtEmailHoSo.setText(nguoiDung.getEmail());
                String gioiTinh="Nam";
                if(nguoiDung.getGioiTinh()==1){
                    gioiTinh="Nữ";
                }else if(nguoiDung.getGioiTinh()==2){
                    gioiTinh="Khác";
                }
                Picasso.get().load(new idwifi().getUrlThangImage+nguoiDung.getAnhDaiDien()).error(R.drawable.meo_meo).into(imgAvatarHoSo);
                txtGioiTinhHoSo.setText(gioiTinh);
                txtSdtHoSo.setText(nguoiDung.getSdt());
            }

            @Override
            public void onErrorHoSo(String error) {

            }
        });
    }

    private void seederTime() {
        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = c.get(Calendar.YEAR);
        this.lastSelectedMonth = c.get(Calendar.MONTH);
        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);
    }

    public void hoTenLinnear(View view){
        createDialogType("Đặt tên",txtTenDayDuHoSo.getText().toString(),txtTenDayDuHoSo,InputType.TYPE_CLASS_TEXT);
    }

    public void emailLinear(View view){
        createDialogType("Email",txtEmailHoSo.getText().toString(),txtEmailHoSo,InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);
    }

    public void sdtlLinear(View view){
        createDialogType("Số điện thoại",txtSdtHoSo.getText().toString(),txtSdtHoSo,InputType.TYPE_CLASS_PHONE);
    }

    public void changeGender(View view){
        createDialogGioiTinh(txtGioiTinhHoSo);
    }

    public void addDate(View view){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                txtNgaySinhHoSo.setText(i+"-"+(i1+1)+"-"+i2);
                lastSelectedYear = i;
                lastSelectedMonth = i1;
                lastSelectedDayOfMonth = i2;
            }
        };
        datePickerDialog = new DatePickerDialog(this, dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        //datePickerDialog = new DatePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog_NoActionBar,dateSetListener,lastSelectedYear,lastSelectedMonth,lastSelectedDayOfMonth);
        datePickerDialog.show();
    }

    public void createDialogType(String title, String content, TextView t, int inputType){
        LayoutInflater inflater=LayoutInflater.from(HoSoView.this);
        View view=inflater.inflate(R.layout.activity_alert_dialog_hoso,null);
//         các nút trong alert

        Button btnHuyAlertHoSo=view.findViewById(R.id.btnHuyAlertHoSo);
        Button btnXacNhanAlertHoSo=view.findViewById(R.id.btnXacNhanAlertHoSo);
        TextView txtTitleHoSo=view.findViewById(R.id.txtTitleHoSo);
        EditText txtContentHoSo=view.findViewById(R.id.txtContentHoSo);

        AlertDialog alertDialog=new AlertDialog.Builder(HoSoView.this).setView(view).create();
        alertDialog.show();
        txtContentHoSo.setInputType(inputType);
        setBtnHuyAlert(btnHuyAlertHoSo,alertDialog);
        setBtnAcceptAlert(btnXacNhanAlertHoSo,alertDialog,t,txtContentHoSo);
        txtTitleHoSo.setText(title);
        txtContentHoSo.setText(content);
    }

    public void createDialogGioiTinh(TextView t){
        LayoutInflater inflater=LayoutInflater.from(HoSoView.this);
        View view=inflater.inflate(R.layout.activity_alert_dialog_hoso_gioitinh,null);
//         các nút trong alert

        Button btnNamHoSo=view.findViewById(R.id.btnNamHoSo);
        Button btnNuHoSo=view.findViewById(R.id.btnNuHoSo);
        Button btnKhacHoSo=view.findViewById(R.id.btnKhacHoSo);

        AlertDialog alertDialog=new AlertDialog.Builder(HoSoView.this).setView(view).create();
        alertDialog.show();

        btnNamHoSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText("Nam");
                alertDialog.cancel();
            }
        });
        btnNuHoSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText("Nữ");
                alertDialog.cancel();
            }
        });
        btnKhacHoSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText("Khác");
                alertDialog.cancel();
            }
        });
    }

    private void setBtnAcceptAlert(Button btnXacNhanAlertHoSo, AlertDialog alertDialog, TextView t, EditText txtContentHoSo) {
        btnXacNhanAlertHoSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText(txtContentHoSo.getText().toString());
                alertDialog.cancel();
            }
        });
    }

    private void setBtnHuyAlert(Button huy,AlertDialog alert) {
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.cancel();
            }
        });
    }

    private void mapping() {
        lnlIdHoSo=findViewById(R.id.lnlIdHoSo);
        lnlTenDayDuHoSo=findViewById(R.id.lnlTenDayDuHoSo);
        lnlNgaySinhHoSo=findViewById(R.id.lnlNgaySinhHoSo);
        lnlEmailHoSo=findViewById(R.id.lnlEmailHoSo);
        lnlGioiTinhHoSo=findViewById(R.id.lnlGioiTinhHoSo);
        lnlSdtHoSo=findViewById(R.id.lnlSdtHoSo);

        txtTenDayDuHoSo=findViewById(R.id.txtTenDayDuHoSo);
        txtNgaySinhHoSo=findViewById(R.id.txtNgaySinhHoSo);
        txtEmailHoSo=findViewById(R.id.txtEmailHoSo);
        txtIdHoSo=findViewById(R.id.txtIdHoSo);
        txtGioiTinhHoSo=findViewById(R.id.txtGioiTinhHoSo);
        txtSdtHoSo=findViewById(R.id.txtSdtHoSo);

        imgAvatarHoSo=findViewById(R.id.imgAvatarHoSo);

        btnAcceptHoSo=findViewById(R.id.btnAcceptHoSo);
        btnReturnHomeHoSo=findViewById(R.id.btnReturnHomeHoSo);


    }

    @Override
    public void getHoSo(NguoiDung.CallBackHoSo callBackHoSo) {
        NguoiDung nguoiDung=new NguoiDung();
        if(tenTK.equalsIgnoreCase("")){
            Intent intent=new Intent(HoSoView.this,LoginActivity.class);
            startActivity(intent);
        }
        else
            nguoiDung.getNguoiDung(HoSoView.this,callBackHoSo,tenTK);
    }

    @Override
    public void updateHoSo() {
        int gioiTinh=2;
        if(txtGioiTinhHoSo.getText().toString().equalsIgnoreCase("Nam")){
            gioiTinh=0;
        }else if(txtGioiTinhHoSo.getText().toString().equalsIgnoreCase("Nữ")){
            gioiTinh=1;
        }
        NguoiDung nguoiDung=new NguoiDung();
        nguoiDung.setId(Integer.parseInt(txtIdHoSo.getText().toString()));
        nguoiDung.setTenDayDu(txtTenDayDuHoSo.getText().toString());

        nguoiDung.setNgaySinh(txtNgaySinhHoSo.getText().toString());
        nguoiDung.setEmail(txtEmailHoSo.getText().toString());
        nguoiDung.setSdt(txtSdtHoSo.getText().toString());
        nguoiDung.setGioiTinh(gioiTinh);

        new NguoiDung().updateHoSo(HoSoView.this,nguoiDung,bitmap);



    }


}
