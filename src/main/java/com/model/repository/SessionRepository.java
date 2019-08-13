package com.model.repository;

import com.model.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SessionRepository extends PagingAndSortingRepository<Session, Long> {
    List<Session> findAllBySessionDateOrderByStartTime(LocalDate sessionDate);
    Page<Session> findAllBySessionDateOrderByStartTime(LocalDate sessionDate, Pageable pageable);
}
