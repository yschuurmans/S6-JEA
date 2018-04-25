import {Injectable} from '@angular/core';

import {Observable} from "rxjs/Observable";
import {Observer} from "rxjs/Observer";

const WEBSOCKETURL = 'ws://localhost:8080/Kwetter/ws';

@Injectable()
export class SocketService {

  private serverMessages: string[];

  private wsTimeline: WebSocket;

  constructor() {
    console.log("OPENING CONNECTION");
    this.wsTimeline = new WebSocket(WEBSOCKETURL + "/timeline");

    console.log("AWAITING CONNECTION");
    this.wsTimeline.onopen = (() => {
      console.log("Connection is now open");
    })
  }

  subscribeTimeline(username: string) {

    this.wsTimeline.onopen = (() => {
      console.log("subscribing to timeline of:" + username);
      this.wsTimeline.send(username);
    });

    return Observable.create(
      (observer: Observer<MessageEvent>) => {
        this.wsTimeline.onmessage = observer.next.bind(observer);
        this.wsTimeline.onerror = observer.error.bind(observer);
        this.wsTimeline.onclose = observer.complete.bind(observer);
      }
    );

  }
}
