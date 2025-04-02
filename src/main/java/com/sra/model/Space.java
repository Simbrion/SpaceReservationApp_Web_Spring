package com.sra.model;

import java.io.IOException;
import java.io.Serializable;

import com.sra.config.Config;
import com.sra.service.spaceoperations.SpacePriceSelector;
import com.sra.service.spaceoperations.SpaceTypeSelector;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
@Entity
@Table(name = "space")
public class Space implements Describable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name",length=20, unique=true, nullable = false)
    private String name;

    @Column(name = "type_of_space", nullable = false)
    private TypeOfSpace typeOfSpace;

    @Column(name = "price", nullable = false)
    private int price;


    public void setType(TypeOfSpace type) {
        this.typeOfSpace = type;
    }

    public void initialize(String nameOfNewSpace, SpaceTypeSelector spaceTypeSelector, SpacePriceSelector spacePriceSelector) throws IOException {
        this.setName(nameOfNewSpace);
        spaceTypeSelector.selectType(this);
        spacePriceSelector.start(this);
    }

    public void printDescription() {
        System.out.println(Config.YELLOW_COLOUR + String.format(" - Space named %s, space type: %s, price per hour: USD %d.",
                this.name,
                this.getTypeAsString(),
                this.price) + Config.RESET_COLOUR);
    }

    public String getTypeAsString() {
        return switch (this.typeOfSpace) {
            case TypeOfSpace.CONFERENCE_ROOM -> "conference_room";
            case TypeOfSpace.PRIVATE_ROOM -> "private_room";
            case TypeOfSpace.OPEN_SPACE -> "open_space";
        };
    }

}
