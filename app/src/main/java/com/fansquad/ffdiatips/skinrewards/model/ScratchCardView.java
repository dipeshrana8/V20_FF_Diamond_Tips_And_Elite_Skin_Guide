package com.fansquad.ffdiatips.skinrewards.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.fansquad.ffdiatips.skinrewards.R;

public class ScratchCardView extends View {

    private Bitmap scratchBitmap;
    private Canvas scratchCanvas;
    private Paint scratchPaint;
    private Path scratchPath;
    private Paint overlayPaint;
    private Bitmap revealImage; // Image to reveal
    private OnScratchListener listener;
    private boolean isScratched = false;

    public ScratchCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        scratchPaint = new Paint();
        scratchPaint.setAntiAlias(true);
        scratchPaint.setStyle(Paint.Style.STROKE);
        scratchPaint.setStrokeWidth(100);
        scratchPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        overlayPaint = new Paint();
        overlayPaint.setColor(Color.GRAY); // Overlay color to hide the image
        overlayPaint.setStyle(Paint.Style.FILL);

        scratchPath = new Path();

        // Load the image from drawable
        revealImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.scratch_image);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        scratchBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        scratchCanvas = new Canvas(scratchBitmap);

        // Scale the image to fit the view
        if (revealImage != null) {
            revealImage = Bitmap.createScaledBitmap(revealImage, w, h, true);
        }

        // Draw the overlay to hide the image initially
        scratchCanvas.drawColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Draw the image as the background
        if (revealImage != null) {
            canvas.drawBitmap(revealImage, 0, 0, null);
        }
        // Draw the scratchable overlay
        canvas.drawBitmap(scratchBitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                scratchPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                scratchPath.lineTo(x, y);
                scratchCanvas.drawPath(scratchPath, scratchPaint);
                break;
            case MotionEvent.ACTION_UP:
                if (!isScratched) {
                    isScratched = true;
                    if (listener != null) {
                        listener.onScratchComplete();
                    }
                }
                break;
        }
        invalidate();
        return true;
    }

    public void setOnScratchListener(OnScratchListener listener) {
        this.listener = listener;
    }

    public interface OnScratchListener {
        void onScratchComplete();
    }
}