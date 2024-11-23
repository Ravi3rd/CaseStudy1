package com.Assignment.CaseStudy1.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long  id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false,unique = true)
    String emailId;
}
