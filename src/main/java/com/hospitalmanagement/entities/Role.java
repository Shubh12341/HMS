package com.hospitalmanagement.entities;


import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 60)
    private String name;
}
