package com.mobileapp.bingewatch.service;

import com.mobileapp.bingewatch.auth.SecurityConfigurer;
import com.mobileapp.bingewatch.entity.Followers;
import com.mobileapp.bingewatch.entity.Users;
import com.mobileapp.bingewatch.modals.UsersList;
import com.mobileapp.bingewatch.repo.FollowersRepository;
import com.mobileapp.bingewatch.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PeopleService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private FollowersRepository followersRepo;

    public List<UsersList> getUsersList(String name) {
        List<UsersList> searchList = new ArrayList<>();
        this.usersRepo.searchUsers(name).forEach(user -> {
            if (!user.getUsername().equalsIgnoreCase(SecurityConfigurer.getLoggedInUser())) {
                UsersList users = new UsersList();
                users.setId(user.getId());
                users.setFirstName(user.getFirstname());
                users.setLastName(user.getLastname());
                users.setUserName(user.getUsername());
                searchList.add(users);
            }
        });
        return searchList;
    }

    public long followPeople(Long userId) {
        long loggedUserId = this.usersRepo.findusername(SecurityConfigurer.getLoggedInUser()).getId();
        Followers followers = new Followers();
        followers.setUser_id(loggedUserId);
        followers.setFollowers(userId.toString() + ",");
        Followers saved = this.followersRepo.save(followers);
        return saved.getId();
    }

    public List<String> getFollowingList() {
        List<String> users = new ArrayList<>();
        this.followersRepo.findByUserId(SecurityConfigurer.getLoggedInUser()).forEach(followers -> {
            users.addAll(Arrays.asList(followers.getFollowings().split(",")));
        });
        return users;
    }
}
