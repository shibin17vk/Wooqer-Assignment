package com.webapp.wooqerassignment.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.webapp.wooqerassignment.R;


/**
 * Custom type faced textview widget
 *
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 */

public class CustomTypeFacedTextView extends TextView {

    public CustomTypeFacedTextView(final Context context) {
        super(context);
        setAppFont(context, null);
    }

    public CustomTypeFacedTextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        setAppFont(context, attrs);
    }

    public CustomTypeFacedTextView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAppFont(context, attrs);
    }

    public CustomTypeFacedTextView(final Context context, final AttributeSet attrs, final int defStyleAttr,
                                   final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setAppFont(context, attrs);
    }

    public void setFont(String fontName) {
        final Typeface typeface = TypefaceCache.get(getContext().getAssets(), fontName);
        setTypeface(typeface);
    }

    private void setAppFont(Context context, AttributeSet attrs) {
        if (attrs != null) {
            final TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.AppFont);
            final String fontName = styledAttrs.getString(R.styleable.AppFont_font);
            styledAttrs.recycle();
            if (fontName != null) {
                final Typeface typeface = TypefaceCache.get(context.getAssets(), fontName);
                setTypeface(typeface);
            }
        }
    }
}
