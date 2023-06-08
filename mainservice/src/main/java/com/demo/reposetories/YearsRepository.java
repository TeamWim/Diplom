package com.demo.reposetories;

import com.demo.domain.Years;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YearsRepository extends JpaRepository<Years, Long> {
}
