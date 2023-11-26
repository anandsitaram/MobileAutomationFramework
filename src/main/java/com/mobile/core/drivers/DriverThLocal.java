package com.mobile.core.drivers;


import io.appium.java_client.AppiumDriver;

public final class DriverThLocal {
    private DriverThLocal() {

    }

    protected static ThreadLocal<AppiumDriver> driverThread = new ThreadLocal<>();
    protected static ThreadLocal<Boolean> isAndroid = new ThreadLocal<Boolean>();
    protected static ThreadLocal<Boolean> isIos= new ThreadLocal<Boolean>();
    protected static AppiumDriver getAppiumDriver() {
        return driverThread.get();

    }

    protected static void setAppiumDriver(AppiumDriver driver) {

        driverThread.set(driver);
    }

    protected static void unload() {
        driverThread.remove();
    }

    protected static Boolean getIsAndroid() {

        return isAndroid.get();

    }

    protected static void setIsAndroid(Boolean value) {

        isAndroid.set(value);
    }
}