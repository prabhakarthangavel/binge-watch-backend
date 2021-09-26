package com.mobileapp.bingewatch.repo;

import com.mobileapp.bingewatch.entity.Followers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

    public interface FollowersRepository extends JpaRepository<Followers, Long> {

    @Query(value = "select * from followers where user_id=?1", nativeQuery = true)
    List<Followers> findByUserId(long loggedInUser);

    @Modifying
    @Query("UPDATE Followers FS SET FS.followings=:followings WHERE FS.user_id=:user_id")
    void updateFollowers(@Param("followings") String followings, @Param("user_id") long user_id);
}
