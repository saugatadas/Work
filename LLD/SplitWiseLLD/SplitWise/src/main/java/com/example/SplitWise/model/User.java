package com.example.SplitWise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity(name="SPLITWISE_USER")
public class User  extends BaseModel{
    private String name;
    private String email;
    private String password;
    @ManyToMany
    private List<User> friends;
    @ManyToMany
    private List<Group> groups;

    public User() {
        friends = new ArrayList<>();
        groups = new ArrayList<>();
    }
}
