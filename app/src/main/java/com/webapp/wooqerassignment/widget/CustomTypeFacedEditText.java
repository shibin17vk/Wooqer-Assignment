package com.webapp.wooqerassignment.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.webapp.wooqerassignment.R;


/**
 * Custom type faced EditText widget
 *
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 */
public class CustomTypeFacedEditText extends EditText {

    public CustomTypeFacedEditText(final Context context) {
        super(context);
        setAppFont(context, null);
    }

    public CustomTypeFacedEditText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        setAppFont(context, attrs);
    }

    public CustomTypeFacedEditText(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
