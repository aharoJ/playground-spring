package io.aharoj.chamba.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.aharoj.chamba.exception.ResourceNotFoundException;
import io.aharoj.chamba.model.Job;
import io.aharoj.chamba.repository.JobRepository;
import io.aharoj.chamba.service.JobService;

/**
 * JobServiceImpl
 */
@Service
public class JobServiceImpl implements JobService {
  @Autowired
  private JobRepository jobRepository;

  // ALL
  @Override
  public List<Job> findAllJobs() {
    return jobRepository.findAll();
  }

  // By id
  @Override
  public Job findJobById(Long id) {
    if (jobRepository.existsById(id)) {
      return jobRepository.findById(id).get();
    } else {
      return null;
    }
    /**
     * Do it by 1 line
     * return jobRepository.findById(id).orElseThrow(null);
     */
  }

  // save job
  @Override
  public Job saveJob(Job job) {
    return jobRepository.save(job);
  }

  // delete job
  @Override
  public Job deleteJob(Long id) {
    if (jobRepository.existsById(id)) {
      jobRepository.deleteById(id);
    } 
    throw new ResourceNotFoundException("Job not found with id: " + id);
  }

  // update job
  @Override
  public Job updateJob(Long id, Job job) {
    Optional<Job> existingJobOptional = jobRepository.findById(id);
    if (existingJobOptional.isPresent()) {
      Job existingJob = existingJobOptional.get();
      existingJob.setTitle(job.getTitle());
      existingJob.setDescription(job.getDescription());
      existingJob.setSalary(job.getSalary());

      return jobRepository.save(existingJob);
    } else {
      throw new ResourceNotFoundException("Job not found with id: " + id);
    }
  }
}
