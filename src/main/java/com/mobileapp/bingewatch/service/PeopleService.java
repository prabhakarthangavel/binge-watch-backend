package com.mobileapp.bingewatch.service;

import com.mobileapp.bingewatch.auth.SecurityConfigurer;
import com.mobileapp.bingewatch.entity.Followers;
import com.mobileapp.bingewatch.modals.UsersList;
import com.mobileapp.bingewatch.repo.FollowersRepository;
import com.mobileapp.bingewatch.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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

    @Transactional
    public long followPeople(Long userId) {
        long loggedUserId = this.usersRepo.findusername(SecurityConfigurer.getLoggedInUser()).getId();

        List<Followers> existUser = this.followersRepo.findByUserId(loggedUserId);
        if (existUser.isEmpty()) {
            Followers followers = new Followers();
            followers.setUser_id(loggedUserId);
            followers.setFollowers(userId.toString() + ",");
            Followers saved = this.followersRepo.save(followers);
            return userId;
        } else {
            this.followersRepo.updateFollowers(existUser.get(0).getFollowings() + userId + ",", loggedUserId);
            return userId;
        }
    }

    @Transactional
    public Long unFollowRequest(long userId) {
        long loggedUserId = this.usersRepo.findusername(SecurityConfigurer.getLoggedInUser()).getId();

        List<Followers> existUser = this.followersRepo.findByUserId(loggedUserId);
        AtomicReference<String> followings = new AtomicReference<>("");
        Arrays.stream(existUser.get(0).getFollowings().split("\\,")).map(value -> Long.parseLong(value)).filter(id -> id != userId).forEach(id -> {
            followings.updateAndGet(v -> v + id);
            followings.updateAndGet(v -> v + ",");
        });
        this.followersRepo.updateFollowers(followings.toString(), loggedUserId);
        return userId;
    }

    public List<Integer> getFollowingList() {
        List<Integer> users = new ArrayList<>();
        this.followersRepo.findByUserId(this.usersRepo.findusername(SecurityConfigurer.getLoggedInUser()).getId()).forEach(following -> {
            users.addAll(Arrays.stream(following.getFollowings().split("\\,")).map(value -> Integer.parseInt(value)).collect(Collectors.toList()));
        });
        return users;
    }

    private List<Long> getFollowersIds() {
        List<Long> users = new ArrayList<>();
        this.followersRepo.findByUserId(this.usersRepo.findusername(SecurityConfigurer.getLoggedInUser()).getId()).forEach(followers -> {
            users.addAll(Arrays.stream(followers.getFollowers().split("\\,")).map(value -> Long.parseLong(value)).collect(Collectors.toList()));
        });
        return users;
    }

    private List<Long> getFollowingsIds() {
        List<Long> users = new ArrayList<>();
        this.followersRepo.findByUserId(this.usersRepo.findusername(SecurityConfigurer.getLoggedInUser()).getId()).forEach(followers -> {
            users.addAll(Arrays.stream(followers.getFollowings().split("\\,")).map(value -> Long.parseLong(value)).collect(Collectors.toList()));
        });
        return users;
    }

    public List<UsersList> getFollowers() {
        List<UsersList> userNames = new ArrayList<>();
        this.usersRepo.usersByIdList(getFollowersIds()).forEach(user -> {
            UsersList usersList = new UsersList();
            usersList.setUserName(user.getUsername());
            usersList.setFirstName(user.getFirstname());
            usersList.setLastName(user.getLastname());
            usersList.setId(user.getId());
            userNames.add(usersList);
        });
        return userNames;
    }

    public List<UsersList> getFollowings() {
        List<UsersList> userNames = new ArrayList<>();
        this.usersRepo.usersByIdList(getFollowingsIds()).forEach(user -> {
            UsersList usersList = new UsersList();
            usersList.setUserName(user.getUsername());
            usersList.setFirstName(user.getFirstname());
            usersList.setLastName(user.getLastname());
            usersList.setId(user.getId());
            userNames.add(usersList);
        });
        return userNames;
    }
}
