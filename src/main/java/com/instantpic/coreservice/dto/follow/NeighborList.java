package com.instantpic.coreservice.dto.follow;

import java.util.ArrayList;
import java.util.List;

public class NeighborList {

    private List<NeighborDto> neighbors;
    private int count;

    public NeighborList() {
        neighbors = new ArrayList<NeighborDto>();
        count = 0;
    }

    public List<NeighborDto> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<NeighborDto> neighbors) {
        this.neighbors = neighbors;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
