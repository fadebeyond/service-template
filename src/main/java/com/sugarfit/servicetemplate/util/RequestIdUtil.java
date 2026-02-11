package com.sugarfit.servicetemplate.util;

import java.util.UUID;

public final class RequestIdUtil {

    private RequestIdUtil() {}

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
