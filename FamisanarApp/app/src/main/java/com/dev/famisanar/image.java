package com.dev.famisanar;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
/**
 * Created by juan.vanegas on 23/11/2017.
 */
public class image extends Activity {

    private ImageView imageView;
    private TextView textView,textViewDesc;
    private String title, thumbnail,desc;
    private Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();

        getBundleExtras();
        setTextView();

        loadImageThumbnail();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void loadImageThumbnail(){
        Glide.with(this)
                .load(thumbnail)
                .centerCrop()
                .override(300,300)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .crossFade()
                .into(imageView);
    }

    private void init(){
        imageView = (ImageView) findViewById(R.id.imagething);
        textView = (TextView) findViewById(R.id.tit);
        textViewDesc = (TextView) findViewById(R.id.descText);
    }

    private void getBundleExtras(){
        extras = getIntent().getExtras();
        title = extras.getString("theTitle");
        thumbnail = extras.getString("thumbnail");
        desc = extras.getString("descriptionHtml");
    }

    private void setTextView(){
        textView.setText(title);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            textViewDesc.setText(Html.fromHtml(desc,Html.FROM_HTML_MODE_LEGACY));
        } else {
            textViewDesc.setText(Html.fromHtml(desc));
        }
       }
}
