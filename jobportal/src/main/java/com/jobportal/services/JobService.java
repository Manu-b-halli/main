package com.jobportal.services;

import com.jobportal.entities.Job;
import com.jobportal.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job addJob(Job job) {
        return jobRepository.save(job);
    }
}
