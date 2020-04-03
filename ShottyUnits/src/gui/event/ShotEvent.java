// ShotEvent.java
//
//
//

package gui.event;

import java.awt.AWTEvent;

import gui.event.ShotComponent;

public class ShotEvent extends AWTEvent {
    public static final int SAMPLE_EVENT = AWTEvent.RESERVED_ID_MAX + 1000;

    public ShotEvent(ShotComponent c) {
	    super(c, SAMPLE_EVENT);
    }
}
