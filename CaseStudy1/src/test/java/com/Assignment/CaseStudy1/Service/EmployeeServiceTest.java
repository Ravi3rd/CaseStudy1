package com.Assignment.CaseStudy1.Service;

import com.Assignment.CaseStudy1.Entity.Employee;
import com.Assignment.CaseStudy1.Repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        employee = new Employee();
        employee.setId(1L);
        employee.setName("Ravi Ranjan");
        employee.setEmailId("ravi@gmail.com");
    }



    @Test
    void saveEmployee() {
        when(employeeRepository.save(employee)).thenReturn(employee);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        assertEquals(1L, savedEmployee.getId());
        assertEquals("Ravi Ranjan", savedEmployee.getName());
        assertEquals("ravi@gmail.com", savedEmployee.getEmailId());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void getEmployeeById() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Optional<Employee> foundEmployee = employeeService.getEmployeeById(1L);
        assertTrue(foundEmployee.isPresent());
        assertEquals(1L, foundEmployee.get().getId());
        assertEquals("Ravi Ranjan", foundEmployee.get().getName());
        assertEquals("ravi@gmail.com", foundEmployee.get().getEmailId());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void updateEmployeeById() {
        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("Ravi Ranjan");
        updatedEmployee.setEmailId("ravi@gmail.com");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);
        Optional<Employee> result = employeeService.updateEmployeeById(1L, updatedEmployee);
        assertEquals("Ravi Ranjan", result.get().getName());
        assertEquals("ravi@gmail.com", result.get().getEmailId());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(employee);
    }
}