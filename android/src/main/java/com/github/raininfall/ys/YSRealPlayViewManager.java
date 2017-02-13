package com.github.raininfall.ys;

import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

import javax.annotation.Nullable;

public class YSRealPlayViewManager extends SimpleViewManager<YSRealPlayView> {
  private static final int COMMAND_PLAY_ID = 1;
  private static final String COMMAND_PLAY_NAME = "play";
  private static final int COMMAND_STOP_ID = 2;
  private static final String COMMAND_STOP_NAME = "stop";
  private static final Map<String, Integer> COMMAND_MAP =  MapBuilder.of(
          COMMAND_PLAY_NAME, COMMAND_PLAY_ID,
          COMMAND_STOP_NAME, COMMAND_STOP_ID
  );

  public static final String REACT_CLASS = "RCTYSRealPlayView";

  @Nullable
  @Override
  public Map<String, Integer> getCommandsMap() {
    return COMMAND_MAP;
  }

  @Override
  public void receiveCommand(YSRealPlayView root, int commandId, @Nullable ReadableArray args) {
    switch (commandId) {
      case COMMAND_PLAY_ID:
        root.play(args.getString(0), args.getInt(1));
        return;
    }
  }

  @Override
  public String getName() {
    Log.i("Self Define", "RCTYSRealPlayView name");
    return REACT_CLASS;
  }

  @Override
  protected YSRealPlayView createViewInstance(ThemedReactContext reactContext) {
    Log.i("Self Define", "RCTYSRealPlayView");
    final YSRealPlayView view = new YSRealPlayView(reactContext);
    Log.i("RCTYSRealPlayView", "width: " + view.getWidth() + " height: "+ view.getHeight() );
    return view;
  }
}
