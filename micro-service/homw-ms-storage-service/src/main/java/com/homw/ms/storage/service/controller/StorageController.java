package com.homw.ms.storage.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homw.ms.common.bean.CommonResult;
import com.homw.ms.storage.service.service.StorageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/storage")
@Api("storage-controller")
public class StorageController {

	@Autowired
	private StorageService storageService;

	@PostMapping("/decrease")
	@ApiOperation("decrease product storage")
	public CommonResult<?> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
		storageService.decrease(productId, count);
		return new CommonResult<>(200, "扣减库存成功！");
	}
}
