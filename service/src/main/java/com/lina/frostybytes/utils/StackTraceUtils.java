package com.lina.frostybytes.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StackTraceUtils {
    private static final int STACK_TRACE_INDEX;

    static {
        STACK_TRACE_INDEX = findIndexOfStackTrace();
    }

    private static int findIndexOfStackTrace() {
        int i = 0;
        for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
            i++;
            if (ste.getClassName().equals(StackTraceUtils.class.getName())) {
                break;
            }
        }
        return i;
    }

    private static StackTraceElement getCurrentInternalStackTraceElement() {
        return Thread.currentThread().getStackTrace()[STACK_TRACE_INDEX + 1];
    }

    public static StackTraceElement getCurrentStackTraceElement() {
        return Thread.currentThread().getStackTrace()[STACK_TRACE_INDEX];
    }

    public static StackTraceElement getPreviousStackTraceElement() {
        return Thread.currentThread().getStackTrace()[STACK_TRACE_INDEX + 1];
    }

    public static String getCurrentMethodName() {
        return getCurrentInternalStackTraceElement().getMethodName();
    }

    public static String getMethodName(StackTraceElement stackTraceElement) {
        return stackTraceElement.getMethodName();
    }

    public static String getCurrentClassName() {
        return getClassName(getCurrentInternalStackTraceElement());
    }

    public static String getClassName(StackTraceElement stackTraceElement) {
        String classPath = stackTraceElement.getClassName();
        return classPath.substring(classPath.lastIndexOf(".") + 1);

    }
}
