package it.thatskai.aragosta.events.impl;

import it.thatskai.aragosta.events.Event;

public class KeyEvent extends Event {
    private final int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
