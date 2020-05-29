package com.homw.ms.common.handler;

import com.homw.ms.common.bean.CommonResult;

public class FallbackHandler {

	public static CommonResult<?> handleFallback(Throwable e) {
		return new CommonResult<>(455, "失败：" + e.getMessage());
	}
}
