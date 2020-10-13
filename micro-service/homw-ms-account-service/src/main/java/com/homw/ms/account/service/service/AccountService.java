package com.homw.ms.account.service.service;

import java.math.BigDecimal;

import com.homw.ms.common.api.entity.Account;

public interface AccountService {
	void decrease(Long userId, BigDecimal money);
	
	int create(Account account);

	Account getAccountById(Long id);
}
