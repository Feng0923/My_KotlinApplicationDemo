package com.example.administrator.my_kotlinapplication.ViewDemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.my_kotlinapplication.R;

/**
 * Created by Administrator on 2018/3/7/007.
 */

public class mView extends View {


    private Paint mPaint = new Paint();
    public mView(Context context) {
        super(context);
    }

    public mView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10f);
    }

    public mView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public mView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int color = getResources().getColor(R.color.btnColor);
//        canvas.drawColor(color);
        canvas.drawPoint(200,200,mPaint);//画点

        canvas.drawLine(200,220,250,220,mPaint);//画线

        //画直角矩形
        Rect rect = new Rect(200,250,250,300);//精度为int

        canvas.drawRect(rect,mPaint);

        //画圆角矩形
        RectF rectF = new RectF(200,320,250,370);//精度为float
        canvas.drawRoundRect(rectF,10,10,mPaint);//圆心和半径

        //画椭圆
        RectF rectF1 = new RectF(200,400,500,500);
        canvas.drawOval(rectF1,mPaint);//内切椭圆

        //画圆
        canvas.drawCircle(270,600,70,mPaint);//圆心,半径

        //圆弧
        RectF rectF2 = new RectF(200,700,500,800);
        canvas.drawArc(rectF2,0,90,false,mPaint);
        RectF rectF3 = new RectF(200,900,500,1000);
        canvas.drawArc(rectF3,0,90,true,mPaint);

    }
}
