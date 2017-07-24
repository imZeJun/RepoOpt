package com.demo.lizejun.repoopt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ClipRectView extends View {

    private static final int[] ID = new int[]{R.drawable.pic_1, R.drawable.pic_2, R.drawable.pic_3};
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
        canvas.save();
        int bits = mBitmaps.length;
        for (int i = 0; i < bits; i++) {
            Bitmap bitmap = mBitmaps[i];
            int bitW = bitmap.getWidth();
            int bitH = bitmap.getHeight();
            if (i != 0) {
                canvas.translate(bitW / 2, 0);
            }
            canvas.save();
            if (i != bits - 1) {
                canvas.clipRect(0, 0, bitW / 2, bitH);
            }
            canvas.drawBitmap(bitmap, 0, 0, null);
            canvas.restore();
        }
        canvas.restore();
    }
}
