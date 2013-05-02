package com.youxiachai.xgridview;

import android.view.View;
import android.widget.ListAdapter;

public class FixedViewInfo {
	   /** The view to add to the list */
    public View view;
    /** The data backing the view. This is returned from {@link ListAdapter#getItem(int)}. */
    public Object data;
    /** <code>true</code> if the fixed view should be selectable in the list */
    public boolean isSelectable;
}
