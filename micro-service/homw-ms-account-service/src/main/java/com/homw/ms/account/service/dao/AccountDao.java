package com.homw.ms.account.service.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.homw.ms.common.api.entity.Account;

@Mapper
public interface AccountDao {
	int decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
	
	int create(Account account);

	Account getAccountById(@Param("id") Long id);
}
