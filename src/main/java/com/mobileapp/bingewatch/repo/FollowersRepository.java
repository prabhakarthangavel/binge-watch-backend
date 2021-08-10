package com.mobileapp.bingewatch.repo;

import com.mobileapp.bingewatch.entity.Followers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowersRepository extends JpaRepository<Followers, Long> {

    @Query(value = "select * from followers where user_id=?1", nativeQuery = true)
    List<Followers> findByUserId(String loggedInUser);
}
