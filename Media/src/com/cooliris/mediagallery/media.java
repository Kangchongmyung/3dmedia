package com.cooliris.mediagallery;

import com.cooliris.app.App;
import com.cooliris.app.Res;
import com.cooliris.cache.CacheService;
import com.cooliris.media.*;
import com.cooliris.wallpaper.RandomDataSource;
import com.cooliris.wallpaper.Slideshow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.HashMap;

public final class media extends Activity {
	public static final String REVIEW_ACTION = "com.cooliris.media.action.REVIEW";
    private static final String TAG = "Media";
    
    private App mApp = null;
    private RenderView mRenderView = null;
    private GridLayer mGridLayer;
    private WakeLock mWakeLock;
    private HashMap<String, Boolean> mAccountsEnabled = new HashMap<String, Boolean>();
    private boolean mDockSlideshow = false;
    private int mNumRetries;
    private boolean mImageManagerHasStorageAfterDelay = false;
    private HandlerThread mPicasaAccountThread = new HandlerThread("PicasaAccountMonitor");
    private Handler mPicasaHandler = null;

    private static final int GET_PICASA_ACCOUNT_STATUS = 1;
    private static final int UPDATE_PICASA_ACCOUNT_STATUS = 2;

    private static final int CHECK_STORAGE = 0;
    private static final int HANDLE_INTENT = 1;
    private static final int NUM_STORAGE_CHECKS = 25;
    
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CHECK_STORAGE:
                    checkStorage();
                    break;
                case HANDLE_INTENT:
                    initializeDataSource();
                    break;
            }
        }
    };
    
    private void checkStorage() {
        mNumRetries++;
        mImageManagerHasStorageAfterDelay = ImageManager.hasStorage();
        if (!mImageManagerHasStorageAfterDelay && mNumRetries < NUM_STORAGE_CHECKS) {
            if (mNumRetries == 1) {
                int res;

                if (Environment.isExternalStorageRemovable()) {
                    res = Res.string.no_sd_card;
                } else {
                    res = Res.string.no_usb_storage;
                }

                mApp.showToast(getResources().getString(res), Toast.LENGTH_LONG);
            }
            handler.sendEmptyMessageDelayed(CHECK_STORAGE, 200);
        } else {
            handler.sendEmptyMessage(HANDLE_INTENT);
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = new App(media.this);
        final boolean imageManagerHasStorage = ImageManager.hasStorage();
        boolean slideshowIntent = false;
        if (isViewIntent()) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                slideshowIntent = extras.getBoolean("slideshow", false);
            }
        }
        if (isViewIntent() && getIntent().getData().equals(Images.Media.EXTERNAL_CONTENT_URI) && slideshowIntent) {
            if (!imageManagerHasStorage) {
                int res;

                if (Environment.isExternalStorageRemovable()) {
                    res = Res.string.no_sd_card;
                } else {
                    res = Res.string.no_usb_storage;
                }

                Toast.makeText(this, getResources().getString(res), Toast.LENGTH_LONG).show();
                finish();
            } else {
                Slideshow slideshow = new Slideshow(this);
                slideshow.setDataSource(new RandomDataSource());
                setContentView(slideshow);
                mDockSlideshow = true;
            }
            return;
        }
        mRenderView = new RenderView(this);
        mGridLayer = new GridLayer(this, (int) (96.0f * App.PIXEL_DENSITY), (int) (72.0f * App.PIXEL_DENSITY), new GridLayoutInterface(4),
                mRenderView);
        mRenderView.setRootLayer(mGridLayer);
        setContentView(mRenderView);

        mPicasaAccountThread.start();
        mPicasaHandler = new Handler(mPicasaAccountThread.getLooper()) {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case GET_PICASA_ACCOUNT_STATUS:
                        mAccountsEnabled = PicasaDataSource.getAccountStatus(Gallery.this);
                        break;
                    case UPDATE_PICASA_ACCOUNT_STATUS:
                        updatePicasaAccountStatus();
                        break;
                }
            }
        };
        sendInitialMessage();

        Log.i(TAG, "onCreate");
    }

}