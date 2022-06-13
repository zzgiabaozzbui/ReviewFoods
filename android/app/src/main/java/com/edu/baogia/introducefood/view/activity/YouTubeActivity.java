package com.edu.baogia.introducefood.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.baogia.introducefood.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YouTubeActivity extends YouTubeBaseActivity {
    YouTubePlayerView you ;
    TextView txtdes;
    Button btnFinis;
    String ApiKey = "AIzaSyAfriL9vFkRyYJDLQ-baEXm9xFVgGBKlMI";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube);
        //Lấy phần gần cuối trước giấu bằng cuối cùng đuôi link youtube
        String link=getIntent().getStringExtra("linkyt");

        String des = getIntent().getStringExtra("desmaking");
        txtdes = findViewById(R.id.txtdes);
        txtdes.setText(""+des);
        btnFinis = findViewById(R.id.btnFinis);
        btnFinis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        you = findViewById(R.id.you);
        you.initialize(ApiKey, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(link);
//                youTubePlayer.setFullscreen(true);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                if(youTubeInitializationResult.isUserRecoverableError()){
                    //khởi tạo lại
                    you.initialize(ApiKey, this);
                }else {
                    Toast.makeText(YouTubeActivity.this, "lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}