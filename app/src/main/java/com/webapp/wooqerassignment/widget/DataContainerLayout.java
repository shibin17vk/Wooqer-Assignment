package com.webapp.wooqerassignment.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.webapp.wooqerassignment.R;

/**
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 */

public class DataContainerLayout extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {

    private ErrorMessage mErrorMessage;
    private EmptyDataLayout mEmptyDataLayout;
    private View mLoaderView;
    private View mDataPanelView;
    private onDataReresheListener monDataReresheListener;

    public interface onDataReresheListener {
        public void onRefresh();
    }

    public DataContainerLayout(final Context context) {
        super(context);
        initLayout(null,context);
    }

    public DataContainerLayout(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        initLayout(attrs,context);
    }

    public DataContainerLayout(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(attrs,context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DataContainerLayout(final Context context, final AttributeSet attrs, final int defStyleAttr,
            final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initLayout(attrs,context);
    }

    private LinearLayout.LayoutParams setGravity(Context context){
        int wid = LinearLayout.LayoutParams.MATCH_PARENT;
        int hit = LinearLayout.LayoutParams.MATCH_PARENT;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(wid, hit);
        lp.gravity = Gravity.CENTER;
        return lp;
    }

    private void initLayout(AttributeSet attrs, Context context) {

        if (attrs != null) {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.DataLayout);
            final int dataLayoutRes = styledAttrs.getResourceId(R.styleable.DataLayout_dataLayout,0);
            mDataPanelView = layoutInflater.inflate(dataLayoutRes, null,   false);
            mLoaderView = layoutInflater.inflate(R.layout.custom_view_loader, null,   false);

            mEmptyDataLayout = new EmptyDataLayout(context);
            mEmptyDataLayout.setId(View.generateViewId());

            mErrorMessage = new ErrorMessage(context);
            mErrorMessage.setId(View.generateViewId());
            mErrorMessage.setOnrefreshLister(this);

            mErrorMessage.setLayoutParams(setGravity(context));
            mLoaderView.setLayoutParams(setGravity(context));
            mDataPanelView.setLayoutParams(setGravity(context));

            this.addView(mErrorMessage);
            this.addView(mLoaderView);
            this.addView(mDataPanelView);
            this.addView(mEmptyDataLayout);
        }
    }


    public void setRefershListener(onDataReresheListener onDataReresheListener) {
        monDataReresheListener = onDataReresheListener;
    }

    public void startLoader() {
        showView(mLoaderView.getId(), mLoaderView, mErrorMessage,mDataPanelView,mEmptyDataLayout );
    }

    public void onError(String error) {
        mErrorMessage.setMessage(error);
        mErrorMessage.setRefreshing(false);
        showView(mErrorMessage.getId(), mLoaderView, mErrorMessage,mDataPanelView,mEmptyDataLayout );
    }

    public void showData() {
        showView(mDataPanelView.getId(), mLoaderView, mErrorMessage,mDataPanelView,mEmptyDataLayout );
    }

    public void showEmptyData() {
        showView(mEmptyDataLayout.getId(), mLoaderView, mErrorMessage,mDataPanelView,mEmptyDataLayout );
    }

    private void  showView(int viewId, View... views) {
        for(View view : views) {
            int visibility = view.getId() == viewId ? View.VISIBLE : View.GONE ;
            view.setVisibility(visibility);
        }
    }

    @Override
    public void onRefresh() {
        if(monDataReresheListener != null) {
            monDataReresheListener.onRefresh();
        }
    }

}
