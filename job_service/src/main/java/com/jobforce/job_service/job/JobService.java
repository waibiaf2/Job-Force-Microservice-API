package com.jobforce.job_service.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    Job findById(Long id);
    boolean saveJob(Job job);
    boolean updateJob(Long id, Job job);
    boolean deleteJob(Long id);
}
