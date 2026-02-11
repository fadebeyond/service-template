package com.sugarfit.servicetemplate.config;

import com.sugarfit.servicetemplate.constants.GlobalConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@Order(2)
public class RequestMetricFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();
        try{
            filterChain.doFilter(request,response);
        }finally {
            long endTime = System.currentTimeMillis();
            long responseTime = endTime-startTime;
            String requestId = MDC.get(GlobalConstants.REQUEST_ID);
            log.info("Took {} ms to complete request-id {} for request type {}",
                    responseTime,requestId,request.getMethod());
        }
    }
}

