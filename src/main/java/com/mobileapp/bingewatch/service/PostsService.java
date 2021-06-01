package com.mobileapp.bingewatch.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobileapp.bingewatch.entity.Movies;
import com.mobileapp.bingewatch.entity.Posts;
import com.mobileapp.bingewatch.modals.AddPost;
import com.mobileapp.bingewatch.repo.MoviesRepository;
import com.mobileapp.bingewatch.repo.PostsRepository;

@Service
public 	class PostsService {
	
	@Autowired
	private PostsRepository postsRepo;
	 
	@Autowired
	private MoviesRepository moviesRepo;

	public double createNewPosts(AddPost request) {
		long result = 0;
		Posts post = new Posts();
		post.setMovieId(request.getMovie_id());
		post.setPostDate(request.getPost_date());
		post.setMovieLike(request.getMovie_like());
		post.setStars(request.getStars());
		post.setUserId(request.getUser_id());
		post.setReview(request.getReview());
		post.setTags(request.getTags());
		post.setCreatedDate(new Date());
		result = postsRepo.save(post).getId();
		if(moviesRepo.findByMovieId(request.getMovie_id()) == null) {
			Movies movie = new Movies();
			movie.setMovieId(request.getMovie_id());
			movie.setMovieImg(request.getMovie_img());
			movie.setMovieName(request.getMovie_name());
			movie.setYear(request.getYear());
			movie.setCast(request.getCast());
			movie.setCreatedDate(new Date());
			moviesRepo.save(movie);
		}
		return result;
	}
}
