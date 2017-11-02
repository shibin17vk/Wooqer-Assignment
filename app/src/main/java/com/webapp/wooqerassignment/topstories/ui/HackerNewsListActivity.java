package com.webapp.wooqerassignment.topstories.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.webapp.wooqerassignment.HackerNewsBaseActivity;
import com.webapp.wooqerassignment.R;
import com.webapp.wooqerassignment.constants.AppConstants;
import com.webapp.wooqerassignment.database.ProviderUri;
import com.webapp.wooqerassignment.database.coloumn.ArticleTableColoumn;
import com.webapp.wooqerassignment.model.Article;
import com.webapp.wooqerassignment.network.WebService;
import com.webapp.wooqerassignment.network.WebserviceResponseEntity;
import com.webapp.wooqerassignment.topstories.adapters.ArticleListRecyclerListAdapter;
import com.webapp.wooqerassignment.topstories.controller.HackerNewsListActivityController;
import com.webapp.wooqerassignment.topstories.interfaces.ActivityControllCallback;
import com.webapp.wooqerassignment.utils.AppUtils;
import com.webapp.wooqerassignment.utils.ConnectionState;
import com.webapp.wooqerassignment.utils.OttoClient;
import com.webapp.wooqerassignment.widget.DataContainerLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class HackerNewsListActivity extends HackerNewsBaseActivity implements ActivityControllCallback, LoaderManager
        .LoaderCallbacks<Cursor> , View.OnClickListener{

    @BindView(R.id.dataLayout)
    protected DataContainerLayout dataContainerLayout;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.newsList)
    protected RecyclerView newsList;

    @BindView(R.id.parent)
    protected LinearLayout parent;

    @BindView(R.id.btnSearch)
    protected ImageView btnSearch;

    @BindView(R.id.btnCloseSearch)
    protected ImageView btnCloseSearch;

    @BindView(R.id.serachView)
    protected EditText serachView;

    @BindView(R.id.panelSearch)
    protected FrameLayout panelSearch;

    @BindView(R.id.panelNonSearch)
    protected FrameLayout panelNonSearch;

    private static final int LOADER_ARTICLES        =       1;
    private static final int LOADER_SEARCH_ITEMS    =       2;

    private static final int TOOLBAR_MODE_VIEW      =       1;
    private static final int TOOLBAR_MODE_SEARCH    =       2;

    private int currentArticlePageNumber            =       AppConstants.PAGE_NO_ZERRO;
    private Bus mBus                                =       null;
    private Snackbar mSnackbar                      =       null;
    private int toolBarMode;

    private LinearLayoutManager mLinearLayoutManager                            =   null;
    private ConnectionState mConnectionState                                    =   null;
    private HackerNewsListActivityController mHackerNewsListActivityController  =   null;
    private ProgressDialog mProgressDialog                                      =   null;
    private ArticleListRecyclerListAdapter mArticleListRecyclerListAdapter      =   null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacker_news_list);
        ButterKnife.bind(this);
        initView();
        registerRecyclerViewScrolListener();
        setToolBarMode(TOOLBAR_MODE_VIEW);
        setStatusBarColor(ContextCompat.getColor(this,R.color.statusBarColor));
        mBus = OttoClient.getInstance().getOttoBus();
        mHackerNewsListActivityController = new HackerNewsListActivityController(this,this);
        mHackerNewsListActivityController.getTopArticlesIdList();
    }

    private void initView() {
        dataContainerLayout.showData();
        mProgressDialog = new ProgressDialog(this);
        mArticleListRecyclerListAdapter = new ArticleListRecyclerListAdapter(this,null,this);
        mLinearLayoutManager = new LinearLayoutManager(this);
        newsList.setLayoutManager(mLinearLayoutManager);
        newsList.setAdapter(mArticleListRecyclerListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBus.register(this);
        getSupportLoaderManager().restartLoader(LOADER_ARTICLES, null, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBus.unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onWebserviceCall(final WebserviceResponseEntity webserviceResponseEntity, int state) {
        if(state == WebService.REQ_SUCCESS) {
            getSupportLoaderManager().restartLoader(LOADER_ARTICLES, null, this);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(final int id, final Bundle args) {
        if( id == LOADER_ARTICLES) {
            mArticleListRecyclerListAdapter.setItemHighlightRequired(false,"");
            return new CursorLoader(this, ProviderUri.URI_ARTICLES,null,null,null, ArticleTableColoumn.SCORE+" DESC");

        } else if(id == LOADER_SEARCH_ITEMS) {
            String searchKey = args.getString(AppConstants.TAG_SEARCH_KEY);
            String selection = ArticleTableColoumn.TITLE +" like '%"+searchKey+"%'";
            mArticleListRecyclerListAdapter.setItemHighlightRequired(true,searchKey);
            return new CursorLoader(this, ProviderUri.URI_ARTICLES,null,selection,null, ArticleTableColoumn.SCORE+" DESC");
        }
        return null;
    }

    @Override
    public void onLoadFinished(final Loader<Cursor> loader, final Cursor data) {
        if(loader.getId() == LOADER_ARTICLES && toolBarMode == TOOLBAR_MODE_VIEW) {
            mArticleListRecyclerListAdapter.swapCursor(data);

        } else if(loader.getId() == LOADER_SEARCH_ITEMS && toolBarMode == TOOLBAR_MODE_SEARCH) {
            mArticleListRecyclerListAdapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(final Loader<Cursor> loader) {
        mArticleListRecyclerListAdapter.swapCursor(null);
    }

    @OnTextChanged({R.id.serachView})
    protected void handleOnTextchange(Editable text) {
        String searchKey = text.toString();
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.TAG_SEARCH_KEY, searchKey);
        getSupportLoaderManager().restartLoader(LOADER_SEARCH_ITEMS, bundle, this);
    }

    @OnClick({R.id.btnSearch, R.id.btnCloseSearch})
    @Override
    public void onClick(final View view) {

        switch (view.getId()) {
            case R.id.btnShare:
                String url = (String) view.getTag(view.getId());
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,url);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            case R.id.articlePanel:
                Article article= (Article) view.getTag(view.getId());
                Bundle bundle = new Bundle();
                bundle.putString(AppConstants.TAG_URL, article.getUrl());
                bundle.putLong(AppConstants.TAG_ID, article.getId());
                Intent intent = new Intent(this, HackerNewsDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.btnSearch:
                setToolBarMode(TOOLBAR_MODE_SEARCH);
                break;
            case R.id.btnCloseSearch:
                setToolBarMode(TOOLBAR_MODE_VIEW);
                break;
        }
    }

    private void setToolBarMode(int mode) {
        toolBarMode = mode;
        if(mode == TOOLBAR_MODE_SEARCH) {
            panelNonSearch.setVisibility(View.GONE);
            panelSearch.setVisibility(View.VISIBLE);

        } else if(mode == TOOLBAR_MODE_VIEW){
            serachView.setText("");
            serachView.requestFocus();
            panelNonSearch.setVisibility(View.VISIBLE);
            panelSearch.setVisibility(View.GONE);
            getSupportLoaderManager().restartLoader(LOADER_ARTICLES, null, this);
        }
    }

    private void registerRecyclerViewScrolListener() {
        newsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = mLinearLayoutManager.getItemCount();
                int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
                int visibleThreshold = 4;
                boolean isRequestQuePending = mHackerNewsListActivityController.isLoadingQue();
                if(totalItemCount <= (lastVisibleItem + visibleThreshold) && !isRequestQuePending) {
                    mHackerNewsListActivityController.loadMoreArticles();
                }

            }
        });
    }

    @Subscribe
    public void onNetworkStateChange(ConnectionState connectionState) {
        if(mConnectionState != null && mConnectionState.getConnectionState() == connectionState.getConnectionState()) {
            return;
        }
        mConnectionState = connectionState;
        if(connectionState.getConnectionState() == ConnectionState.CONNECTED) {
            mSnackbar = AppUtils.getSnackbar(this,parent,"Connected", Snackbar.LENGTH_SHORT);
            mSnackbar.show();
        }
        else if(connectionState.getConnectionState() == ConnectionState.NOT_CONNECTED) {
            mSnackbar = AppUtils.getSnackbar(this,parent,"Offline", Snackbar.LENGTH_INDEFINITE);
            mSnackbar.setAction("Retry", new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    mSnackbar.dismiss();
                    mHackerNewsListActivityController.getTopArticlesIdList();
                }
            });
            mSnackbar.show();
        }
    }
}
