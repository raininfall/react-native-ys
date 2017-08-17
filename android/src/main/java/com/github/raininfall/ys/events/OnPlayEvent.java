package com.github.raininfall.ys.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/**
 * Created by rongqiansong on 2017/8/17.
 */

public class OnPlayEvent extends Event<OnPlayEvent> {
		public static final String EVENT_NAME="onPlay";

		private final int code;
		private final String info;

		public OnPlayEvent(int viewId, int code, String info) {
				super(viewId);

				this.code = code;
				this.info = info;
		}

		@Override
		public String getEventName() {
				return EVENT_NAME;
		}

		@Override
		public void dispatch(RCTEventEmitter rctEventEmitter) {
				rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
		}

		private int getCode() {
				return this.code;
		}

		private String getInfo() {
				return this.info;
		}

		private WritableMap serializeEventData() {
				WritableMap eventData = Arguments.createMap();
				eventData.putDouble("code", getCode());
				eventData.putString("info", getInfo());

				return eventData;
		}
}
