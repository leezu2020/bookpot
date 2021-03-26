package com.bookpot.web.tag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookpot.web.tag.dao.TagDao;

@Service
public class TagService implements ITagService{

	@Autowired
	private TagDao tagDao;
	
	@Override
	public Boolean existByName(String name) {
		return (tagDao.getByName(name) != null);
	}

	@Override
	public Boolean regTag(String name) {
		return tagDao.insert(name);
	}

}
