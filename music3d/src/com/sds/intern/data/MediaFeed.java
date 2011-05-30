package com.sds.intern.data;

import java.util.ArrayList;

import com.sds.intern.music3d.GridLayer;

import android.content.Context;

public final class MediaFeed implements Runnable {

    public interface Listener {
        public abstract void onFeedAboutToChange(MediaFeed feed);

        public abstract void onFeedChanged(MediaFeed feed, boolean needsLayout);
    }

	public static final String OPERATION_DELETE = null;
	public static final String OPERATION_ROTATE = null;

	public MediaFeed(Context mContext, DataSource dataSource,
			GridLayer gridLayer) {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		// TODO Auto-generated method stub

	}

	public int getNumSlots() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setVisibleRange(int firstVisibleSlotIndex,
			int lastVisibleSlotIndex) {
		// TODO Auto-generated method stub
		
	}

	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	public boolean restorePreviousClusteringState() {
		// TODO Auto-generated method stub
		return false;
	}

	public MediaSet getCurrentSet() {
		// TODO Auto-generated method stub
		return null;
	}

	public void performClustering() {
		// TODO Auto-generated method stub
		
	}

	public void expandMediaSet(int invalid) {
		// TODO Auto-generated method stub
		
	}

	public void removeFilter() {
		// TODO Auto-generated method stub
		
	}

	public DataSource getDataSource() {
		// TODO Auto-generated method stub
		return null;
	}

	public void copySlotStateFrom(MediaFeed feed) {
		// TODO Auto-generated method stub
		
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}

	public boolean isSingleImageMode() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getWaitingForMediaScanner() {
		// TODO Auto-generated method stub
		return false;
	}

	public MediaSet getSetForSlot(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public MediaSet getExpandedMediaSet() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isLoading() {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Integer> getBreaks() {
		// TODO Auto-generated method stub
		return null;
	}

	public void performOperation(String operationDelete,
			ArrayList<MediaBucket> selectedBuckets, Object object) {
		// TODO Auto-generated method stub
		
	}

	public boolean isClustered() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasExpandedMediaSet() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean canExpandSet(int slotIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setFilter(LocationMediaFilter locationMediaFilter) {
		// TODO Auto-generated method stub
		
	}

	public void onPause() {
		// TODO Auto-generated method stub
		
	}

	public void onResume() {
		// TODO Auto-generated method stub
		
	}

	public void updateListener(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public MediaSet getFilteredSet() {
		// TODO Auto-generated method stub
		return null;
	}

}
