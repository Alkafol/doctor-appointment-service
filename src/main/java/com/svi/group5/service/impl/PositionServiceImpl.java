package com.svi.group5.service.impl;

import com.svi.group5.dao.PositionRepository;
import com.svi.group5.entity.Position;
import com.svi.group5.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Position findById(Long id) {
        return positionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public Position create(Position position) {
        return positionRepository.save(position);
    }

    @Override
    public Position findByName(String name) {
        return positionRepository.findByName(name);
    }

    @Override
    public void delete(Long id) {
        Position position = findById(id);
        if (!position.getDoctors().isEmpty()) {
            throw new IllegalStateException();
        }

        positionRepository.deleteById(id);
    }
}
