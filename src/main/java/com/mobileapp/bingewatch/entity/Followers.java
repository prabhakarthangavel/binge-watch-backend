package com.mobileapp.bingewatch.entity;

import javax.persistence.*;

@Entity
@Table(name = "followers")
public class Followers {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private long id;
    @Column(name = "USER_ID")
    private long user_id;
    @Column(name = "FOLLOWERS")
    private String followers;
    @Column(name = "FOLLOWINGS")
    private String followings;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowings() {
        return followings;
    }

    public void setFollowings(String followings) {
        this.followings = followings;
    }
}
