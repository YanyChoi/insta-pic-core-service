package com.instantpic.coreservice.dto.follow;

public class NeighborDto {

    private String neighborId;

    private String profilePic;

    public NeighborDto() {
        neighborId = "";
        profilePic = "";
    }

    public String getNeighborId() {
        return neighborId;
    }

    public void setNeighborId(String neighborId) {
        this.neighborId = neighborId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
