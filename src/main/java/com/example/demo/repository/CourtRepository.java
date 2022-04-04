package com.example.demo.repository;

import com.example.demo.model.Court;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepository extends PagingAndSortingRepository<Court, String> {
}
