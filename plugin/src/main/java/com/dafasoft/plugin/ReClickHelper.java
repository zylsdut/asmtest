package com.dafasoft.plugin;

public class ReClickHelper {
    private static long currentTime;

    public static boolean clickEnable() {
        return clickEnable(500);
    }

    public static boolean clickEnable(long interval) {
        boolean clickEnable = true;
        if (Math.abs(System.currentTimeMillis() - currentTime) < interval) {
            clickEnable = false;
        } else {
            currentTime = System.currentTimeMillis();
        }
        return clickEnable;
    }

    public static void clickEnable500() {
        if (!clickEnable()) {
            return;
        }
    }
}