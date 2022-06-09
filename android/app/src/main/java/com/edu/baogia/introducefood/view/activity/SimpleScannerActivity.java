package com.edu.baogia.introducefood.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.edu.baogia.introducefood.R;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SimpleScannerActivity extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    // Biến constant được dùng để định danh dữ liệu được truyền giữa các Activity
    public static final String EXTRA_DATA = "EXTRA_DATA";

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_simple_scanner);
        mScannerView = findViewById(R.id.scannerView);   // Programmatically initialize the scanner view
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {


                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        mScannerView.resumeCameraPreview(SimpleScannerActivity.this);
                        mScannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(SimpleScannerActivity.this, "You must enable this permission", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();
    }


    @Override
    public void handleResult(Result rawResult) {
//        Toast.makeText(SimpleScannerActivity.this, "handleResult: "+rawResult.getText(), Toast.LENGTH_SHORT).show();
//        // Do something with the result here
//        Log.d("AAA", rawResult.getText()); // Prints scan results
//        Log.d("AAA", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        // Nếu bạn muốn tiếp tục quét, hãy gọi phương pháp này bên dưới:
        //mScannerView.resumeCameraPreview(SimpleScannerActivity.this);
        //Quay về main
        Swap(rawResult);
    }


    private void Swap(Result rawResult) {
        // Tạo một Intent mới để chứa dữ liệu trả về
        final Intent data = new Intent();

        // Truyền data vào intent
        data.putExtra(EXTRA_DATA, rawResult.getText());

        // Đặt resultCode là Activity.RESULT_OK to
        // thể hiện đã thành công và có chứa kết quả trả về
        setResult(Activity.RESULT_OK, data);

        // gọi hàm finish() để đóng Activity hiện tại và trở về MainActivity.
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(SimpleScannerActivity.this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        Toast.makeText(SimpleScannerActivity.this, "Pau", Toast.LENGTH_SHORT).show();
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause

    }

}