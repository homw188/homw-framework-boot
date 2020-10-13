package com.homw.ms.order.service.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homw.ms.common.api.entity.Order;
import com.homw.ms.common.bean.CommonResult;
import com.homw.ms.order.service.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/order")
@Api("order-controller")
public class OrderController {
	@Resource
	private OrderService orderService;

	@PostMapping("/create")
	@ApiOperation("create order")
	public CommonResult<?> create(@RequestBody Order order) {
		orderService.create(order);
		return new CommonResult<>(200, "订单创建成功");
	}

}
