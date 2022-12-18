package com.devapi.api.domain.repository;

import com.devapi.api.domain.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
