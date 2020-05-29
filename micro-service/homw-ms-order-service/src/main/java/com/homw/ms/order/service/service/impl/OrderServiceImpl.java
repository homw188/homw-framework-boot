package com.homw.ms.order.service.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.homw.ms.common.api.AccountService;
import com.homw.ms.common.api.StorageService;
import com.homw.ms.common.entity.Order;
import com.homw.ms.order.service.dao.OrderDao;
import com.homw.ms.order.service.service.OrderService;

import io.seata.spring.annotation.GlobalTransactional;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Resource
	private OrderDao orderDao;
	@Resource
	private StorageService storageService;
	@Resource
	private AccountService accountService;

	@Override
	@GlobalTransactional(name = "order-create", rollbackFor = Exception.class)
	public void create(Order order) {
		log.info("----->开始新建订单");
		// 1 新建订单
		orderDao.create(order);

		// 2 扣减库存
		log.info("----->订单微服务开始调用库存，做扣减Count");
		storageService.decrease(order.getProductId(), order.getCount());
		log.info("----->订单微服务开始调用库存，做扣减end");

		// 3 扣减账户
		log.info("----->订单微服务开始调用账户，做扣减Money");
		accountService.decrease(order.getUserId(), order.getMoney());
		log.info("----->订单微服务开始调用账户，做扣减end");

		// 4 修改订单状态，从零到1, 1代表已经完成
		log.info("----->修改订单状态开始");
		orderDao.update(order.getUserId(), 0);
		log.info("----->修改订单状态结束");

		log.info("----->下订单结束了，O(∩_∩)O哈哈~");
	}
}
