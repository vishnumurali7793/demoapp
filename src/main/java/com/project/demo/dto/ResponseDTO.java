package com.project.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseDTO {
	@JsonProperty("status_code")
	private Long statusCode;
	@JsonProperty("message")
	private String message;
	@JsonProperty("data")
	private Object data;
	@JsonProperty("error_code")
	private Long errorCode;
	@JsonProperty("error_message")
	private String errorMessage;

}
