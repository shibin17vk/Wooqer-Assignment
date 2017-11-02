package com.webapp.wooqerassignment.topstories.adapters;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;

/**
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 */


public abstract class RecyclerViewCursorAdapter<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    private Cursor cursor;

    public Cursor swapCursor(final Cursor cursor) {
        this.cursor = cursor;
        this.notifyDataSetChanged();
        return cursor;
    }

    public Cursor getItem(final int position) {
        if (this.cursor != null && !this.cursor.isClosed()) {
            this.cursor.moveToPosition(position);
        }

        return this.cursor;
    }

    public Cursor getCursor() {
        return this.cursor;
    }

    @Override
    public final void onBindViewHolder(final VH holder, final int position) {
        final Cursor cursor = this.getItem(position);
        this.onBindViewHolder(holder, cursor);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) {
            return 0;
        }
        return this.cursor.getCount();
    }

    public abstract void onBindViewHolder(final VH holder, final Cursor cursor);
}
