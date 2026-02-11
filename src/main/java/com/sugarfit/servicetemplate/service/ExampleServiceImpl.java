package com.sugarfit.servicetemplate.service;

import com.sugarfit.servicetemplate.enums.ExampleResponseEnum;
import com.sugarfit.servicetemplate.model.request.ExampleRequest;
import com.sugarfit.servicetemplate.model.response.ExampleResponse;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl implements ExampleService {

    public ExampleResponse defaultMethod(ExampleRequest request) {
        return new ExampleResponse(ExampleResponseEnum.SUCCESS);
    }
}
