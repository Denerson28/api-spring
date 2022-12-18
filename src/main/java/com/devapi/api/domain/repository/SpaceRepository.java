package com.devapi.api.domain.repository;

import com.devapi.api.domain.model.Space;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceRepository extends JpaRepository<Space, Long> {
}
