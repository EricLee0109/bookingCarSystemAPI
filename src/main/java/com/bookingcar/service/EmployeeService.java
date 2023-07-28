package com.bookingcar.service;

import com.bookingcar.domain.Employee;
import com.bookingcar.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Employee}.
 */
@Service
@Transactional
public class EmployeeService {

    private final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Save a employee.
     *
     * @param employee the entity to save.
     * @return the persisted entity.
     */
    public Employee save(Employee employee) {
        log.debug("Request to save Employee : {}", employee);
        return employeeRepository.save(employee);
    }

    /**
     * Update a employee.
     *
     * @param employee the entity to save.
     * @return the persisted entity.
     */
    public Employee update(Employee employee) {
        log.debug("Request to update Employee : {}", employee);
        return employeeRepository.save(employee);
    }

    /**
     * Partially update a employee.
     *
     * @param employee the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Employee> partialUpdate(Employee employee) {
        log.debug("Request to partially update Employee : {}", employee);

        return employeeRepository
            .findById(employee.getId())
            .map(existingEmployee -> {
                if (employee.getPhone() != null) {
                    existingEmployee.setPhone(employee.getPhone());
                }
                if (employee.getGender() != null) {
                    existingEmployee.setGender(employee.getGender());
                }

                return existingEmployee;
            })
            .map(employeeRepository::save);
    }

    /**
     * Get all the employees.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        log.debug("Request to get all Employees");
        return employeeRepository.findAll();
    }

    /**
     * Get all the employees with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Employee> findAllWithEagerRelationships(Pageable pageable) {
        return employeeRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one employee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Employee> findOne(Long id) {
        log.debug("Request to get Employee : {}", id);
        return employeeRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the employee by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Employee : {}", id);
        employeeRepository.deleteById(id);
    }
}
