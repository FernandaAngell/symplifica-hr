package com.symplifica.backend.employee.service;

import com.symplifica.backend.employee.dto.request.CreateEmployeeRequest;
import com.symplifica.backend.employee.dto.response.EmployeeResponse;
import com.symplifica.backend.employee.entity.Employee;
import com.symplifica.backend.employee.mapper.EmployeeMapper;
import com.symplifica.backend.employee.repository.EmployeeBenefitRepository;
import com.symplifica.backend.employee.repository.EmployeeRepository;
import com.symplifica.backend.nominatim.dto.NominatimResponse;
import com.symplifica.backend.nominatim.service.NominatimService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;
    private final NominatimService nominatimService;
    private final EmployeeBenefitRepository employeeBenefitRepository;

    public EmployeeService(
            EmployeeRepository repository,
            NominatimService nominatimService,
            EmployeeBenefitRepository employeeBenefitRepository) {

        this.repository = repository;
        this.nominatimService = nominatimService;
        this.employeeBenefitRepository = employeeBenefitRepository;
    }

    public List<EmployeeResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }

    public EmployeeResponse findById(UUID id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        return EmployeeMapper.toResponse(employee);
    }

    public EmployeeResponse save(CreateEmployeeRequest request) {

        Employee employee = EmployeeMapper.toEntity(request);

        NominatimResponse location = nominatimService.searchCity(request.getCity());

        if (location != null) {
            employee.setLatitude(new BigDecimal(location.getLat()));
            employee.setLongitude(new BigDecimal(location.getLon()));
            employee.setDisplayName(location.getDisplayName());
        }

        employee = repository.save(employee);

        return EmployeeMapper.toResponse(employee);
    }
    
    public EmployeeResponse update(UUID id, CreateEmployeeRequest request) {

    Employee employee = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

    employee.setFirstName(request.getFirstName());
    employee.setLastName(request.getLastName());
    employee.setEmail(request.getEmail());
    employee.setPhone(request.getPhone());
    employee.setCity(request.getCity());
    employee.setPosition(request.getPosition());

    try {
    NominatimResponse location = nominatimService.searchCity(request.getCity());

    if (location != null) {
        employee.setLatitude(new BigDecimal(location.getLat()));
        employee.setLongitude(new BigDecimal(location.getLon()));
        employee.setDisplayName(location.getDisplayName());
    }

} catch (Exception e) {
    System.out.println("No fue posible obtener la ubicación: " + e.getMessage());
}

    employee = repository.save(employee);

    return EmployeeMapper.toResponse(employee);
}

    public void delete(UUID id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Empleado no encontrado");
        }

        employeeBenefitRepository.deleteByEmployeeId(id);

        repository.deleteById(id);
    }
}