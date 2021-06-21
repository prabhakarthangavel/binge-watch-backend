package com.mobileapp.bingewatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobileapp.bingewatch.modals.AddPost;
import com.mobileapp.bingewatch.modals.FetchPost;
import com.mobileapp.bingewatch.modals.Response;
import com.mobileapp.bingewatch.service.PostsService;

@RestController
@RequestMapping("/posts")
public class PostsController {

	@Autowired
	private PostsService postsService;

	@PostMapping("/addPosts")
	public ResponseEntity<Response> addPosts(@RequestBody AddPost request) {
		long id = this.postsService.createPosts(request);
		if (id == 0) {
			return ResponseEntity.badRequest().body(new Response("ERROR OCCURED WHILE SAVING"));
		} else if (id == 1) {
			return ResponseEntity.ok().body(new Response("Post Updated Successfully!"));
		} else {
			return ResponseEntity.ok().body(new Response("Post Created Successfully!"));
		}
	}

	@PostMapping("/editPosts")
	public ResponseEntity<AddPost> editPosts(@RequestBody FetchPost request) {
		return ResponseEntity.ok().body(this.postsService.fetchPosts(request));
	}

}
