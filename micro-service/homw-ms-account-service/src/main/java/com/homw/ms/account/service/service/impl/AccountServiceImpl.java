package com.homw.ms.account.service.service.impl;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.homw.ms.account.service.dao.AccountDao;
import com.homw.ms.account.service.service.AccountService;
import com.homw.ms.common.entity.Account;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
	@Resource
	private AccountDao accountDao;

	@Override
	public void decrease(Long userId, BigDecimal money) {
		LOGGER.info("------->account-service中扣减账户余额开始");
		// 模拟超时异常，全局事务回滚
		// 暂停几秒钟线程
		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		accountDao.decrease(userId, money);
		LOGGER.info("------->account-service中扣减账户余额结束");
	}

	@Override
	public int create(Account account) {
		return accountDao.create(account);
	}

	@Override
	public Account getAccountById(Long id) {
		return accountDao.getAccountById(id);
	}
}
