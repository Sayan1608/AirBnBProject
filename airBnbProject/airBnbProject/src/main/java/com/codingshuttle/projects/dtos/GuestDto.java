package com.codingshuttle.projects.dtos;

import com.codingshuttle.projects.entities.enums.Gender;
import jakarta.persistence.*;

public class GuestDto {
    private Long id;
    private String name;
    private Gender gender;
    private Integer age;
}
