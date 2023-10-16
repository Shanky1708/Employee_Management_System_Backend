package com.employee.employeemanagement.util;

import com.employee.employeemanagement.dto.DepartmentDTO;
import com.employee.employeemanagement.model.Department;

public class DepartmentUtil {
    private DepartmentUtil() {
    }

    public static DepartmentDTO entityToDTO(Department department) {
        return new DepartmentDTO(department.getDepartmentId(), department.getDepartmentName());
    }

    public static Department DTOToEntity(DepartmentDTO departmentDTO) {
        return new Department(null, departmentDTO.getDepartmentName());
    }
}
