package com.example.backend.exception;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomMessageException  extends RuntimeException{

    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    private String code;


}
