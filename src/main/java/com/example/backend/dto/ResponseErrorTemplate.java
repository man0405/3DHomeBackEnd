package com.example.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;



public record ResponseErrorTemplate(
        String message,
        String code,
        @JsonProperty("data")
        Object object
        ) {

}
