package com.mobileapp.bingewatch.controller;

import com.mobileapp.bingewatch.modals.UsersList;
import com.mobileapp.bingewatch.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @GetMapping("/searchPeople/{name}")
    public ResponseEntity<List<UsersList>> addPosts(@PathVariable String name) {
        return ResponseEntity.ok().body(this.peopleService.getUsersList(name));
    }

    @GetMapping("/followPeople/{userId}")
    public ResponseEntity<Long> followRequest(@PathVariable long userId) {
        return ResponseEntity.ok().body(this.peopleService.followPeople(userId));
    }

    @GetMapping("/followingPeople")
    public ResponseEntity<List<Integer>> followingPeoples() {
        return ResponseEntity.ok().body(this.peopleService.getFollowingList());
    }

    @GetMapping("/unfollowPeople/{userId}")
    public ResponseEntity<?> unFollowRequest(@PathVariable long userId) {
        return ResponseEntity.ok().body(this.peopleService.unFollowRequest(userId));
    }

    //People Landing page
    @GetMapping("/followers")
    public ResponseEntity<List<UsersList>> userFollowers() {
        return ResponseEntity.ok().body(this.peopleService.getFollowers());
    }

    //People Landing page
    @GetMapping("/followings")
    public ResponseEntity<List<UsersList>> usersFollowing() {
        return ResponseEntity.ok().body(this.peopleService.getFollowings());
    }
}
