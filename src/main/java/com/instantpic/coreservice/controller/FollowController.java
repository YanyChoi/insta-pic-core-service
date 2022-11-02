package com.instantpic.coreservice.controller;

import com.instantpic.coreservice.dto.follow.FollowDto;
import com.instantpic.coreservice.dto.follow.FollowList;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowController {
    @PostMapping
    public FollowDto follow(String userId, String followId) {

        return new FollowDto();
    }
    @DeleteMapping
    public FollowDto unfollow(String userId, String followId) {

        return new FollowDto();
    }
    @GetMapping
    public FollowList getFollow(String userId, String requestType) {
        FollowList result = new FollowList();
        switch (requestType) {
            case "followers":

                break;
            case "following":

                break;
            case "neighbors":

                break;
        }
        return result;
    }
}
