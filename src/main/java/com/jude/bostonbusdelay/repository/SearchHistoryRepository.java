package com.jude.bostonbusdelay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jude.bostonbusdelay.model.SearchHistory;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
    List<SearchHistory> findTop10ByOrderBySearchTimeDesc();
}