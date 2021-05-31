package com.mobileapp.bingewatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobileapp.bingewatch.modals.AddPost;
import com.mobileapp.bingewatch.service.PostsService;

@RestController
@RequestMapping("/posts")
public class PostsController {
	
	@Autowired
	private PostsService postsService;
	
	@PostMapping("/addPosts")
	public ResponseEntity<String> addPosts(@RequestBody AddPost request) {
		if(this.postsService.createNewPosts(request) == 0) return new ResponseEntity<>("Bad Request",HttpStatus.BAD_REQUEST);	
		return new ResponseEntity<>("Saved",HttpStatus.OK);
	}

}
