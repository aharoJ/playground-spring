package io.aharoj.chamba.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.aharoj.chamba.model.Job;
import io.aharoj.chamba.service.JobService;

/**
 * JobController
 */
@Controller
@RequestMapping("job")
public class JobController {
  private JobService jobService;

  public JobController(JobService jobService) {
    this.jobService = jobService;
  }

  @GetMapping()
  public ResponseEntity<List<Job>> findAllJobs() {
    List<Job> jobs = jobService.findAllJobs();
    return new ResponseEntity<>(jobs, HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<Job> findJobById(@PathVariable Long id) {
    Job job = jobService.findJobById(id);
    return new ResponseEntity<>(job, HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<Job> saveJob(@RequestBody Job job) {
    Job saveJob = jobService.saveJob(job);
    return new ResponseEntity<>(saveJob, HttpStatus.CREATED);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Job> deleteJob(@PathVariable Long id) {
    Job deleteJob = jobService.deleteJob(id);
    return new ResponseEntity<>(deleteJob, HttpStatus.OK);
  }

  @PutMapping("{id}")
  public ResponseEntity<Job> updateJob(@PathVariable Long id, Job job) {
    Job updateJob = jobService.updateJob(id, job);
    return new ResponseEntity<>(updateJob, HttpStatus.OK);
  }
}