package com.demo.reposetories;

import com.demo.domain.Years;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YearRepository extends JpaRepository<Years, Long> {
}
