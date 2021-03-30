package com.bookpot.web.scrap.service;

import com.bookpot.web.scrap.entity.Scrap;

public interface IScrapService {

	// 좋아요 +1
	public boolean addScrap(Scrap scrap);

	// 좋아요 -1 (취소)
	public boolean delScrap(Scrap scrap);

	// 좋아요 체크 확인
	// 존재 -> true , 없음 -> false
	public boolean isExistScrap(Scrap scrap);


}
