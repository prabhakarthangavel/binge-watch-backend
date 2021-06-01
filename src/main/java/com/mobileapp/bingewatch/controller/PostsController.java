package com.mobileapp.bingewatch.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.mobileapp.bingewatch.modals.Response;
import com.mobileapp.bingewatch.service.PostsService;

@RestController
@RequestMapping("/posts")
public class PostsController {
	
	@Autowired
	private PostsService postsService;
	
	@PostMapping("/addPosts")
	public ResponseEntity<Response> addPosts(@RequestBody AddPost request) {
		if(this.postsService.createNewPosts(request) == 0) return ResponseEntity.badRequest()
		        .body(new Response("ERROR OCCURED WHILE SAVING"));
		return ResponseEntity.ok()
		        .body(new Response("SAVED"));
	}

}
