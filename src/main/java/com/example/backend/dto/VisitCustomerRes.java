package com.example.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitCustomerRes {
//    v.priority, h.name, c.first_name,c.last_name, u.email , c.phone
    private boolean priority;
    private String project;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;


}
