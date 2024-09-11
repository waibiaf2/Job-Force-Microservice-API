package com.jobforce.job_service.job.mapper;

import com.jobforce.job_service.dto.JobDto;
import com.jobforce.job_service.external.Company;
import com.jobforce.job_service.job.Job;

public class JobMapper {
    public static JobDto mapToJobWithCompany(Job job, Company company) {
        JobDto jobDto = new JobDto();

        jobDto.setId(job.getId());
        jobDto.setTitle(job.getTitle());
        jobDto.setDescription(job.getDescription());
        jobDto.setMinSalary(job.getMinSalary());
        jobDto.setMaxSalary(job.getMaxSalary());
        jobDto.setLocation(job.getLocation());

        jobDto.setCompany(company);

        return jobDto;
    }
}
