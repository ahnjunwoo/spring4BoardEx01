package org.zerock.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;
import org.zerock.test.BoardDAOTest;

@RestController
@RequestMapping("/replies")
public class ReplyController {

	@Inject
	private ReplyService service;
	
	private static Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO vo){
		ResponseEntity<String> entity = null;
		logger.debug("댓글등록 컨트롤러 시작!");
		try{
			service.addReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value="/all/{bno}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") Integer bno){
		ResponseEntity<List<ReplyVO>> entity = null;
		try{
			entity = new ResponseEntity<>(service.listReply(bno), HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value="/{bno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("bno") Integer bno,@PathVariable("page") Integer page){
		ResponseEntity<Map<String, Object>> entity = null;
		try{
			Criteria cri = new Criteria();
			cri.setPage(page);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			
			Map<String, Object> map = new HashMap<String,Object>();
			List<ReplyVO> list = service.listReplyPage(bno, cri);
			map.put("list", list);
			
			int replyCount = service.count(bno);
			pageMaker.setTotalCount(replyCount);
			
			map.put("pageMaker", pageMaker);
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
