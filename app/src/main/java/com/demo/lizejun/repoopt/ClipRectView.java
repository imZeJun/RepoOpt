package com.demo.lizejun.repoopt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ClipRectView extends View {

    private static final int[] ID = new int[]{R.drawable.udn, R.drawable.usatoday, R.drawable.weibo, R.drawable.xinhua, R.drawable.yahoo, R.drawable.zhe800};
    private Bitmap[] mBitmaps;

    public ClipRectView(Context context) {
        super(context);
        prepareBitmap();
    }

    public ClipRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        prepareBitmap();
    }

    public ClipRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        prepareBitmap();
    }

    private void prepareBitmap() {
        mBitmaps = new Bitmap[ID.length];
        int i = 0;
        for (int id : ID) {
            mBitmaps[i++] = BitmapFactory.decodeResource(getResources(), id);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Bitmap bitmap : mBitmaps) {
            canvas.drawBitmap(bitmap, 0, 0, null);
            canvas.translate(bitmap.getWidth() / 2, 0);
        }
    }
}
