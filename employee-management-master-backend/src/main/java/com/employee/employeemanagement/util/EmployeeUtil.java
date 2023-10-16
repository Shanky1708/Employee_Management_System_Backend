package com.employee.employeemanagement.util;

import com.employee.employeemanagement.dto.EmployeeDTO;
import com.employee.employeemanagement.model.Employee;

public class EmployeeUtil {

    private EmployeeUtil() {
    }

    public static EmployeeDTO entityToDTO(Employee employee) {
        return new EmployeeDTO(employee.getEmployeeId(), employee.getEmployeeFirstName(),
                employee.getEmployeeLastName(),
                employee.getEmployeeEmail(), DepartmentUtil.entityToDTO(employee.getEmployeeDepartment()));
    }

    public static Employee DTOToEntity(EmployeeDTO employeeDTO) {
        return new Employee(null, employeeDTO.getEmployeeFirstName(), employeeDTO.getEmployeeLastName(),
                employeeDTO.getEmployeeEmail(), null);
    }
}
