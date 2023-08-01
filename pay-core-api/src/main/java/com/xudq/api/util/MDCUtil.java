package com.xudq.api.util;

import org.slf4j.MDC;



public class MDCUtil {

    public static final String TRACE_ID = "traceId";

    public static void putTransId(String requestId) {
        MDC.put(TRACE_ID, requestId);
    }

    public static String getTraceId() {
        return MDC.get(TRACE_ID);
    }
}
