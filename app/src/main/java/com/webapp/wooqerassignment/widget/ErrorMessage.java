package com.webapp.wooqerassignment.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webapp.wooqerassignment.R;

/**
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 */

public class ErrorMessage extends LinearLayout {

    private TextView mTvErrorMessage, tvSubErrorMessage;
    private ImageView mIvImage;
    private LinearLayout parentBackground;
    private SwipeRefreshLayout swipeToRefresh;
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener;

    public ErrorMessage(final Context context) {
        super(context);
        loadViewComponets(context,null);
    }

    public ErrorMessage(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        loadViewComponets(context,attrs);
    }

    public ErrorMessage(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadViewComponets(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ErrorMessage(final Context context, final AttributeSet attrs, final int defStyleAttr,
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

    public void setOnrefreshLister(SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        if(swipeToRefresh != null) {
            swipeToRefresh.setOnRefreshListener(onRefreshListener);
        }
    }

    public void setRefreshing(boolean value) {
        swipeToRefresh.setRefreshing(value);
    }

    public void setErrorMessageStyle(int textColor,int textSubColor, int resourceIconResource, int iconResourceTint) {
        mTvErrorMessage.setTextColor(textColor);
        mIvImage.setImageResource(resourceIconResource);
        tvSubErrorMessage.setTextColor(textSubColor);
    }

    public void setBackgroundColor(int color) {
        parentBackground.setBackgroundColor(color);
    }

    public void setMessage(String message) {
        mTvErrorMessage.setText(message);
    }

    public void setMessage(String message, String subMessage) {
        mTvErrorMessage.setText(message);
        tvSubErrorMessage.setText(subMessage);
    }

    public void setImageResource(int resource) {
        mIvImage.setImageResource(resource);
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
        mTvErrorMessage = (TextView) view.findViewById(R.id.tvErrorMessage);
        tvSubErrorMessage  = (TextView) view.findViewById(R.id.tvSubErrorMessage);
        swipeToRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mIvImage = (ImageView) view.findViewById(R.id.icon);
        parentBackground = (LinearLayout) view.findViewById(R.id.parentBackground);

        LayoutParams layoutParams = setGravity(context);
        if(layoutParams != null) {
            view.setLayoutParams(layoutParams);
        }
        this.addView(view);

    }
}
