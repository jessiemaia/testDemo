package com.example.CourtneyDemo.entity;

import lombok.Data;
import lombok.Builder;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name="EXAMPLE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    @Column(name = "NAME")
    private String name;

    @Column(name = "ISVEGAN")
    private boolean isVegan;

}
