package com.rest.foody.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.NaturalId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Role extends BaseEntity{


	@NaturalId
    @Column(length = 70, unique = true)
    private String name;

    @Column(length = 255)
    private String description;
    
    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
