package com.jobforce.company_service.company.impl;

import com.jobforce.company_service.company.Company;
import com.jobforce.company_service.company.CompanyRepository;
import com.jobforce.company_service.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean saveCompany(Company company) {
        if(company != null) {
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCompany(Company company) {
        Optional<Company> companyOptional = companyRepository.findById(company.getId());
        if(companyOptional.isPresent()) {
            Company companyToUpdate = companyOptional.get();

            companyToUpdate.setName(company.getName());
            companyToUpdate.setDescription(company.getDescription());

            companyRepository.save(companyToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompany(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()) {
            companyRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
