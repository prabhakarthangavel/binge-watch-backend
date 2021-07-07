package com.mobileapp.bingewatch.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobileapp.bingewatch.entity.Movies;
import com.mobileapp.bingewatch.entity.Posts;
import com.mobileapp.bingewatch.modals.AddPost;
import com.mobileapp.bingewatch.modals.FetchMovies;
import com.mobileapp.bingewatch.modals.FetchPost;
import com.mobileapp.bingewatch.repo.MoviesRepository;
import com.mobileapp.bingewatch.repo.PostsRepository;
import com.mobileapp.bingewatch.repo.UsersRepo;

@Service
public class PostsService {

	@Autowired
	private PostsRepository postsRepo;

	@Autowired
	private MoviesRepository moviesRepo;

	@Autowired
	private UsersRepo usersRepo;

	@Transactional
	public long createPosts(AddPost request) {
		Posts post = this.postsRepo.fetchPostForUser(this.usersRepo.findusername(request.getUser()).getId(),
				request.getMovie_id());
		if (post == null) {
			return this.newPosts(request);
		} else {
			return this.editPosts(request);
		}
	}

	private long editPosts(AddPost request) {
		return this.postsRepo.updatePost(this.usersRepo.findusername(request.getUser()).getId(), request.getMovie_id(),
				request.getPost_date(), request.getMovie_like(), request.getReview(), request.getTags(),
				request.getStars());
	}

	private long newPosts(AddPost request) {
		long result;
		Posts post = new Posts();
		post.setMovieId(request.getMovie_id());
		post.setPostDate(request.getPost_date());
		post.setMovieLike(request.getMovie_like());
		post.setStars(request.getStars());
		post.setUserId(this.usersRepo.findusername(request.getUser()).getId());
		post.setReview(request.getReview());
		post.setTags(request.getTags());
		post.setCreatedDate(new Date());
		result = this.postsRepo.save(post).getId();
		if (this.moviesRepo.findByMovieId(request.getMovie_id()) == null) {
			Movies movie = new Movies();
			movie.setMovieId(request.getMovie_id());
			movie.setMovieImg(request.getMovie_img());
			movie.setMovieName(request.getMovie_name());
			movie.setYear(request.getYear());
			movie.setCast(request.getCast());
			movie.setCreatedDate(new Date());
			this.moviesRepo.save(movie);
		}
		return result;
	}

	public AddPost fetchPosts(FetchPost fetch) {
		Posts post = this.postsRepo.fetchPostForUser(this.usersRepo.findusername(fetch.getUser()).getId(),
				fetch.getMovie_id());
		AddPost userPost = new AddPost();
		if (post != null) {
			userPost.setMovie_id(post.getMovieId());
			userPost.setPost_date(post.getPostDate());
			userPost.setStars(post.getStars());
			userPost.setMovie_like(post.getMovieLike());
			userPost.setReview(post.getReview());
			userPost.setTags(post.getTags());
		}
		return userPost;
	}
	
	public List<FetchMovies> fetchMoviesList(String userName) {
		List<Posts> posts = this.postsRepo.moviesIdForUser(this.usersRepo.findusername(userName).getId());
		List<FetchMovies> moviesList = new ArrayList<>();
		posts.forEach(post -> {
			Movies entity = this.moviesRepo.fetchMoviesList(post.getMovieId());
			FetchMovies movie = new FetchMovies();
			movie.setCast(entity.getCast());
			movie.setCreatedDate(entity.getCreatedDate());
			movie.setMovieId(entity.getMovieId());
			movie.setMovieImg(entity.getMovieImg());
			movie.setMovieName(entity.getMovieName());
			movie.setRating(post.getStars());
			movie.setYear(entity.getYear());
			movie.setReview(post.getReview());
			movie.setTags(post.getTags());
			moviesList.add(movie);
		});
		return moviesList;
	}
}
