package com.webapp.wooqerassignment.topstories.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
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
import com.webapp.wooqerassignment.utils.AppUtils;

/**
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 */

public class ArticleListRecyclerListAdapter extends RecyclerViewCursorAdapter<RecyclerView.ViewHolder> {

    private Cursor mCursor;
    private Context context;
    private View.OnClickListener mOnClickListener;
    private String highLightString;
    private boolean isItemHighlightRequired = false;

    public ArticleListRecyclerListAdapter(Context context, Cursor cursor, View.OnClickListener onClickListener) {
        this.mCursor = cursor;
        this.context = context;
        this.mOnClickListener = onClickListener;
    }

    public void setItemHighlightRequired(final boolean itemHighlightRequired, final String highLightString) {
        this.isItemHighlightRequired = itemHighlightRequired;
        this.highLightString = highLightString;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final Cursor cursor) {
        AdapterViewHolder adapterViewHolder = (AdapterViewHolder) holder;
        adapterViewHolder.articlePanel.setTag(adapterViewHolder.articlePanel.getId(),getArticleOf(cursor));
        adapterViewHolder.articlePanel.setOnClickListener(mOnClickListener);
        adapterViewHolder.btnShare.setTag(adapterViewHolder.btnShare.getId(),cursor.getString(cursor.getColumnIndex(ArticleTableColoumn.URL)));
        adapterViewHolder.btnShare.setOnClickListener(mOnClickListener);
        adapterViewHolder.time.setText(cursor.getString(cursor.getColumnIndex(ArticleTableColoumn.TIME)));
        adapterViewHolder.score.setText("Score " + cursor.getString(cursor.getColumnIndex(ArticleTableColoumn.SCORE)));
        int readStatus = cursor.getInt(cursor.getColumnIndex(ArticleTableColoumn.READ_STATUS));
        int readStatusColor = readStatus == AppConstants.ReadStatus.READ ? R.color.colorPrimary : R.color.shadowGray;
        adapterViewHolder.icReadStatus.setColorFilter(ContextCompat.getColor(context, readStatusColor));
        adapterViewHolder.tvArticleTitle.setText(cursor.getString(cursor.getColumnIndex(ArticleTableColoumn.TITLE)));

        String articleTitle = cursor.getString(cursor.getColumnIndex(ArticleTableColoumn.TITLE));
        if(isItemHighlightRequired && highLightString != null && highLightString.length() > 0) {
            if (articleTitle != null && articleTitle.toLowerCase().contains(highLightString.toLowerCase())) {
                Spannable wordtoSpan = new SpannableString(articleTitle);
                int startPosition = articleTitle.toLowerCase().indexOf(highLightString.toLowerCase());
                int endPosition = startPosition + highLightString.length();
                wordtoSpan.setSpan(new ForegroundColorSpan(Color.BLUE), startPosition, endPosition,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                adapterViewHolder.tvArticleTitle.setText(wordtoSpan);
            }
        }

        long articlePostTIme = cursor.getLong(cursor.getColumnIndex(ArticleTableColoumn.TIME));
        String author = cursor.getString(cursor.getColumnIndex(ArticleTableColoumn.AUTHOR));
        String timeAuthor = AppUtils.time(articlePostTIme, context) + " by " + author;
        adapterViewHolder.time.setText(timeAuthor);
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

        protected TextView tvArticleTitle, time, score, comments;
        protected ImageView btnShare,icReadStatus;
        protected LinearLayout articlePanel;

        public AdapterViewHolder(final View itemView) {
            super(itemView);
            tvArticleTitle = (TextView) itemView.findViewById(R.id.tvArticleTitle);
            time = (TextView) itemView.findViewById(R.id.time);
            score = (TextView) itemView.findViewById(R.id.score);
            comments = (TextView) itemView.findViewById(R.id.comments);
            btnShare = (ImageView) itemView.findViewById(R.id.btnShare);
            articlePanel = (LinearLayout) itemView.findViewById(R.id.articlePanel);
            icReadStatus = (ImageView) itemView.findViewById(R.id.icReadStatus);
        }
    }
}
