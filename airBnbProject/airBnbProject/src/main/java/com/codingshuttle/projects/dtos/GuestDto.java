package com.codingshuttle.projects.dtos;

import com.codingshuttle.projects.entities.enums.Gender;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuestDto {
    private Long id;
    private String name;
    private Gender gender;
    private Integer age;
}
