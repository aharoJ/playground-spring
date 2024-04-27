package io.aharoj.jobapplication.job;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

/**
 * JobController
 */

@RestController
public class JobController {
  public JobController(JobService jobService) {
    this.jobService = jobService;
  }

  private JobService jobService;

  @GetMapping("/jobs")
  public List<Job> findAll() {
    return jobService.findAll();
  }

  @PostMapping("/jobs")
  public String createJob(@RequestBody Job job) {
    jobService.createJob(job);
    return "Job added succesfully";
  }

  @GetMapping("/jobs/{id}")
  // whatever path variable is passed in the url, it will be passed to the method
  public Job getJobById(@PathVariable Long id) {
    Job job = jobService.getJobById(id);
    return job;
  }

}
