package io.aharoj.chamba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.aharoj.chamba.model.Job;

/**
 * JobRepository
 */
public interface JobRepository extends JpaRepository<Job, Long> {

}
