package cc.sayaki.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/11/19
 */

public class PlumbTextView extends View {

    private TextPaint textPaint;
    private Paint leftLinePaint;

    private int width;
    private int height;
    private int columnWidth;
    private int charHeight;

    private CharSequence text;
    private int textColor;
    private int textSize;
    private int letterSpacing;
    private int columnSpacing;
    private boolean leftLine;
    private int leftLinePadding;
    private int leftLineColor;
    private String regex;
    private int textStyle;
    private List<String> formatTexts = new ArrayList<>();

    public PlumbTextView(Context context) {
        this(context, null);
    }

    public PlumbTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlumbTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PlumbTextView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.PlumbTextView_text) {
                text = a.getString(attr);

            } else if (attr == R.styleable.PlumbTextView_textColor) {
                textColor = a.getColor(attr, textColor);

            } else if (attr == R.styleable.PlumbTextView_textSize) {
                textSize = a.getDimensionPixelSize(attr, textSize);

            } else if (attr == R.styleable.PlumbTextView_columnSpacing) {
                columnSpacing = a.getDimensionPixelSize(attr, columnSpacing);

            } else if (attr == R.styleable.PlumbTextView_letterSpacing) {
                letterSpacing = a.getDimensionPixelSize(attr, letterSpacing);

            } else if (attr == R.styleable.PlumbTextView_regex) {
                regex = a.getString(attr);

            } else if (attr == R.styleable.PlumbTextView_textStyle) {
                textStyle = a.getInt(attr, textStyle);

            } else if (attr == R.styleable.PlumbTextView_leftLine) {
                leftLine = a.getBoolean(attr, false);

            } else if (attr == R.styleable.PlumbTextView_leftLinePadding) {
                leftLinePadding = a.getDimensionPixelSize(attr, leftLinePadding);

            } else if (attr == R.styleable.PlumbTextView_leftLineColor) {
                leftLineColor = a.getColor(attr, leftLineColor);

            }
        }
        a.recycle();

        initPaint();
        columnWidth = (int) (Math.abs(textPaint.ascent())
                + Math.abs(textPaint.descent()) + columnSpacing);
    }

    private void init() {
        text = "";
        textColor = 0xff272727;
        textSize = sp2px(getContext(), 14);
        columnSpacing = 0;
        letterSpacing = dp2px(getContext(), 4);
        leftLinePadding = 0;
        leftLineColor = 0xff272727;
        regex = "";
        textStyle = 0;
    }

    private void initPaint() {
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.density = getResources().getDisplayMetrics().density;
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
        textPaint.setFakeBoldText((textStyle & Typeface.BOLD) != 0);
        textPaint.setTextSkewX((textStyle & Typeface.ITALIC) != 0 ? -0.25f : 0);

        leftLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        leftLinePaint.setColor(leftLineColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            if (!TextUtils.isEmpty(regex)) {
                height = 0;
                String[] texts = text.toString().split(regex);
                for (String s : texts) {
                    height = Math.max(height, getDesiredHeight(s));
                }
                height += letterSpacing;
            } else {
                height = getDesiredHeight(text.toString());
            }
            if (height > heightSize) {
                height = heightSize;
            }
        }

        // To prevent duplication of data caused by repeated measurements
        formatTexts.clear();

        if (!TextUtils.isEmpty(regex)) {
            String[] texts = text.toString().split(regex);
            for (String s : texts) {
                getFormatTexts(s);
            }
        } else {
            getFormatTexts(text.toString());
        }

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            if (!TextUtils.isEmpty(regex)) {
                width = columnWidth * formatTexts.size();
            } else {
                width = columnWidth * (getDesiredHeight(text.toString())
                        / (height - getPaddingTop() - getPaddingBottom()) + 1);
            }
            if (width > widthSize) {
                width = widthSize;
            }
        }

        setMeasuredDimension(width, height);
    }

    private void getFormatTexts(String s) {
        int contentHeight = height - getPaddingTop() - getPaddingBottom();
        if (getDesiredHeight(s) > contentHeight) {
            int count = contentHeight / charHeight;
            int i = 0;
            for (; i < getDesiredHeight(s) / contentHeight; i++) {
                formatTexts.add(s.substring(i * count, (i + 1) * count));
            }
            if (getDesiredHeight(s) % contentHeight != 0) {
                formatTexts.add(s.substring(i * count, s.length()));
            }
        } else {
            formatTexts.add(s);
        }
    }

    private int getDesiredHeight(String s) {
        return getCharHeight() * s.length();
    }

    private int getCharHeight() {
        return charHeight = (int) (Math.abs(textPaint.ascent()) + letterSpacing);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float x = width - getPaddingLeft() - getPaddingRight();
        float y = getPaddingTop();
        for (int i = 0; i < formatTexts.size(); i++) {
            x = i == 0 ? width - columnWidth + columnSpacing : x - columnWidth;
            for (int j = 0; j < formatTexts.get(i).length(); j++) {
                y = j == 0 ? charHeight - letterSpacing + getPaddingTop() : y + charHeight;
                canvas.drawText(formatTexts.get(i), j, j + 1, x, y, textPaint);
            }
            if (leftLine) {
                canvas.drawLine(x - leftLinePadding, getPaddingTop(),
                        x - leftLinePadding, y + letterSpacing, leftLinePaint);
            }
        }
    }

    public CharSequence getText() {
        return text;
    }

    public void setText(CharSequence text) {
        this.text = text;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getLetterSpacing() {
        return letterSpacing;
    }

    public void setLetterSpacing(int letterSpacing) {
        this.letterSpacing = letterSpacing;
    }

    public int getColumnSpacing() {
        return columnSpacing;
    }

    public void setColumnSpacing(int columnSpacing) {
        this.columnSpacing = columnSpacing;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public int getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(int textStyle) {
        this.textStyle = textStyle;
    }

    public int getTypeface() {
        Typeface typeface = textPaint.getTypeface();
        return typeface != null ? typeface.getStyle() : Typeface.NORMAL;
    }

    public void setTypeface(Typeface tf, int style) {
        if (style > 0) {
            if (tf == null) {
                tf = Typeface.defaultFromStyle(style);
            } else {
                tf = Typeface.create(tf, style);
            }

            setTypeface(tf);
            int typefaceStyle = tf != null ? tf.getStyle() : 0;
            int need = style & ~typefaceStyle;
            textPaint.setFakeBoldText((need & Typeface.BOLD) != 0);
            textPaint.setTextSkewX((need & Typeface.ITALIC) != 0 ? -0.25f : 0);
        } else {
            textPaint.setFakeBoldText(false);
            textPaint.setTextSkewX(0);
            setTypeface(tf);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface == null) {
            typeface = Typeface.DEFAULT;
        }
        if (textPaint.getTypeface() != typeface) {
            textPaint.setTypeface(typeface);

            requestLayout();
            invalidate();
        }
    }

    private int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    private int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
