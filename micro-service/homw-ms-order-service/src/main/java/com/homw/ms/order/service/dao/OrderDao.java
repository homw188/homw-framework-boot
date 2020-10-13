package com.homw.ms.order.service.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.homw.ms.common.api.entity.Order;

@Mapper
public interface OrderDao {
	void create(Order order);

	void update(@Param("userId") Long userId, @Param("status") Integer status);
}
