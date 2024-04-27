package io.aharoj.jobapplication.job.impl;

import java.util.ArrayList;
import java.util.List;

import io.aharoj.jobapplication.job.Job;
import io.aharoj.jobapplication.job.JobService;

/**
 * JobServiceImpl
 * this promomotes loose coupling between the controller and the service
 */
public class JobServiceImpl implements JobService {
  private List<Job> jobs = new ArrayList<>();

  @Override
  public List<Job> findAll() {
    return jobs;
  }

  @Override
  public void createJob(Job job) {
    jobs.add(job);
  }

}
