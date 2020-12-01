package com.route4me.survey.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

import com.route4me.survey.R;


public class BaseEditTextView extends AppCompatEditText {

    private Paint paint;

    public BaseEditTextView(Context context) {
        super(context);
        init();
    }

    public BaseEditTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseEditTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        int dividerColor = ContextCompat.getColor(getContext(), R.color.border);
        paint = new Paint();
        paint.setColor(dividerColor);
        paint.setStrokeWidth(4f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float startY = getHeight() * 0.8f;
        float stopY = getHeight() - startY;
        canvas.drawLine(0.0f, startY, getWidth(), stopY, paint);
    }

}
