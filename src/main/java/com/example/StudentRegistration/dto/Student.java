package com.example.StudentRegistration.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Student
{
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
}
