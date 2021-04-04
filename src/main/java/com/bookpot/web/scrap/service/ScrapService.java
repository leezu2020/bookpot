package com.bookpot.web.scrap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookpot.web.scrap.dao.ScrapDao;
import com.bookpot.web.scrap.entity.Scrap;

@Service
public class ScrapService implements IScrapService {

	@Autowired
	private ScrapDao scrapDao;

	@Override
	public boolean addScrap(Scrap scrap) {
		// TODO Auto-generated method stub
		return scrapDao.insert(scrap);
	}

	@Override
	public boolean delScrap(Scrap scrap) {
		// TODO Auto-generated method stub
		return scrapDao.delete(scrap);
	}

	@Override
	public boolean isExistScrap(Scrap scrap) {
		// TODO Auto-generated method stub
		return scrapDao.get(scrap);
	}

}
