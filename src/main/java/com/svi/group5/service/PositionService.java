package com.svi.group5.service;

import com.svi.group5.entity.Position;

import java.util.List;

public interface PositionService {
    Position findById(Long id);
    List<Position> findAll();
    Position create(Position position);

    Position findByName(String name);
    void delete(Long id);
}
