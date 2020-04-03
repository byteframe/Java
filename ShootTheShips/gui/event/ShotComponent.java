// ShotComponent.java
//
//
//

package gui.event;

import java.awt.*;

import gui.event.ShotEvent;
import gui.event.ShotListener;

public class ShotComponent extends Component {
    private ShotListener shotlistener;

    ShotComponent() {
        enableEvents(0);
    }

    public void addShotListener(ShotListener l) {
        shotlistener = l;
    }

    public void processEvent(AWTEvent e) {
        if (e instanceof ShotEvent) {
          if(shotlistener != null) {
            shotlistener.shotHappened(ShotEvent /////// );
          }
        } else {
          super.processEvent(e);
        }
    }

    { // what is
      EventQueue evtq = Toolkit.getDefaultToolkit().getSystemEventQueue();
      ShotEvent se = new ShotEvent(this);
      evtq.postEvent(se);
    }
}
