package com.github.raininfall.ys.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/**
 * Created by rongqiansong on 2017/8/17.
 */

public class OnStopEvent extends Event<OnStopEvent> {
		public static final String EVENT_NAME="onStop";

		public OnStopEvent(int viewId) {
				super(viewId);
		}


		@Override
		public String getEventName() {
				return EVENT_NAME;
		}

		@Override
		public void dispatch(RCTEventEmitter rctEventEmitter) {
				rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
		}

		private WritableMap serializeEventData() {
				WritableMap eventData = Arguments.createMap();

				return eventData;
		}
}
