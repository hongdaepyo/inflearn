package com.dphong.inflearnthejavatest.study;

import com.dphong.inflearnthejavatest.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
}
