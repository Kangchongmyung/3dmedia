package com.sds.intern.data;

import java.util.ArrayList;

public class LocalDataSource implements DataSource {

	public static final Object CAMERA_BUCKET_ID = null;

	public void loadMediaSets(MediaFeed feed) {
		// TODO Auto-generated method stub

	}

	public void loadItemsForSet(MediaFeed feed, MediaSet parentSet,
			int rangeStart, int rangeEnd) {
		// TODO Auto-generated method stub

	}

	public void shutdown() {
		// TODO Auto-generated method stub

	}

	public boolean performOperation(int operation,
			ArrayList<MediaBucket> mediaBuckets, Object data) {
		// TODO Auto-generated method stub
		return false;
	}

	public String[] getDatabaseUris() {
		// TODO Auto-generated method stub
		return null;
	}

	public void refresh(MediaFeed feed, String[] databaseUris) {
		// TODO Auto-generated method stub

	}

}
