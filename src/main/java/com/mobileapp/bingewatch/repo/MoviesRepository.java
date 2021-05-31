package com.mobileapp.bingewatch.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mobileapp.bingewatch.entity.Movies;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Long> {
	
	@Query(value = "SELECT ID FROM MOVIES WHERE MOVIE_ID = ?1", nativeQuery = true)
	long findByMovieId(String movie_id);
	
}
