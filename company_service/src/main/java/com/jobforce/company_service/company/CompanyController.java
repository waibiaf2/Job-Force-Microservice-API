package com.jobforce.company_service.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.findAll();
        if (!companies.isEmpty()) {
            return new ResponseEntity<>(
                companies,
                HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.findById(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        boolean companyCreated = companyService.saveCompany(company);

        if (companyCreated)
            return new ResponseEntity<>(
                "Company created successfully",
                HttpStatus.CREATED
            );
        else
            return new ResponseEntity<>(
                "Company creation failed",
                HttpStatus.BAD_REQUEST
            );

    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(
        @PathVariable Long id,
        @RequestBody Company company
    ) {
        boolean companyUpdated = companyService.updateCompany(id, company);
        if (!companyUpdated) {
            return new ResponseEntity<>(
                "Company update failed",
                HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
            "Company updated successfully",
            HttpStatus.OK
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        boolean companyDeleted = companyService.deleteCompany(id);
        if (!companyDeleted) {
            return new ResponseEntity<>(
                "Company Not Deleted",
                HttpStatus.NOT_FOUND
            );
        }


        return new ResponseEntity<>(
            "Company deleted successfully",
            HttpStatus.OK
        );
    }
}
