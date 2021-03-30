package com.bookpot.web.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookpot.web.comment.dto.CommentDto;
import com.bookpot.web.comment.service.ICommentService;
import com.bookpot.web.comment.vo.CommentVo;

@RestController
@RequestMapping("/")
public class CommentController {
	
	@Autowired
	private ICommentService commentService;

	@PostMapping("writings/{writingNo}/comments")
	public ResponseEntity<String> regComments(@PathVariable long writingNo,
			@RequestBody CommentDto commentDto){

//		유저 정보 가져오기
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		SecurityUser user = (SecurityUser) authentication.getPrincipal();		
		commentDto.setWritingNo(writingNo);
		commentDto.setUserNo((long)11);
		
		System.out.println(commentDto);
		
		return commentService.regComments(commentDto) ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("writings/{writingNo}/comments")
	public ResponseEntity<List<CommentVo>> getComments(@PathVariable long writingNo){
		
		List<CommentVo> list = commentService.getList(writingNo);
		System.out.println("list사이즈 : " + list.size());
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i) + " 시간 : " + list.get(i).getRegDate());
		}
		return !list.isEmpty() ? new ResponseEntity<List<CommentVo>>(list, HttpStatus.OK)
				: new ResponseEntity<List<CommentVo>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("comments/{commentNo}")
	public ResponseEntity<String> modComments(@PathVariable(name="commentNo") long commentNo,
			@RequestBody CommentDto commentDto){
		
		// 유저 정보 확인
		
		// 유저가 쓴 댓글이 맞는지 확인
		
		commentDto.setNo(commentNo);
		
		
		return commentService.modify(commentDto) ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("comments/{commentNo}")
	public ResponseEntity<String> delComments(@PathVariable long commentNo){
		
		return commentService.delete(commentNo) ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
