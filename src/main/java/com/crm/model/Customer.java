package com.crm.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "customer_table")
public class Customer {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String mobile_phone;

    private String email;

    private String sex;
    
    private String address;
    
    private String created_at;
    
 
}