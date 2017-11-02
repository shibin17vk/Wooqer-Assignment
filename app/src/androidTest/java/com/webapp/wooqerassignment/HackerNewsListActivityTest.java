package com.webapp.wooqerassignment;

import android.content.Context;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.webapp.wooqerassignment.database.coloumn.ArticleTableColoumn;
import com.webapp.wooqerassignment.topstories.adapters.ArticleListRecyclerListAdapter;
import com.webapp.wooqerassignment.topstories.ui.HackerNewsListActivity;

import org.junit.Test;

/**
 * @author shibin
 * @version 1.0
 * @date 02/11/17
 */

public class HackerNewsListActivityTest  extends ActivityInstrumentationTestCase2<HackerNewsListActivity> {

    private static final String CHAR_A = "a ENTER ";
    private static final String CHAR_B = "b ENTER ";
    private static final String SEARCH_RESULT_RESULT = "ab";


    private EditText serachView;
    private RecyclerView mRecyclerView;
    private HackerNewsListActivity mHackerNewsListActivity;

    public HackerNewsListActivityTest(final Class<HackerNewsListActivity> activityClass) {
        super(HackerNewsListActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mHackerNewsListActivity = getActivity();
        serachView = (EditText) mHackerNewsListActivity.findViewById(R.id.serachView);
        mRecyclerView = (RecyclerView) mHackerNewsListActivity.findViewById(R.id.newsList);
    }

    public void testPreconditions() {
        assertNotNull("HackerNewsListActivityTest is null", mHackerNewsListActivity);
        assertNotNull("serachView is null", serachView);
        assertNotNull("News List Recycler View is null", mRecyclerView);
    }


    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.webapp.wooqerassignment", appContext.getPackageName());
    }

    @Test
    public void testSearchResultEfficiency() throws Exception {
        sendKeys(CHAR_A);
        sendKeys(CHAR_B);
        sendKeys("ENTER");
        if(mRecyclerView.getAdapter().getItemCount() > 0) {
            ArticleListRecyclerListAdapter articleListRecyclerListAdapter = (ArticleListRecyclerListAdapter) mRecyclerView.getAdapter();
            Cursor cursor = articleListRecyclerListAdapter.getItem(0);
            String articleTitle = cursor.getString(cursor.getColumnIndex(ArticleTableColoumn.TITLE));
            assertTrue("Search Result should contain key - " + SEARCH_RESULT_RESULT, articleTitle.contains(SEARCH_RESULT_RESULT));
        }
    }
}
