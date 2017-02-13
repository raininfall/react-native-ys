package com.github.raininfall.ys;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.RelativeLayout;

import com.facebook.react.bridge.Callback;
import com.videogo.errorlayer.ErrorInfo;
import com.videogo.openapi.EZConstants.EZRealPlayConstants;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.EZPlayer;

import javax.annotation.Nullable;

public class YSRealPlayView extends RelativeLayout implements SurfaceHolder.Callback, Handler.Callback {
  /* Play Status */
  static private final int STATUS_INIT = 0;
  static private final int STATUS_STOP = 1;
  static private final int STATUS_PLAY = 2;
  /* Video Output View */
  private final SurfaceView mSurfaceView;
  private SurfaceHolder mSurfaceHolder = null;
  private EZPlayer mPlayer = null;
  /* Player Message Handler */
  private Handler mPlayerHandler = null;
  /* Lifecycle flag*/
  private boolean mFinishing = false;
  /* Playing flag */
  int mPlayStatus = STATUS_INIT;
  private Callback mPlayStatusCallback = null;
  /* Props */
  private String mDeviceSerial = "";
  private int mCameraNO = 0;

  private void handleGetCameraInfoSuccess() {
    //TODO: add functional button on view
  }

  private void handlePlaySuccess(Message msg) {
    //TODO: here
    Log.i("RealPlayView", "Plat Start!");
  }

  private void handlePlayFail(Object object) {
    if (null != object) {
      ErrorInfo errorInfo = (ErrorInfo) object;
      Log.e("RealPlayView", errorInfo.description);
    }
  }

  private void handleSetVideoModeSuccess() {
    //TODO: here
  }

  private void handleSetVideoModeFail(int arg) {
    //TODO: here
  }

  private void handlePtzControlFail(Message msg) {
    //TODO: here
  }

  private void handleVoiceTalkSucceed() {
    //TODO: here
  }

  private void handleVoiceTalkStopped(boolean talking) {
    //TODO: here
  }

  private void handleVoiceTalkFailed(Message msg) {
    //TODO: here
  }

  @Override
  public boolean handleMessage(Message msg) {
    if (this.isFinishing()) {
      return false;
    }
    switch (msg.what) {
      case EZRealPlayConstants.MSG_REALPLAY_STOP_SUCCESS:
        Log.i("RealPlayView", "Plat Stop!");
        break;
      case EZRealPlayConstants.MSG_GET_CAMERA_INFO_SUCCESS:

        handleGetCameraInfoSuccess();
        break;
      case EZRealPlayConstants.MSG_REALPLAY_PLAY_START:

        break;
      case EZRealPlayConstants.MSG_REALPLAY_CONNECTION_START:

        break;
      case EZRealPlayConstants.MSG_REALPLAY_CONNECTION_SUCCESS:

        break;
      case EZRealPlayConstants.MSG_REALPLAY_PLAY_SUCCESS:
        handlePlaySuccess(msg);
        break;
      case EZRealPlayConstants.MSG_REALPLAY_PLAY_FAIL:
        handlePlayFail(msg.obj);
        break;
      case EZRealPlayConstants.MSG_SET_VEDIOMODE_SUCCESS:
        handleSetVideoModeSuccess();
        break;
      case EZRealPlayConstants.MSG_SET_VEDIOMODE_FAIL:
        handleSetVideoModeFail(msg.arg1);
        break;
      case EZRealPlayConstants.MSG_PTZ_SET_FAIL:
        handlePtzControlFail(msg);
        break;
      case EZRealPlayConstants.MSG_REALPLAY_VOICETALK_SUCCESS:
        handleVoiceTalkSucceed();
        break;
      case EZRealPlayConstants.MSG_REALPLAY_VOICETALK_STOP:
        handleVoiceTalkStopped(false);
        break;
      case EZRealPlayConstants.MSG_REALPLAY_VOICETALK_FAIL:
        handleVoiceTalkFailed(msg);
        break;
      default:
        break;
    }
    return false;
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    mFinishing = true;
  }

  private boolean isFinishing() {
    return mFinishing;
  }

  public YSRealPlayView(Context context) {
    super(context);

    LayoutInflater.from(context).inflate(R.layout.real_play, this);
    /* video output view */
    mSurfaceView = (SurfaceView) findViewById(R.id.play);
    mSurfaceHolder = mSurfaceView.getHolder();
    mSurfaceHolder.addCallback(this);
    /* Player Message Handler init*/
    mPlayerHandler = new Handler(this);
    /* Ready to play */
    mPlayStatus = STATUS_STOP;
  }

  public void play(String deviceSerial, int cameraNO) {
    mDeviceSerial = deviceSerial;
    mCameraNO = cameraNO;
    if (mPlayStatus == STATUS_STOP) {
      startRealPlay();
    }
    Log.e("RealPlayView", deviceSerial + " " + cameraNO + " " + mPlayStatus);
  }

  private void startRealPlay() {
    /* ezplayer init */
    if (null == mPlayer) {
      mPlayer = EZOpenSDK.getInstance().createPlayer(mDeviceSerial, mCameraNO);
    }
    if (null == mPlayer) {
      return;
    }
    mPlayer.setHandler(mPlayerHandler);
    mPlayer.setSurfaceHold(mSurfaceHolder);
    mPlayer.startRealPlay();

    mPlayStatus = STATUS_PLAY;
  }
  public void stop() {
    if (mPlayStatus != STATUS_STOP) {
      stopRealPlay();
    }
  }

  private void stopRealPlay() {
    if (null != mPlayer) {
      mPlayer.stopRealPlay();
    }
    mPlayStatus = STATUS_STOP;
  }

  @Override
  public void surfaceCreated(SurfaceHolder surfaceHolder) {
    if (null != mPlayer) {
      mPlayer.setSurfaceHold(surfaceHolder);
    }
    mSurfaceHolder = surfaceHolder;
  }

  @Override
  public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
    if (null != mPlayer) {
      Log.i("RealPlayView", "Call play stop");
      stopRealPlay();
      //下面语句防止stopRealPlay线程还没释放surface, startRealPlay线程已经开始使用surface
      //因此需要等待500ms
      SystemClock.sleep(500);
      // 开始播放
      mPlayer.setSurfaceHold(surfaceHolder);
      Log.i("RealPlayView", "Call play start");
      startRealPlay();

    }
    mSurfaceHolder = surfaceHolder;
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    if (null != mPlayer) {
      mPlayer.setSurfaceHold(null);
    }
    mSurfaceHolder = null;
  }
}
