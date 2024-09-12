package com.jobforce.job_service.job.mapper;

import com.jobforce.job_service.dto.JobDto;
import com.jobforce.job_service.external.Company;
import com.jobforce.job_service.external.Review;
import com.jobforce.job_service.job.Job;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class JobMapper {
    public static JobDto mapToJobWithCompany(
        Job job,
        Company company,
        ResponseEntity<List<Review>> reviews
    ) {
        JobDto jobDto = new JobDto();

        jobDto.setId(job.getId());
        jobDto.setTitle(job.getTitle());
        jobDto.setDescription(job.getDescription());
        jobDto.setLocation(job.getLocation());
        jobDto.setMinSalary(job.getMinSalary());
        jobDto.setMaxSalary(job.getMaxSalary());

        jobDto.setCompany(company);
        jobDto.setReviews(reviews.getBody());

        return jobDto;
    }
}
