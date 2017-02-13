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
  componentWillMount() {
    ys.showSDKLog(false);
    ys.enableP2P(true);
    ys.initLib('a428ead782174f3ab6db3317843799f9', '', (result) => {
      console.log(`init result ${result}`);
      ys.setAccessToken('at.275ay3em7231p32p0qupg1ozbuumj14i-8oglsvihq7-18xb3wu-l7omgwpza');
    });
  }
  componentDidMount() {
    this.video.play("656492863", 1);
  }

  render() {
    return (
      <View style={styles.container}>
        <View style={styles.container2} >
          <RealPlayView
            ref = { video => this.video = video }
            style={{flex: 1}}/>
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
