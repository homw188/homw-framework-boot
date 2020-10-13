package com.homw.ms.common.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.homw.ms.common.bean.CommonResult;

@FeignClient(name = "ms-storage-service", path = "ms-storage/storage")
public interface StorageServiceApi {
	@PostMapping(value = "/decrease")
	CommonResult<?> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
