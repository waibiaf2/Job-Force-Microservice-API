package com.jobforce.job_service.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobsController {
    private final JobService jobService;

    public JobsController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.findAll();

        return new ResponseEntity<>(
            jobs,
            HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.findById(id);

        if (job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        if (job != null) {
            jobService.saveJob(job);
            return new ResponseEntity<>(
                "Job created successfully",
                HttpStatus.CREATED
            );
        }

        return new ResponseEntity<>(
            "Job could not be created",
            HttpStatus.NOT_FOUND
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(
        @PathVariable Long id,
        @RequestBody Job job
    ) {
       boolean jobUpdate =  jobService.updateJob(id, job);

       if (jobUpdate) {
           return new ResponseEntity<>(
               "Job updated successfully",
               HttpStatus.OK
           );
       }

        return new ResponseEntity<>(
            "Job not updated",
            HttpStatus.NOT_FOUND
        );
    }

}
