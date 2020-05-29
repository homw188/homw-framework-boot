package com.homw.ms.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description common result for rest api
 * @author Hom
 * @version 1.0
 * @since 2020-05-29
 */
@ApiModel(description = "common result for rest api")
public class CommonResult<T> {
	@ApiModelProperty(value = "represent status", example = "200")
	private Integer code;
	@ApiModelProperty(value = "description of result", example = "success")
	private String message;
	@ApiModelProperty(value = "content of result, arbitrary type")
	private T data;
	
	public CommonResult() {
	}

	public CommonResult(Integer code, String message) {
		this(code, message, null);
	}

	public CommonResult(Integer code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

}
