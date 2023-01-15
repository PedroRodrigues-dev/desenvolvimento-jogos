package com.pedro.world;

public class Camera {

    public static int x = 0;
    public static int y = 0;

    public static int clamp(int currentValue, int minimunValue, int maximunValue) {
        if (currentValue < minimunValue) {
            currentValue = minimunValue;
        }

        if (currentValue > maximunValue) {
            currentValue = maximunValue;
        }

        return currentValue;
    }
}
