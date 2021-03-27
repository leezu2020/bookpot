package com.bookpot.web.good.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookpot.web.good.dao.GoodDao;
import com.bookpot.web.good.entity.Good;

@Service
public class GoodService implements IGoodService {

	@Autowired
	private GoodDao goodDao;

	@Override
	public boolean goodUp(Good good) {
		return goodDao.insert(good);
	}

	@Override
	public boolean goodDown(Good good) {
		return goodDao.delete(good);
	}

	@Override
	public boolean isExistGood(Good good) {
		return goodDao.get(good);
	}

}
