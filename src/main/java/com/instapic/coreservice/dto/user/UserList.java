package com.instapic.coreservice.dto.user;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private List<UserDto> users;
    private int count;

    public UserList() {
        users = new ArrayList<>();
        count = 0;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
