package com.homw.ms.account.service.controller;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homw.ms.account.service.service.AccountService;
import com.homw.ms.common.bean.CommonResult;
import com.homw.ms.common.entity.Account;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/account")
@Api("account-controller")
public class AccountController {

	private static final Logger log = LoggerFactory.getLogger(AccountController.class);

	@Resource
	private AccountService accountService;

	@Value("${server.port}")
	private String serverPort;

	@PostMapping("/decrease")
	@ApiOperation("decrease user account")
	public CommonResult<?> decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
		accountService.decrease(userId, money);
		return new CommonResult<>(200, "扣减账户余额成功！");
	}

	@PostMapping("/create")
	@ApiOperation("create user account")
	public CommonResult<?> create(@RequestBody Account account) {
		int result = accountService.create(account);
		log.info("*****插入结果：" + result);

		if (result > 0) {
			return new CommonResult<>(200, "插入数据库成功,serverPort: " + serverPort, result);
		} else {
			return new CommonResult<>(444, "插入数据库失败");
		}
	}

	@GetMapping("/get/{id}")
	@ApiOperation("get user account by id")
	public CommonResult<Account> getAccountById(@PathVariable("id") Long id) {
		Account account = accountService.getAccountById(id);

		if (account != null) {
			return new CommonResult<>(200, "查询成功,serverPort:  " + serverPort, account);
		} else {
			return new CommonResult<>(444, "没有对应记录,查询ID: " + id);
		}
	}

}
