package com.micro.cors.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "category")
public class Category {
	
	@Id
	@Column(name = "code")
    private String code;
    
	@Column(name = "label", nullable = false)
    private String label;

}
