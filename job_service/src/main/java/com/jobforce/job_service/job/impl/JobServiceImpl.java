package com.jobforce.job_service.job.impl;

import com.jobforce.job_service.external.Review;
import com.jobforce.job_service.job.Job;
import com.jobforce.job_service.job.JobRepository;
import com.jobforce.job_service.job.JobService;
import com.jobforce.job_service.dto.JobDto;
import com.jobforce.job_service.external.Company;
import com.jobforce.job_service.job.mapper.JobMapper;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobDto> findAll() {
        List<Job> jobs =  jobRepository.findAll();

        return jobs.stream()
            .map(this::covertToDto)
            .collect(Collectors.toList());
    }

    @Override
    public JobDto findById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);

        if (job == null) return null;

        return covertToDto(job);
    }

    @Override
    public boolean saveJob(Job job) {
        if (job != null) {
            jobRepository.save(job);
            return true;
        } else {
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

    private JobDto covertToDto(Job job) {

        Company company = restTemplate.getForObject(
            "http://COMPANY-SERVICE/companies/" + job.getCompanyId(),
            Company.class
        );

        ResponseEntity<List<Review>> reviews = restTemplate.exchange(
            "http://REVIEW-SERVICE/reviews?companyId=" + job.getCompanyId(),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Review>>() {}
        );

        return JobMapper.mapToJobWithCompany(job, company, reviews);
    }
}
