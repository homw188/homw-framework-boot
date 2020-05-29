package com.homw.ms.storage.service.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.homw.ms.storage.service.dao.StorageDao;
import com.homw.ms.storage.service.service.StorageService;

@Service
public class StorageServiceImpl implements StorageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

	@Resource
	private StorageDao storageDao;

	@Override
	public void decrease(Long productId, Integer count) {
		LOGGER.info("------->storage-service中扣减库存开始");
		storageDao.decrease(productId, count);
		LOGGER.info("------->storage-service中扣减库存结束");
	}
}
