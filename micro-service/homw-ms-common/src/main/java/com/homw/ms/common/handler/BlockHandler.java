package com.homw.ms.common.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.homw.ms.common.bean.CommonResult;

public class BlockHandler {

	public static CommonResult<?> handleBlock(BlockException e) {
		return new CommonResult<>(466, "失败：" + e.getMessage());
	}
}
