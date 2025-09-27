package com.example.userauthservice.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Role extends BaseModel{

    String roleName;

    @ManyToMany(mappedBy = "roles")
    List<User> users;
}
