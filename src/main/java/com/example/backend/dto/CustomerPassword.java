package com.example.backend.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerPassword {
    private Long id;
    private String oldPassword;

    private String newPassword;
    private String againNewPassword;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getOldPassword() {
//        return oldPassword;
//    }
//
//    public void setOldPassword(String oldPassword) {
//        this.oldPassword = oldPassword;
//    }
//
//    public String getNewPassword() {
//        return newPassword;
//    }
//
//    public void setNewPassword(String newPassword) {
//        this.newPassword = newPassword;
//    }
//
//    public String getAgainNewPassword() {
//        return againNewPassword;
//    }
//
//    public void setAgainNewPassword(String againNewPassword) {
//        this.againNewPassword = againNewPassword;
//    }
}
