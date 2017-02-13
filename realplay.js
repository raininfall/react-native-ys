import React, { Component } from 'react';
import {
  requireNativeComponent,
  View,
  UIManager,
  findNodeHandle,
} from 'react-native';

const iface = {
  name: 'RealPlayView',
  propTypes: {
    ...View.propTypes // 包含默认的View的属性
  },
};

const RCTYSRealPlayView = requireNativeComponent('RCTYSRealPlayView', iface);
const RCT_YS_REAL_PLAY_REF = 'RealPlayView';

export default class RealPlayView extends Component {
  play(deviceSerial, cameraNO){
      UIManager.dispatchViewManagerCommand(
          findNodeHandle(this.refs[RCT_YS_REAL_PLAY_REF]),
          UIManager.RCTYSRealPlayView.Commands.play,
          [deviceSerial, cameraNO]
      );
  }

  stop(){
      UIManager.dispatchViewManagerCommand(
          findNodeHandle(this.refs[RCT_YS_REAL_PLAY_REF]),
          UIManager.RCTYSRealPlayView.Commands.stop,
          null
      );
  }

  render() {
    return (
      <RCTYSRealPlayView
        {...this.props}
        ref = {RCT_YS_REAL_PLAY_REF}
      />
    );
  }
}
