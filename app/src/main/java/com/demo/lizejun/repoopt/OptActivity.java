package com.demo.lizejun.repoopt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import static android.R.attr.start;

public class OptActivity extends AppCompatActivity {

    private ViewStub mViewStub;
    private View mStubView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip);
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
}
