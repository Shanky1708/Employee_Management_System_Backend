package com.employee.employeemanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.employee.employeemanagement.dto.DepartmentDTO;
import com.employee.employeemanagement.enums.DepartmentField;
import com.employee.employeemanagement.exception.BadRequestException;

public interface DepartmentService {
    public Page<DepartmentDTO> getDepartments(int pageNo, int pageSize, DepartmentField sortField,
            Sort.Direction sortDirection, String searchQuery);

    public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) throws BadRequestException;

    public DepartmentDTO editDepartment(Long departmentId, DepartmentDTO departmentDTO) throws BadRequestException;
}
