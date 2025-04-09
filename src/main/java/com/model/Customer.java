package com.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull (message = "The name cannot be null.")
    @Length (min = 3, message = "The name should be at least 3 letters long.")
    @Length (max = 20, message = "The name should be not longer than 20 letters.")
    @Column(name = "name",length=20, unique=true, nullable=false)
    private String name;

}
