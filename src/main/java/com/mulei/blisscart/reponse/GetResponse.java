package com.mulei.blisscart.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetResponse {




        @JsonProperty("success")
        private Boolean success;

        @JsonProperty("message")
        private String message;

        @JsonProperty("data")
        private List data;

        @JsonProperty("isLastPage")
        private Boolean lastPage;
        public GetResponse(List data, String message, Boolean success, Boolean lastPage) {
            this.data = data;
            this.message = message;
            this.success = success;
            this.lastPage=lastPage;
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


        public Boolean getLastPage() {
            return lastPage;
        }

        public void setLastPage(Boolean lastPage) {
            this.lastPage = lastPage;
        }


}
