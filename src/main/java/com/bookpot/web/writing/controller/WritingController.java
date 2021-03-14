package com.bookpot.web.writing.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/writing/")
public class WritingController {

	// REST에 따른 수정 -> db를 어떻게 구성할지?
	@GetMapping("{no}")
	public String detail(@PathVariable int no) {
		return "writing/detail";
	}
	
	@GetMapping("reg")
	public String reg() {
		return "writing/reg";
	}
	
	@GetMapping(value="search/{name}/{page}", produces = "text/plain; charset=UTF-8")
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
