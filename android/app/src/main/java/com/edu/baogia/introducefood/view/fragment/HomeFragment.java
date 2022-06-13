package com.edu.baogia.introducefood.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.adapter.HomeAdapter;
import com.edu.baogia.introducefood.adapter.RcyNewAdapter;
import com.edu.baogia.introducefood.model.object.Food;
import com.edu.baogia.introducefood.presenter.HomePresenter;
import com.edu.baogia.introducefood.presenter.HomePresenterIm;
import com.edu.baogia.introducefood.view.activity.FoodActivity;
import com.edu.baogia.introducefood.view.activity.SearchMainActivity;
import com.edu.baogia.introducefood.view.activity.SimpleScannerActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeFragmentView{
    TextView txtCateNew, txtDesNew, txtNameNew, txtidNew, txtSearch;
    Button btnNewFood,btnScanQrcode;
    ImageView imgNew, imageAlpha;
    private static final int REQUEST_CODE_EXAMPLE = 0x9345;
    ViewFlipper vfSlide;

    public  static RecyclerView rcyPhone,rcyPhone2,rcycate,rcyNew;
    public  static HomeAdapter rcyAdapter;

    public  static RcyNewAdapter rcyNewAdapter;
    public  static List<Food> listrcy2 = new ArrayList<>();

    public List<Food> homeFragList;


    HomePresenter homePresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home,container,false);
        innit(root);
        slide();
        addNavButton();
        loadData();
        click();
        return root;
    }

    private void click() {
        btnNewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FoodActivity.class);
                intent.putExtra("idFood",Integer.parseInt(txtidNew.getText().toString()));
                startActivity(intent);
            }
        });
        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchMainActivity.class);
                startActivity(intent);
            }
        });

        btnScanQrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SimpleScannerActivity.class);
                startActivityForResult(intent, 9345);
            }
        });
    }

    private void addNavButton() {

    }
    private void slide() {
        vfSlide.setAutoStart(true);

    }
    private void innit(ViewGroup root) {
        //Màu thanh pin
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.argb(70,255,51,153));
        }
        vfSlide = root.findViewById(R.id.vfSlide);
        rcyNew = root.findViewById(R.id.rcyNew);
        rcyPhone = root.findViewById(R.id.rcyPhone);
        rcyPhone2 = root.findViewById(R.id.rcyPhone2);
        txtSearch = root.findViewById(R.id.txtSearch);
        txtCateNew = root.findViewById(R.id.txtCateNew);
        txtDesNew= root.findViewById(R.id.txtDesNew);
        txtNameNew= root.findViewById(R.id.txtNameNew);
        txtidNew = root.findViewById(R.id.txtidNew);
        btnNewFood= root.findViewById(R.id.btnNewFood);
        imgNew = root.findViewById(R.id.imgNew);
        imageAlpha = root.findViewById(R.id.imageAlpha);
        btnScanQrcode = root.findViewById(R.id.btnScanQrcode);


    }


    private void loadData() {
        homePresenter = new HomePresenterIm( HomeFragment.this);
        homePresenter.loadData();
    }
    private void setrcAdapter(List<Food> listDemo) {
        listrcy2.clear();
        for (int i=0; i < 8; i++){
            listrcy2.add(listDemo.get(i));
        }
        rcyNewAdapter = new RcyNewAdapter(getContext(),listrcy2,R.layout.food_new,this);
        rcyNew.setAdapter(rcyNewAdapter);

        rcyAdapter = new HomeAdapter(getContext(),listDemo,R.layout.food);
        rcyPhone.setAdapter(rcyAdapter);
        rcyPhone2.setAdapter(rcyAdapter);
//        //dọc:
//        rcyPhone.setLayoutManager(new LinearLayoutManager(this));

        //Ngang
        rcyNew.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rcyPhone.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rcyPhone2.setLayoutManager(new GridLayoutManager(rcyPhone.getContext(), 2));

        rcyNewAdapter.notifyDataSetChanged();
        rcyAdapter.notifyDataSetChanged();
        txtidNew.setText(""+listDemo.get(0).getId());
        txtCateNew.setText(""+listDemo.get(0).getCate());
        txtNameNew.setText(""+listDemo.get(0).getName());
        txtDesNew.setText(""+listDemo.get(0).getDes());
        Picasso.get().load(""+listDemo.get(0).getImg()).into(imgNew);
        Picasso.get().load(""+listDemo.get(0).getImg()).into(imageAlpha);
    }


    @Override
    public void listRcy(List<Food> listDemo) {
        setrcAdapter(listDemo);
    }

    public TextView getTxtCateNew() {
        return txtCateNew;
    }

    public TextView getTxtDesNew() {
        return txtDesNew;
    }

    public TextView getTxtNameNew() {
        return txtNameNew;
    }

    public ImageView getImgNew() {
        return imgNew;
    }

    public ImageView getImageAlpha() {
        return imageAlpha;
    }

    public TextView getTxtidNew() {
        return txtidNew;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        Nếu muốn chuyển kết quả cho activity thì dùng thên super
//        super.onActivityResult(requestCode, resultCode, data);
        // Kiểm tra requestCode có trùng với REQUEST_CODE vừa dùng
        if(requestCode == 9345) {

            // resultCode được set bởi DetailActivity
            // RESULT_OK chỉ ra rằng kết quả này đã thành công
            if(resultCode == Activity.RESULT_OK) {
                // Nhận dữ liệu từ Intent trả về
                String result = data.getStringExtra("idfood");

                // Sử dụng kết quả result bằng cách hiện Toast
                Toast.makeText(getContext(), "Result: " + result, Toast.LENGTH_LONG).show();
                Log.d("AAA", "onActivityResult2: "+result);
            } else {
                // DetailActivity không thành công, không có data trả về.
            }
        }
    }
}
