package com.dafasoft.asmtest;

public class ReClickHelper {
    private static long currentTime;

    public static boolean clickEnable() {
        return !clickEnable(500);
    }

    public static boolean clickEnable(long interval) {
        System.out.println("-------------------------clickEnable");
        boolean clickEnable = true;
        if (Math.abs(System.currentTimeMillis() - currentTime) < interval) {
            clickEnable = false;
        } else {
            currentTime = System.currentTimeMillis();
        }
        return clickEnable;
    }






    public static void clickEnable500() {
        boolean enable = clickEnable();
        if (!enable) {
            return;
        }
    }
}