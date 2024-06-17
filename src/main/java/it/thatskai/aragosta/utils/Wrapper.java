package it.thatskai.aragosta.utils;

import org.lwjgl.input.Keyboard;

public class Wrapper {

    public static int getKey(String keyname) {
        return Keyboard.getKeyIndex(keyname.toUpperCase());
    }

    public static String getKeyName(int keycode) {
        return Keyboard.getKeyName(keycode);
    }
}
