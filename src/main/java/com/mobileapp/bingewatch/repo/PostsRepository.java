package com.mobileapp.bingewatch.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobileapp.bingewatch.entity.Posts;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
