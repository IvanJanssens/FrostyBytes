package com.lina.frostybytes.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class LogUtils {
    public static void logError(StackTraceElement stackTraceElement, Throwable error) {
        log.error("%s#%s: ".formatted(
                StackTraceUtils.getClassName(stackTraceElement),
                StackTraceUtils.getMethodName(stackTraceElement)
        ), error);
    }

    public static void logError(StackTraceElement stackTraceElement, String message, Throwable error) {
        log.error("%s#%s: %s".formatted(
                StackTraceUtils.getClassName(stackTraceElement),
                StackTraceUtils.getMethodName(stackTraceElement),
                message
        ), error);
    }

    public static void onError(Throwable error) {
        logError(StackTraceUtils.getPreviousStackTraceElement(), error);
    }
}
