package com.bookpot.web.writing.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookpot.web.criteria.Criteria;
import com.bookpot.web.security.SecurityUser;
import com.bookpot.web.writing.dto.WritingDto;
import com.bookpot.web.writing.entity.Writing;
import com.bookpot.web.writing.service.WritingService;
import com.bookpot.web.writing.view.WritingView;

@Controller
@RequestMapping("/writing")
public class WritingController {

	@Autowired
	private WritingService writingService;
	
	// 모든 목록 출력
	@ResponseBody
	@GetMapping("/view/{type}")
	public List<WritingView> list(@PathVariable String type) {
		System.out.println("viewType : " + type);
		
		// 그리드 뷰
		if(type.equals("grid")) {
			// 그리드형 데이터 가져오기
			
		} else if(type.equals("list")){
			// 리스트형 데이터 가져오기
			
		}
		
		// 빈 criteria로 검색했을때에는?
		Criteria cri = new Criteria();
		
		return writingService.getWritingList(cri);
	}
	
	// 글 등록
	@PostMapping("/reg")
	public void regWriting(@AuthenticationPrincipal SecurityUser user, @RequestParam WritingDto writingDto) {
		
		// 추후에 validator로 유효성 검사 추가하기
		
		// 유저 정보확인 -> 
		if(user == null) {
		//	return exception;
		}
		// 글쓴이 정보 setting
		writingDto.setUserNo(user.getNo());
		
		// 
//		if(writingService.add(writing)) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
		
		// 유저 정보 X
		// bad request로 반환
	}	

	// 글 상세 페이지 출력
	@GetMapping("/{no}")
	@ResponseBody
	public WritingView detail(@PathVariable long no) {
		// 내용 수정
		return writingService.get(no);
	}
	
	// 검색 목록 출력
	@GetMapping("/search")
	@ResponseBody
	public List<WritingView> search() {
		Criteria cri = new Criteria();
		
		cri.setKeyword("");
		cri.setDivision("");
		cri.setCategories(List.of("역사/문화", "예술"));
		cri.setSort("date");
		
		
		return writingService.getWritingList(cri);
	}

		
///////////////////////////////// 구현완료 ///////////////////////////////////////////////////	
	// 글 등록 페이지 이동
	@GetMapping("/reg")
	public String reg() {
		return "writing/reg";
	}

	// 책 제목 검색 DB
	@GetMapping("/title/{keyword}")
	@ResponseBody
	public List<String> srchtitle(@PathVariable String keyword) {
		Criteria srch = new Criteria();
		srch.setKeyword(keyword);
		List<String> list = writingService.getTitleList(srch);
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		return list;
	}
	
	// 글 등록 도서 검색
	@GetMapping(value="/search/{name}/{page}", produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String search(@PathVariable String name, @PathVariable int page) {
		String clientID = "EvnKZwxPFMW7RgOp65SV";
		String clientSecret = "HbVI1XHWWT";
		
		try {
			String text = URLEncoder.encode(name, "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/book_adv.xml?d_titl=" + text + "&start=" + ((page - 1) * 10 + 1);
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientID);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret); // get request
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) {
				System.out.println("정상 호출");
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				System.out.println("호출 오류");
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
			
			System.out.println(response.toString());
			return response.toString();
		} catch (Exception e) {
			System.out.println(e);
			return "실패";
		}
		
	}

}
