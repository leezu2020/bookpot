package com.bookpot.web.writing.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookpot.web.search.Criteria;
import com.bookpot.web.search.PageDto;
import com.bookpot.web.security.SecurityUser;
import com.bookpot.web.writing.dto.WritingDto;
import com.bookpot.web.writing.service.WritingService;
import com.bookpot.web.writing.validator.WritingValidator;
import com.bookpot.web.writing.view.WritingView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/writings")
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
	


	// 글 상세 페이지 출력
	@GetMapping("/{no}")
	public String detail(@PathVariable long no, Model model) {
		// 내용 수정
		model.addAttribute("writing", writingService.get(no));
		
		
		return "writing/detail";
	}
	
	// 검색 목록 출력
	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> search(Criteria cri) {		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		
//		if(user != null) {
//			cri.setUserNo(user.getNo());
//		}
		cri.setUserNo((long)38);
		for(int i=0; i<cri.getCategories().size(); i++)
			System.out.println("분야 : " + cri.getCategories().get(i));

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//페이징 처리
		int writingNum = writingService.getWritingNum(cri);
		PageDto paging = new PageDto(writingNum, cri.getPage(), cri.getPerPage());
		// 요청된 페이지는 없는 페이지
		if(cri.getPage() > paging.getEndPage()) {
			return new ResponseEntity<HashMap<String,Object>>(HttpStatus.NOT_FOUND);
		}
		
		map.put("pageNum", paging.getPageList());
		map.put("paging", paging.getBtn());
		
		map.put("writing", writingService.getWritingList(cri));
		
		// url 주소 처리하기
		map.put("url", "/search?keyword=&division=&categories=");
		return new ResponseEntity<HashMap<String,Object>>(map, HttpStatus.OK);
	}
	
	// 글 등록
	@PostMapping(value = "", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> regWriting(@RequestBody WritingDto writingDto,
			BindingResult bindingResult) {
		
		// validator로 유효성 검사 추가하기
		
		WritingValidator validator = new WritingValidator();
		validator.validate(writingDto, bindingResult);
		if (bindingResult.hasErrors()) {
			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, String> map = new HashMap<>();

			for (FieldError e : bindingResult.getFieldErrors()) {
				System.out.println(e.getField() + " : " + e.getDefaultMessage());
				map.put(e.getField(), e.getDefaultMessage());
			}
			try {
				String json = mapper.writeValueAsString(map);
				System.out.println(json);
				return new ResponseEntity<String>(json, HttpStatus.BAD_REQUEST);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		} // 해시태그 정보 기입

		// 카테고리 정보 기입

		// 유저 정보확인 ->
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		if (user != null) {
			// 글쓴이 정보 setting
			writingDto.setUserNo(user.getNo());
		} else {
			// 로그인 안되어있다면
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}

		if (writingService.add(writingDto)) {
			return new ResponseEntity<>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
		
///////////////////////////////// 구현완료 ///////////////////////////////////////////////////	

	
	// 글 등록 페이지 이동
	@GetMapping("/reg")
	public String reg() {
		return "writing/write";
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
