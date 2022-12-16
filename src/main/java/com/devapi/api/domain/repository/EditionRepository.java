package com.devapi.api.domain.repository;

import com.devapi.api.domain.model.Edition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditionRepository extends JpaRepository<Edition, Long> {
}
