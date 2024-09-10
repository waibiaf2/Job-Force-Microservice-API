package com.jobforce.job_service.job.impl;

import com.jobforce.job_service.job.Job;
import com.jobforce.job_service.job.JobRepository;
import com.jobforce.job_service.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean saveJob(Job job) {
        if (job != null) {
            jobRepository.save(job);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job job) {
        Optional<Job> jobOptional = jobRepository.findById(id);

        if (jobOptional.isPresent()) {
            Job jobToUpdate = jobOptional.get();

            jobToUpdate.setTitle(job.getTitle());
            jobToUpdate.setDescription(job.getDescription());
            jobToUpdate.setMinSalary(job.getMinSalary());
            jobToUpdate.setMaxSalary(job.getMaxSalary());
            jobToUpdate.setLocation(job.getLocation());

            jobRepository.save(jobToUpdate);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteJob(Long id) {
        return false;
    }
}
