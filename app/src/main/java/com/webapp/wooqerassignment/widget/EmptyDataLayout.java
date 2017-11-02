package com.webapp.wooqerassignment.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.webapp.wooqerassignment.R;

/**
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 */

public class EmptyDataLayout extends LinearLayout {

    public EmptyDataLayout(final Context context) {
        super(context);
        loadViewComponets(context,null);
    }

    public EmptyDataLayout(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        loadViewComponets(context,attrs);
    }

    public EmptyDataLayout(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadViewComponets(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public EmptyDataLayout(final Context context, final AttributeSet attrs, final int defStyleAttr,
            final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        loadViewComponets(context,attrs);
    }


    private LinearLayout.LayoutParams setGravity(Context context){
        Activity activityContext = null;
        if(context instanceof Activity) {
            activityContext = (Activity) context;
        } else if (context instanceof ContextWrapper){
            activityContext = (Activity) ((ContextWrapper)context).getBaseContext();
        }
        if(activityContext != null) {

            Display displayDevice = activityContext.getWindowManager().getDefaultDisplay();
            int wid = displayDevice.getWidth();
            int hit = LayoutParams.MATCH_PARENT;
            LayoutParams lp = new LayoutParams(wid, hit);
            lp.gravity = Gravity.CENTER;
            return lp;
        }
        return null;

    }

    private void loadViewComponets(Context context, AttributeSet attributeSet){
        Activity activityContext;
        if(context instanceof Activity) {
            activityContext = (Activity) context;
        } else if (context instanceof ContextWrapper){
            activityContext = (Activity) ((ContextWrapper)context).getBaseContext();
        } else {
            return;
        }
        View view = activityContext.getLayoutInflater().inflate(R.layout.cutom_view_error, null);
        LayoutParams layoutParams = setGravity(context);
        if(layoutParams != null) {
            view.setLayoutParams(layoutParams);
        }
        this.addView(view);

    }
}
