package com.mobileapp.bingewatch.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mobileapp.bingewatch.entity.Posts;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {

	@Query(value = "SELECT * FROM POSTS WHERE USER_ID=?1 AND MOVIE_ID=?2", nativeQuery = true)
	Posts fetchPostForUser(long user_id, String movie_id);

	@Modifying
	@Query(value = "UPDATE POSTS SET POST_DATE=?3, MOVIE_LIKE=?4, REVIEW=?5, TAGS=?6, STARS=?7 WHERE USER_ID=?1 AND MOVIE_ID=?2", nativeQuery = true)
	int updatePost(long user_id, String movie_id, Date post_date, char movie_like, String review, String tags,
			int stars);

	@Query(value = "SELECT MOVIE_ID FROM POSTS WHERE USER_ID=?1", nativeQuery = true)
	List<String> moviesIdForUser(long user_id);
}