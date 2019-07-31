package com.crm.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "salesman_table")
public class Salesman {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String mobile_phone;

    private String email;
    
    private String address;
 
}