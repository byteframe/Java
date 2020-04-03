// ShotListener.java
//
// listener interface for components that listen to ShotEvents
//

package gui.event;

import gui.event.ShotEvent;

public interface ShotListener {
    public void shotHappened(ShotEvent se);
}
