package com.ecloud.trianglerectanglelabelview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-12-13 21:38
 * Description: 三角圆角矩形标签
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 14-12-13      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class TriangleRectangleLabelView extends TextView {
    private static final String TAG = TriangleRectangleLabelView.class.getSimpleName();

    private static final int DEFAULT_BG_COLOR = 0xff41c7cd;
    private static final int DEFAULT_CIRCLE_COLOR = 0xffffffff;
    private static final int DEFAULT_LINE_COLOR = 0xfffb9ece;
    private static final int DEFAULT_HEIGHT = 30;//dp
    private static final int DEFAULT_WIDTH = 70;//dp
    private static final int DEFAULT_ROUND_RECT_RADIUS = 8;//px
    private static final int DEFAULT_ROUND_RECT_WIDTH = 8;//px

    private Paint mBgPaint;
    private Paint mCirclePaint;
    private Paint mLinePaint;

    private int mRoundRectWidth = DEFAULT_ROUND_RECT_WIDTH;//圆角矩形宽度
    private int mRoundRectRadius = DEFAULT_ROUND_RECT_RADIUS;//矩形圆角半径
    private int mCircleRadius = 8;//圆点半径
    private int mCircleSpaceRectangle = 16;//圆点到标签的间隔
    private int mSpaceHeight = 8;//背景上下间隔
    private int mLineWidth = 2;//竖线宽度

    private int mBgColor = DEFAULT_BG_COLOR;//背景颜色
    private int mLineColor = DEFAULT_LINE_COLOR;//竖直线颜色
    private int mCircleColor = DEFAULT_CIRCLE_COLOR;//圆点颜色

    private LINE_MODE mLineMode = LINE_MODE.MIDDLE;//竖线模式

    private boolean isShowLine = false;//是否显示竖线
    private boolean isShowCircle = true;//是否显示圆点
    private boolean isDrawRoundRect = true;//是否显示矩形圆角
    private boolean isLeft = true;//是否箭头朝左显示

    private boolean isFirstPadding = true;

    public static enum LINE_MODE {
        START, MIDDLE, END
    }

    public TriangleRectangleLabelView(Context context) {
        this(context, null);
    }

    public TriangleRectangleLabelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TriangleRectangleLabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(attrs);
        init();
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TriangleRectangleLabelView);

            mBgColor = a.getColor(R.styleable.TriangleRectangleLabelView_trlvBgColor, mBgColor);
            mLineColor = a.getColor(R.styleable.TriangleRectangleLabelView_trlvLineColor, mLineColor);
            mCircleColor = a.getColor(R.styleable.TriangleRectangleLabelView_trlvCircleColor, mCircleColor);

            isLeft = a.getBoolean(R.styleable.TriangleRectangleLabelView_trlvIsLeft, isLeft);
            isShowLine = a.getBoolean(R.styleable.TriangleRectangleLabelView_trlvIsShowLine, isShowLine);
            isShowCircle = a.getBoolean(R.styleable.TriangleRectangleLabelView_trlvIsShowCircle, isShowCircle);
            isDrawRoundRect = a.getBoolean(R.styleable.TriangleRectangleLabelView_trlvIsDrawRoundRect, isDrawRoundRect);

            mCircleRadius = a.getDimensionPixelSize(R.styleable.TriangleRectangleLabelView_trlvCircleRadius, mCircleRadius);
            mCircleSpaceRectangle = a.getDimensionPixelSize(R.styleable.TriangleRectangleLabelView_trlvCircleSpaceRectangle, mCircleSpaceRectangle);
            mLineWidth = a.getDimensionPixelSize(R.styleable.TriangleRectangleLabelView_trlvLineWidth, mLineWidth);
            mRoundRectWidth = a.getDimensionPixelSize(R.styleable.TriangleRectangleLabelView_trlvRoundRectWidth, mRoundRectWidth);
            mRoundRectRadius = a.getDimensionPixelSize(R.styleable.TriangleRectangleLabelView_trlvRoundRectRadius, mRoundRectRadius);

            DisplayMetrics dm = getResources().getDisplayMetrics();
            int minHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_HEIGHT, dm);
            int minWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_WIDTH, dm);
            setMinHeight(minHeight);
            setMinWidth(minWidth);
            a.recycle();
        }
    }

    private void init() {
        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setStrokeWidth(4);
        mBgPaint.setStyle(Paint.Style.FILL);
        mBgPaint.setColor(mBgColor);
        mBgPaint.setAntiAlias(true);

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStrokeWidth(1);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setAntiAlias(true);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStrokeWidth(mLineWidth);
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setColor(mLineColor);
        mLinePaint.setAntiAlias(true);
    }

    /**
     * 设置圆角矩形宽度
     *
     * @param roundRectWidth px
     */
    public void setRoundRectWidth(int roundRectWidth) {
        this.mRoundRectWidth = roundRectWidth;
        postInvalidate();
    }

    /**
     * 设置圆角矩形圆角半径
     *
     * @param roundRectRadius px
     */
    public void setRoundRectRadius(int roundRectRadius) {
        this.mRoundRectRadius = roundRectRadius;
        postInvalidate();
    }

    public void setBgColor(int color) {
        mBgColor = color;
        mBgPaint.setColor(mBgColor);
        postInvalidate();
    }

    public void setLineColor(int color) {
        mLineColor = color;
        mLinePaint.setColor(getContext().getResources().getColor(mLineColor));
        postInvalidate();
    }

    /**
     * 设置竖直线模式
     *
     * @param lineMode LINE_MODE: START, MIDDLE, END
     */
    public void setLineMode(LINE_MODE lineMode) {
        this.mLineMode = lineMode;
        postInvalidate();
    }

    /**
     * 是否箭头朝向左
     *
     * @param isLeft true：left false：right
     */
    public void setLeft(boolean isLeft) {
        this.isLeft = isLeft;
        postInvalidate();
    }

    /**
     * 设置 View 上下间距
     *
     * @param spaceHeight px
     */
    public void setSpaceHeight(int spaceHeight) {
        this.mSpaceHeight = spaceHeight;
        isFirstPadding = true;
        postInvalidate();
    }

    /**
     * 设置原点与标签的间隔
     *
     * @param circleSpaceRectangle px
     */
    public void setCircleSpaceRectangle(int circleSpaceRectangle) {
        this.mCircleSpaceRectangle = circleSpaceRectangle;
        isFirstPadding = true;
        postInvalidate();
    }

    /**
     * 设置原点半径
     *
     * @param circleRadius px
     */
    public void setCircleRadius(int circleRadius) {
        this.mCircleRadius = circleRadius;
        isFirstPadding = true;
        postInvalidate();
    }

    /**
     * 设置分割线宽度
     *
     * @param lineWidth px
     */
    public void setLineWidth(int lineWidth) {
        this.mLineWidth = lineWidth;
        mLinePaint.setStrokeWidth(mLineWidth);
        postInvalidate();
    }

    public void setShowLine(boolean isShowLine) {
        this.isShowLine = isShowLine;
        postInvalidate();
    }

    public void setShowCircle(boolean isShowCircle) {
        this.isShowCircle = isShowCircle;
        postInvalidate();
    }

    public void setDrawRoundRect(boolean isDrawRoundRect) {
        this.isDrawRoundRect = isDrawRoundRect;
        postInvalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "isFirstPadding = " + isFirstPadding);
        //-------------------Set Text Padding Begin----------------------------//
        if (isFirstPadding) {
            int paddingLeft = mCircleRadius * 2 + mCircleSpaceRectangle + (h - mSpaceHeight) / 2 + 2;
            setPadding(isLeft ? paddingLeft : mRoundRectWidth, mSpaceHeight + 4, isLeft ? mRoundRectWidth + 4 : paddingLeft, mSpaceHeight + 4);
            setGravity(Gravity.CENTER_VERTICAL);
            isFirstPadding = false;
        }
        //-------------------Set Text Padding End----------------------------//
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBackground(canvas);
        super.onDraw(canvas);
    }

    private void drawBackground(Canvas canvas) {
        int height = getHeight();
        int width = getWidth();
        Log.d(TAG, "height = " + height);
        Log.d(TAG, "width = " + width);

        //-------------------Draw Line Begin----------------------------//
        int startLineX = isShowLine ? (isLeft ? mCircleRadius : width - mCircleRadius) : 0;
        if (isShowLine) {
            if (mLineMode == LINE_MODE.START) {
                canvas.drawLine(startLineX, height / 2, startLineX, height, mLinePaint);
            } else if (mLineMode == LINE_MODE.MIDDLE) {
                canvas.drawLine(startLineX, height, startLineX, 0, mLinePaint);
            } else if (mLineMode == LINE_MODE.END) {
                canvas.drawLine(startLineX, height / 2, startLineX, 0, mLinePaint);
            }
        }
        //-------------------Draw Line End----------------------------//

        //-------------------Draw Circle Begin----------------------------//
        int startCircleX = isShowCircle ? (isLeft ? mCircleRadius : width - mCircleRadius) : 0;
        if (isShowCircle) {
            canvas.drawCircle(startCircleX, height / 2, mCircleRadius, mCirclePaint);
        }
        //-------------------Draw Circle End----------------------------//


        //-------------------Draw RoundRect Begin----------------------------//
        int roundLeft = isDrawRoundRect ? (isLeft ? width - mRoundRectWidth : 0) : 0;
        int roundRight = isDrawRoundRect ? (isLeft ? width : mRoundRectWidth) : 0;

        if (isDrawRoundRect) {
            //draw RoundRect
            RectF rect = new RectF();
            rect.left = roundLeft;
            rect.top = mSpaceHeight;
            rect.right = roundRight;
            rect.bottom = height - mSpaceHeight;
            canvas.drawRoundRect(rect, mRoundRectRadius, mRoundRectRadius, mBgPaint);
        }
        //-------------------Draw RoundRect End----------------------------//

        //-------------------Draw Triangle And Rectangle Begin----------------------------//
        if (isLeft) {
            //draw Triangle And Rectangle
            int startX = mCircleSpaceRectangle + 2 * mCircleRadius;
            Point a = new Point(startX, height / 2);
            Point b = new Point(startX + (height - mSpaceHeight) / 2, height - mSpaceHeight);
            Point c = new Point(width - (isDrawRoundRect ? mRoundRectWidth / 2 : 0), height - mSpaceHeight);
            Point d = new Point(width - (isDrawRoundRect ? mRoundRectWidth / 2 : 0), mSpaceHeight);
            Point e = new Point(startX + (height - mSpaceHeight) / 2, mSpaceHeight);
            Point f = new Point(startX, height / 2);

            Path path = new Path();
            path.moveTo(a.x, a.y);
            path.lineTo(b.x, b.y);
            path.lineTo(c.x, c.y);
            path.lineTo(d.x, d.y);
            path.lineTo(e.x, e.y);
            path.lineTo(f.x, f.y);

            canvas.drawPath(path, mBgPaint);

        } else {
            //draw Triangle And Rectangle
            int startX = width - (mCircleSpaceRectangle + 2 * mCircleRadius);
            Point a = new Point(startX, height / 2);
            Point b = new Point(startX - (height - mSpaceHeight) / 2, height - mSpaceHeight);
            Point c = new Point((isDrawRoundRect ? mRoundRectWidth / 2 : 0), height - mSpaceHeight);
            Point d = new Point((isDrawRoundRect ? mRoundRectWidth / 2 : 0), mSpaceHeight);
            Point e = new Point(startX - (height - mSpaceHeight) / 2, mSpaceHeight);
            Point f = new Point(startX, height / 2);

            Path path = new Path();
            path.moveTo(a.x, a.y);
            path.lineTo(b.x, b.y);
            path.lineTo(c.x, c.y);
            path.lineTo(d.x, d.y);
            path.lineTo(e.x, e.y);
            path.lineTo(f.x, f.y);

            canvas.drawPath(path, mBgPaint);
        }
        //-------------------Draw Triangle And Rectangle End----------------------------//
    }
}
