package com.homw.ms.common.api;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.homw.ms.common.bean.CommonResult;
import com.homw.ms.common.entity.Account;

@FeignClient(name = "ms-account-service", path = "ms-account")
public interface AccountService {
	@PostMapping(value = "/account/decrease")
	CommonResult<?> decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
	
	@PostMapping(value = "/account/create")
	CommonResult<?> create(@RequestBody Account account);
	
	@GetMapping(value = "/account/get/{id}")
	CommonResult<?> getAccountById(@PathVariable("id") Long id);
}
