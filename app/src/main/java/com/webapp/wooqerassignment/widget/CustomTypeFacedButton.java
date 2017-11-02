package com.webapp.wooqerassignment.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.webapp.wooqerassignment.R;


/**
 * Custom type faced Button widget
 *
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 */
public class CustomTypeFacedButton extends Button {

    public CustomTypeFacedButton(final Context context) {
        super(context);
        setAppFont(context, null);
    }

    public CustomTypeFacedButton(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        setAppFont(context, attrs);
    }

    public CustomTypeFacedButton(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAppFont(context, attrs);
    }

    public CustomTypeFacedButton(final Context context, final AttributeSet attrs, final int defStyleAttr,
                                 final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setAppFont(context, attrs);
    }

    private void setAppFont(Context context, AttributeSet attrs) {
        if (attrs != null) {
            final TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.AppFont);
            final String font = styledAttrs.getString(R.styleable.AppFont_font);
            styledAttrs.recycle();
            if (font != null) {
                final Typeface typeface = TypefaceCache.get(context.getAssets(), font);
                setTypeface(typeface);
            }
        }
    }
}
