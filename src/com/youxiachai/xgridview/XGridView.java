package com.youxiachai.xgridview;

import java.util.ArrayList;

import com.youxiachai.onexlistview.R;
import me.maxwin.view.IXListViewListener;
import me.maxwin.view.XListViewFooter;
import me.maxwin.view.XListViewHeader;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * @author youxiachai
 * @date 2013-5-3
 */
public class XGridView extends GridView implements OnScrollListener {

	protected float mLastY = -1; // save event y
	protected Scroller mScroller; // used for scroll back
	protected OnScrollListener mScrollListener; // user's scroll listener

	// the interface to trigger refresh and load more.
	protected IXListViewListener mListViewListener;

	// -- header view
	protected XListViewHeader mHeaderView;
	// header view content, use it to calculate the Header's height. And hide it
	// when disable pull refresh.
	protected RelativeLayout mHeaderViewContent;
	protected TextView mHeaderTimeView;
	protected int mHeaderViewHeight; // header view's height
	protected boolean mEnablePullRefresh = true;
	protected boolean mPullRefreshing = false; // is refreashing.

	// -- footer view
	protected XListViewFooter mFooterView;
	protected boolean mEnablePullLoad;
	protected boolean mPullLoading;
	protected boolean mIsFooterReady = false;

	// total list items, used to detect is at the bottom of listview.
	protected int mTotalItemCount;

	// for mScroller, scroll back from header or footer.
	protected int mScrollBack;
	protected final static int SCROLLBACK_HEADER = 0;
	protected final static int SCROLLBACK_FOOTER = 1;

	protected final static int SCROLL_DURATION = 400; // scroll back duration
	protected final static int PULL_LOAD_MORE_DELTA = 50; // when pull up >=
															// 50px
															// at bottom,
															// trigger
															// load more.
	protected final static float OFFSET_RADIO = 1.8f; // support iOS like pull
														// feature.

	public XGridView(Context context) {
		super(context);
		initWithContext(context);
	}

	public XGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initWithContext(context);
	}

	public XGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initWithContext(context);
	}

	private void initWithContext(Context context) {
		mScroller = new Scroller(context, new DecelerateInterpolator());
		// XListView need the scroll event, and it will dispatch the event to
		// user's listener (as a proxy).
		super.setOnScrollListener(this);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (mLastY == -1) {
			mLastY = ev.getRawY();
		}

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastY = ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			final float deltaY = ev.getRawY() - mLastY;
			mLastY = ev.getRawY();
		
			if (getFirstVisiblePosition() == 0
					&& ( deltaY > 0)) {
				// the first item is showing, header has shown or pull down.
				updateHeaderHeight(deltaY / OFFSET_RADIO);
			} 
			break;
		default:
			mLastY = -1; // reset
			if (getFirstVisiblePosition() == 0) {
				resetHeaderHeight();
			} 
			break;
		}
		return super.onTouchEvent(ev);
	}



	private void resetHeaderHeight() {
		// TODO Auto-generated method stub
		head = 0;
		setPadding(0, 0, 0, 0);
	
	}
	int head = 0;
	private void updateHeaderHeight(float delta) {
		// TODO Auto-generated method stub
		if(head < 100){
			head += 5;
			setPadding(0, (int) delta + head, 0, 0);
		}
		setSelection(0);
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub

	}



}
