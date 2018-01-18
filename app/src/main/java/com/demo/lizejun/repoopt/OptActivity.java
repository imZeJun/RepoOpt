package com.demo.lizejun.repoopt;

import android.content.res.Configuration;
import android.support.v4.view.AsyncLayoutInflater;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

public class OptActivity extends AppCompatActivity {

    private ViewStub mViewStub;
    private View mStubView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_width_demo);
        ThreadDemo demo = new ThreadDemo();
    }

    private void optUtils() {
        Button assemble = (Button) findViewById(R.id.bt_assemble);
        assemble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThreadLocalSamples.startSample();
            }
        });
        Button string = (Button) findViewById(R.id.bt_string);
        string.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptUtils.badString();
            }
        });
        Button app = (Button) findViewById(R.id.bt_app);
        app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptUtils.startApp(OptActivity.this);
            }
        });
        Button service = (Button) findViewById(R.id.bt_service);
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptUtils.startActivity(OptActivity.this);
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void useSpan() {
        TextView textView = (TextView) findViewById(R.id.tv_span);
        SpannableStringBuilder ssb = new SpannableStringBuilder("300 RMB");
        //设置文字大小。
        ssb.setSpan(new RelativeSizeSpan(6.0f), 0, 3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        //设置文字颜色。
        ssb.setSpan(new ForegroundColorSpan(0xff303F9F), 0, 3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(ssb);
    }

    private void inflateIfNeed() {
        //1.获取到布局中的ViewStub。
        mViewStub = (ViewStub) findViewById(R.id.view_stub);
        //2.调用其inflate方法实例化它所指定的layout。
        mStubView = mViewStub.inflate();
    }

    private void asyncInflated() {
        TextView textView = (TextView) findViewById(R.id.tv_async);
        final ViewGroup root = (ViewGroup) findViewById(R.id.ll_root);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncLayoutInflater asyncLayoutInflater = new AsyncLayoutInflater(OptActivity.this);
                asyncLayoutInflater.inflate(R.layout.layout_async, root, new AsyncLayoutInflater.OnInflateFinishedListener() {

                    @Override
                    public void onInflateFinished(View view, int resId, ViewGroup parent) {
                        parent.addView(view);
                    }
                });
            }
        });
    }
}
