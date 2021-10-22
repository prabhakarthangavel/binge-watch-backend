package com.mobileapp.bingewatch.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mobileapp.bingewatch.entity.Users;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
	
	@Query(value = "select * from users where username = ?1", nativeQuery = true)
	Users findusername(String username);

	@Query(value = "select * from users where firstname like %?1% or lastname like %?1%", nativeQuery = true)
	List<Users> searchUsers(String name);

//	@Query(value = "select * from users where id in ?1", nativeQuery = true)
	@Query("SELECT US FROM Users US WHERE US.id IN :ids")
	List<Users> usersByIdList(@Param("ids") List<Long> ids);
}
