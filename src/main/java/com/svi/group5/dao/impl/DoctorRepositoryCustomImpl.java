package com.svi.group5.dao.impl;

import com.svi.group5.dao.DoctorRepositoryCustom;
import com.svi.group5.dao.PositionRepository;
import com.svi.group5.entity.Doctor;
import com.svi.group5.entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DoctorRepositoryCustomImpl implements DoctorRepositoryCustom {
    private final EntityManager em;
    private final PositionRepository positionRepository;

    DoctorRepositoryCustomImpl(EntityManager em, PositionRepository positionRepository) {
        this.em = em;
        this.positionRepository = positionRepository;
    }

    @Override
    public List<Doctor> find(Map<String, Object> filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Doctor> cq = cb.createQuery(Doctor.class);

        Root<Doctor> doctor = cq.from(Doctor.class);
        List<Predicate> predicates = new ArrayList<>();

        if (filter.containsKey("experienceLowerBound")) {
            predicates.add(cb.greaterThanOrEqualTo(doctor.get("experience"), filter.get("experienceLowerBound").toString()));
        }
        if (filter.containsKey("positionId")) {
            Position position = positionRepository.findById(Long.valueOf(filter.get("positionId").toString())).orElseThrow();
            predicates.add(cb.equal(doctor.get("position"), position));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }
}
