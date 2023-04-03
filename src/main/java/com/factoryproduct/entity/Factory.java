package com.factoryproduct.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Factory {

    @Id
    @SequenceGenerator(
            name = "factory_sequence",
            sequenceName = "factory_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "factory_sequence"
    )
    private Long id;
    private String name;
    private String region;

    public Factory(Long id, String name, String region) {
        this.id = id;
        this.name = name;
        this.region = region;
    }

    public Factory() {
    }

    @Override
    public String toString() {
        return "Factory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
