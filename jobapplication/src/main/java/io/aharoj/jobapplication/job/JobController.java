package io.aharoj.jobapplication.job;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * JobController
 */

@RestController
public class JobController {

  @GetMapping("/jobs")
  public List<Job> findAll() {
    return jobs;
  }

  @PostMapping("/jobs")
  public String createJob(@RequestBody Job job){
    jobs.add(job);
    return "Job added succesfully";
  }

}
