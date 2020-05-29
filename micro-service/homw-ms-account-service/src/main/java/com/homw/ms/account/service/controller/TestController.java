package com.homw.ms.account.service.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.homw.ms.common.bean.CommonResult;
import com.homw.ms.common.handler.BlockHandler;
import com.homw.ms.common.handler.FallbackHandler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RefreshScope // 支持nacos动态刷新
@RequestMapping("/test")
@Api("test-controller")
public class TestController {

	private static final Logger log = LoggerFactory.getLogger(TestController.class);

	@Resource
	private DiscoveryClient discoveryClient;

	@Value("${server.port}")
	private String serverPort;

	@Value("${spring.application.name}")
	private String serviceName;

	@Value("${config.info}")
	private String configInfo;

	@GetMapping(value = "/discovery")
	@ApiOperation(value = "get discovery info", notes = "expose discovery detail info")
	public CommonResult<DiscoveryClient> discovery() {
		List<String> services = discoveryClient.getServices();
		for (String element : services) {
			log.info("*****element: " + element);
		}

		List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
		for (ServiceInstance instance : instances) {
			log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t"
					+ instance.getUri());
		}
		return new CommonResult<>(200, "查询成功,serverPort: " + serverPort, this.discoveryClient);
	}

	@GetMapping("/lb/port")
	@ApiOperation(value = "get instance port", notes = "different instance port is returned according to different load balancing strategies.")
	public CommonResult<?> getLoadBalancePort() {
		return new CommonResult<>(200, "查询成功", serverPort);
	}

	@GetMapping("/config/nacos")
	@ApiOperation(value = "get nacos configuration info", notes = "expose dynamic configuration from nacos")
	public CommonResult<?> getConfigInfoNacos() {
		return new CommonResult<>(200, "查询成功,serverPort: " + serverPort, configInfo);
	}

	@GetMapping("/config/sentinel")
	@SentinelResource(value = "flow-control", blockHandler = "handleBlock", blockHandlerClass = {
			BlockHandler.class }, fallback = "handleFallback", fallbackClass = { FallbackHandler.class })
	@ApiOperation(value = "get nacos configuration info", notes = "expose dynamic configuration from nacos, support flow control, circuit & degradation.")
	public CommonResult<?> getConfigInfoSentinel() {
		// 模拟异常
		// System.out.println(1 / 0);
		return new CommonResult<>(200, "查询成功,serverPort: " + serverPort, configInfo);
	}
}
