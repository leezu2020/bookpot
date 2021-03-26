package com.bookpot.web.good.service;

import com.bookpot.web.good.entity.Good;

public interface IGoodService {

	// 좋아요 +1
	public boolean goodUp(Good good);

	// 좋아요 -1 (취소)
	public boolean deselectGood(Good good);

	// 좋아요 체크 확인
	// 존재 -> true , 없음 -> false
	public boolean isExistGood(Good good);


}
