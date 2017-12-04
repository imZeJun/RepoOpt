package com.demo.lizejun.repoopt;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class WidthDemoLayout extends LinearLayout {

    public WidthDemoLayout(Context context) {
        super(context);
    }

    public WidthDemoLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WidthDemoLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (i == childCount - 1) {
                child.layout(child.getLeft(), child.getTop(), child.getRight() + 400, child.getBottom());
            }
            Log.d("WidthDemoLayout", "measuredWidth=" + child.getMeasuredWidth() + ",width=" + child.getWidth());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
