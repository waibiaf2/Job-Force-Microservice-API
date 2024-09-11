package com.jobforce.job_service.job;

import com.jobforce.job_service.dto.JobDto;

import java.util.List;

public interface JobService {
    List<JobDto> findAll();
    JobDto findById(Long id);
    boolean saveJob(Job job);
    boolean updateJob(Long id, Job job);
    boolean deleteJob(Long id);
}
