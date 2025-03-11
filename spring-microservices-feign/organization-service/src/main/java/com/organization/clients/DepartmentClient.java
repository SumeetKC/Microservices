package com.organization.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.organization.model.Department;

@Component
@FeignClient(name = "department-service", url = "http://localhost:8081")
public interface DepartmentClient {
	
	@GetMapping("/organization/{organizationId}")
	List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId);
	
	@GetMapping("/organization/{organizationId}/with-employees")
	List<Department> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId);

}
