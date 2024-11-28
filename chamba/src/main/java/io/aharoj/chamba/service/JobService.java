package io.aharoj.chamba.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.aharoj.chamba.model.Job;

/**
 * JobService
 */
@Service
public interface JobService {
  // ALL
  List<Job> findAllJobs();

  // By id
  Job findJobById(Long id);

  // save job
  Job saveJob(Job job);

  // update job
  Job updateJob(Long id, Job job);

  // delete job
  Job deleteJob(Long id);
}
