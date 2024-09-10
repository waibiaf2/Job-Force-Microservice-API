package com.jobforce.company_service.company;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();
    Company findById(Long id);
    boolean saveCompany(Company company);
    boolean updateCompany(Company company);
    boolean deleteCompany(Long id);
}
