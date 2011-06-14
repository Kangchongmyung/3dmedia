package com.sds.intern.data;

import android.content.Context;
import android.graphics.Bitmap;

import com.sds.intern.data.MediaItemTexture.Config;
import com.sds.intern.music3d.RenderView;
import com.sds.intern.music3d.Texture;

public class MediaItemTexture extends Texture {

	public static final class Config {

		public int thumbnailWidth;
		public int thumbnailHeight;

	}

	public MediaItemTexture(Context context, Config config, MediaItem mItemRef) {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Bitmap load(RenderView view) {
		// TODO Auto-generated method stub
		return null;
	}

}
