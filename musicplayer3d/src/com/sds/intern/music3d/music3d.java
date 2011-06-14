package com.sds.intern.music3d;

import com.sds.intern.app.App;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class music3d extends Activity {
    /** Called when the activity is first created. */
	private static final String TAG = "music3d";
	private App mApp = null;
	private RenderView mRenderView = null;
	private GridLayer mGridLayer = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        mApp = new App(music3d.this);
//        final boolean imageManagerHasStorage = ImageManager.hasStorage();
        boolean slideshowIntent = false;
        if (isViewIntent()) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                slideshowIntent = extras.getBoolean("slideshow", false);
            }
        }
//        if (isViewIntent() && getIntent().getData().equals(Images.Media.EXTERNAL_CONTENT_URI) && slideshowIntent) {
//            if (!imageManagerHasStorage) {
//                Toast.makeText(this, getResources().getString(Res.string.no_sd_card), Toast.LENGTH_LONG).show();
//                finish();
//            } else {
//                Slideshow slideshow = new Slideshow(this);
//                slideshow.setDataSource(new RandomDataSource());
//                setContentView(slideshow);
//                mDockSlideshow = true;
//            }
//            return;
//        }
        mRenderView = new RenderView(this);
        mGridLayer = new GridLayer(this, (int) (96.0f * App.PIXEL_DENSITY), (int) (72.0f * App.PIXEL_DENSITY), new GridLayoutInterface(4),
                mRenderView);
        mRenderView.setRootLayer(mGridLayer);
        setContentView(mRenderView);

//        mPicasaAccountThread.start();
//        mPicasaHandler = new Handler(mPicasaAccountThread.getLooper()) {
//
//            @Override
//            public void handleMessage(Message msg) {
//                switch (msg.what) {
//                    case GET_PICASA_ACCOUNT_STATUS:
//                        mAccountsEnabled = PicasaDataSource.getAccountStatus(Gallery.this);
//                        break;
//                    case UPDATE_PICASA_ACCOUNT_STATUS:
//                        updatePicasaAccountStatus();
//                        break;
//                }
//            }
//        };

//        sendInitialMessage();

        Log.i(TAG, "onCreate");
    }
    
    private boolean isViewIntent() {
        String action = getIntent().getAction();
        return Intent.ACTION_VIEW.equals(action);
    }
}