package com.model;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;


@Data
@Component
@Entity
@Table(name = "space")
public class Space implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull (message = "The field is mandatory.")
    @Length(max = 20, message = "The name should be up to 20 letters long. Please choose another name.")
    @Length(min = 3, message = "The name should be up at least 3 letters long. Please choose another name.")
    @Column(name = "name",length=20, unique=true, nullable = false)
    private String name;

    @NotNull (message = "  Please choose one of the options.")
    @Column(name = "type_of_space", nullable = false)
    private TypeOfSpace typeOfSpace;

    @Positive (message = "Price should not be lower than zero. Please set te correct price.")
    @NotNull (message = "The field is mandatory.")
    @Column(name = "price", nullable = false)
    private int price;

    public void setType(TypeOfSpace type) {
        this.typeOfSpace = type;
    }

}
