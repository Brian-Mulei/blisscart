package com.mulei.blisscart.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mulei.blisscart.dto.OrderResponseDTO;
import com.mulei.blisscart.model.Order;

import java.util.List;

public class OrderResponse {

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private OrderResponseDTO data;

    public OrderResponse(OrderResponseDTO data, String message, Boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public OrderResponseDTO getData() {
        return data;
    }

    public void setData(OrderResponseDTO data) {
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
