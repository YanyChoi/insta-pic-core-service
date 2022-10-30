package com.instantpic.coreservice.controller;

import com.instantpic.coreservice.dto.follow.FollowDto;
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
    public List<FollowDto> getFollow(String userId, String requestType) {
        List<FollowDto> result = new ArrayList();
        result.add(new FollowDto());
        return result;
    }

}
