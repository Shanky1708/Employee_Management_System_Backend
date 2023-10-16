package com.employee.employeemanagement.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.employee.employeemanagement.dto.DepartmentDTO;
import com.employee.employeemanagement.enums.DepartmentField;
import com.employee.employeemanagement.exception.BadRequestException;
import com.employee.employeemanagement.model.Department;
import com.employee.employeemanagement.repository.DepartmentRepository;
import com.employee.employeemanagement.service.DepartmentService;
import com.employee.employeemanagement.util.DepartmentUtil;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Value(value = "${department.id.notFound}")
    private String departmentIdDoesNotExist;

    @Value(value = "${department.name.alreadyExists}")
    private String departmentNameAlreadyExist;

    @Override
    public Page<DepartmentDTO> getDepartments(int pageNo, int pageSize, DepartmentField sortField,
            Sort.Direction sortDirection, String searchQuery) {
        Sort sort = Sort.by(sortDirection, sortField.toString());
        Page<Department> departmentPage = departmentRepository.findByDepartmentNameContaining(searchQuery,
                PageRequest.of(pageNo - 1, pageSize, sort));
        return departmentPage.map(DepartmentUtil::entityToDTO);
    }

    @Override
    public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) throws BadRequestException {
        Optional<Department> departmentOptional = departmentRepository
                .findByDepartmentName(departmentDTO.getDepartmentName());
        if (departmentOptional.isPresent())
            throw new BadRequestException(departmentNameAlreadyExist);
        Department department = departmentRepository.save(DepartmentUtil.DTOToEntity(departmentDTO));
        return DepartmentUtil.entityToDTO(department);
    }

    @Override
    public DepartmentDTO editDepartment(Long departmentId, DepartmentDTO departmentDTO) throws BadRequestException {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        Department department = departmentOptional.orElseThrow(() -> new BadRequestException(departmentIdDoesNotExist));
        departmentOptional = departmentRepository.findByDepartmentName(departmentDTO.getDepartmentName());
        if (departmentOptional.isPresent() && !departmentOptional.get().getDepartmentId().equals(departmentId))
            throw new BadRequestException(departmentNameAlreadyExist);
        department.setDepartmentName(departmentDTO.getDepartmentName());
        return DepartmentUtil.entityToDTO(departmentRepository.save(department));
    }

}
