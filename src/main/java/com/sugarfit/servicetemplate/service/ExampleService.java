package com.sugarfit.servicetemplate.service;

import com.sugarfit.servicetemplate.model.request.ExampleRequest;
import com.sugarfit.servicetemplate.model.response.ExampleResponse;

public interface ExampleService {

    public ExampleResponse defaultMethod(ExampleRequest request);
}
