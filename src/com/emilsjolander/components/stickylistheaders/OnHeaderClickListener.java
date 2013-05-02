package com.emilsjolander.components.stickylistheaders;

import android.view.View;
import android.widget.AbsListView;

public interface OnHeaderClickListener {
	public void onHeaderClick(AbsListView l, View header,
			int itemPosition, long headerId, boolean currentlySticky);
}
