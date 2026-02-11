package com.sugarfit.servicetemplate.model.response;

import com.sugarfit.servicetemplate.enums.ExampleResponseEnum;

public class ExampleResponse {

    private final ExampleResponseEnum result;

    public ExampleResponse(ExampleResponseEnum result) {
        this.result = result;
    }

    public String getResult() {
        return result.name();
    }
}
