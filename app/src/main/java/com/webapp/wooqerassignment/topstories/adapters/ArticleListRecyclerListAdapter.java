package com.webapp.wooqerassignment.topstories.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webapp.wooqerassignment.R;
import com.webapp.wooqerassignment.constants.AppConstants;
import com.webapp.wooqerassignment.database.coloumn.ArticleTableColoumn;
import com.webapp.wooqerassignment.model.Article;

/**
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 */

public class ArticleListRecyclerListAdapter extends RecyclerViewCursorAdapter<RecyclerView.ViewHolder> {

    private Cursor mCursor;
    private Context context;
    private View.OnClickListener mOnClickListener;

    public ArticleListRecyclerListAdapter(Context context, Cursor cursor, View.OnClickListener onClickListener) {
        this.mCursor = cursor;
        this.context = context;
        this.mOnClickListener = onClickListener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final Cursor cursor) {
        AdapterViewHolder adapterViewHolder = (AdapterViewHolder) holder;
        adapterViewHolder.articlePanel.setTag(adapterViewHolder.articlePanel.getId(),getArticleOf(cursor));
        adapterViewHolder.articlePanel.setOnClickListener(mOnClickListener);
        adapterViewHolder.btnShare.setTag(adapterViewHolder.btnShare.getId(),cursor.getString(cursor.getColumnIndex(ArticleTableColoumn.URL)));
        adapterViewHolder.btnShare.setOnClickListener(mOnClickListener);
        adapterViewHolder.tvArticleTitle.setText(cursor.getString(cursor.getColumnIndex(ArticleTableColoumn.TITLE)));
        adapterViewHolder.time.setText(cursor.getString(cursor.getColumnIndex(ArticleTableColoumn.TIME)));
        adapterViewHolder.score.setText("Score " + cursor.getString(cursor.getColumnIndex(ArticleTableColoumn.SCORE)));
        adapterViewHolder.comments.setText("3 Comments");

        int readStatus = cursor.getInt(cursor.getColumnIndex(ArticleTableColoumn.READ_STATUS));
        if(readStatus == AppConstants.ReadStatus.READ) {
            adapterViewHolder.icReadStatus.setVisibility(View.VISIBLE);
            adapterViewHolder.articlePanel.setBackgroundColor(ContextCompat.getColor(context, R.color.bkgDim));
        } else {
            adapterViewHolder.icReadStatus.setVisibility(View.GONE);
            adapterViewHolder.articlePanel.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        }
    }

    private Article getArticleOf(Cursor cursor) {
        Article article = new Article();
        article.setId(cursor.getLong(cursor.getColumnIndex(ArticleTableColoumn.ARTICLE_ID)));
        article.setTitle(cursor.getString(cursor.getColumnIndex(ArticleTableColoumn.TITLE)));
        article.setUrl(cursor.getString(cursor.getColumnIndex(ArticleTableColoumn.URL)));
        return article;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_article_list,parent, false);
        return new ArticleListRecyclerListAdapter.AdapterViewHolder(view);
    }

    static class AdapterViewHolder extends RecyclerView.ViewHolder {

        protected TextView tvArticleTitle, time, score, comments,icReadStatus;
        protected ImageView btnShare;
        protected LinearLayout articlePanel;

        public AdapterViewHolder(final View itemView) {
            super(itemView);
            tvArticleTitle = (TextView) itemView.findViewById(R.id.tvArticleTitle);
            time = (TextView) itemView.findViewById(R.id.time);
            score = (TextView) itemView.findViewById(R.id.score);
            comments = (TextView) itemView.findViewById(R.id.comments);
            btnShare = (ImageView) itemView.findViewById(R.id.btnShare);
            articlePanel = (LinearLayout) itemView.findViewById(R.id.articlePanel);
            icReadStatus = (TextView) itemView.findViewById(R.id.icReadStatus);
        }
    }
}
