/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';
import ys, {RealPlayView} from 'react-native-ys';

export default class demo extends Component {
  closeTimer = null;

  componentWillMount() {
    ys.showSDKLog(false);
    ys.enableP2P(true);
    ys.initLib('3ce9b9a3bbd450ab7de2b0f9c111d32', '', (result) => {
      console.log(`init result ${result}`);
      ys.setAccessToken('at.wngcpkc65ijtij70p3lbi0pb1i290x1-4u81mcnkle-0eesnsi-ypjxhbz3d');
    });
  }

  componentDidMount() {
    this.video.play("49783092", 1);
    closeTimer = setTimeout(() => {
      this.video.stop();
    }, 10 * 1000);
  }

  componentWillUnmount() {
    if (this.closeTimer) {
      clearTimeout(this.closeTimer);
      this.closeTimer = null;
    }
    this.video.stop();
  }

  onPlay = (event) => {
    console.log(event.nativeEvent);
  }

  onStop = () => {
    console.log('Video stopped!');
  }

  render() {
    return (
      <View style={styles.container}>
        <View style={styles.container2} >
          <RealPlayView
            ref = { video => this.video = video }
            style={{flex: 1}}
            onPlay={this.onPlay}
            onStop={this.onStop}
          />
        </View>
        <View style={{flex: 1}}>
          <Text style={styles.welcome}>
            Welcome to React Native!
          </Text>
          <Text style={styles.instructions}>
            To get started, edit index.android.js
          </Text>
          <Text style={styles.instructions}>
            Double tap R on your keyboard to reload,{'\n'}
            Shake or press menu button for dev menu
          </Text>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'space-around',
    backgroundColor: '#F5FCFF',
  },
  container2: {
    flex: 1,
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

AppRegistry.registerComponent('demo', () => demo);
