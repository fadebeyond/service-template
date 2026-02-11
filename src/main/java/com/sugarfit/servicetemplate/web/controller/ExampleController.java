package com.sugarfit.servicetemplate.web.controller;

import com.sugarfit.servicetemplate.constants.GlobalConstants;
import com.sugarfit.servicetemplate.model.common.ApiResponse;
import com.sugarfit.servicetemplate.model.request.ExampleRequest;
import com.sugarfit.servicetemplate.model.response.ExampleResponse;
import com.sugarfit.servicetemplate.service.ExampleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ExampleController {

    private final ExampleService exampleService;


    @PostMapping("/test")
    public ApiResponse<ExampleResponse> test(
            @Valid @RequestBody ExampleRequest request
    ) {
        ExampleResponse response = exampleService.defaultMethod(request);
        return ApiResponse.success(MDC.get(GlobalConstants.REQUEST_ID), null);
    }
}

