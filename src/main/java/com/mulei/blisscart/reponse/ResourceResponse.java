package com.mulei.blisscart.reponse;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResourceResponse {

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private List data;

    public ResourceResponse(List data, String message, Boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
