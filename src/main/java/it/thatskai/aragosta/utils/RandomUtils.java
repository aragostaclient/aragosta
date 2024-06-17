package it.thatskai.aragosta.utils;

import java.util.ArrayList;
import java.util.Random;

public class RandomUtils {
    private static RandomUtils instance = new RandomUtils();

    public static RandomUtils getRandomUtils() {
        return instance;
    }

    public int getRandomInt(int paramInt1, int paramInt2) {
        return (int)(paramInt1 + Math.random() * (paramInt2 - paramInt1 + 1));
    }

    public static String randomNumber(int length) {
        return random(length, "123456789");
    }
    public static String random(int length, String chars) {
        return random(length, chars.toCharArray());
    }

    public static String random(int length, char[] chars) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++)
            stringBuilder.append(chars[(new Random()).nextInt(chars.length)]);
        return stringBuilder.toString();
    }
}
